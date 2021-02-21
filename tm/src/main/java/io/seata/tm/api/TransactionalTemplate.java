/*
 *  Copyright 1999-2019 Seata.io Group.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package io.seata.tm.api;

import java.util.List;

import io.seata.common.exception.ShouldNeverHappenException;
import io.seata.core.context.GlobalLockConfigHolder;
import io.seata.core.exception.TransactionException;
import io.seata.core.model.GlobalLockConfig;
import io.seata.core.model.GlobalStatus;
import io.seata.tm.api.transaction.Propagation;
import io.seata.tm.api.transaction.SuspendedResourcesHolder;
import io.seata.tm.api.transaction.TransactionHook;
import io.seata.tm.api.transaction.TransactionHookManager;
import io.seata.tm.api.transaction.TransactionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Template of executing business logic with a global transaction.
 *  TM 事务 执行的 开始边界

 */
public class TransactionalTemplate {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionalTemplate.class);


    /**
     * Execute object.
     *
     * @param business the business
     * @return the object
     * @throws TransactionalExecutor.ExecutionException the execution exception
     */
    public Object execute(TransactionalExecutor business) throws Throwable {
        // 1. Get transactionInfo
        TransactionInfo txInfo = business.getTransactionInfo();
        if (txInfo == null) {
            throw new ShouldNeverHappenException("transactionInfo does not exist");
        }
        // 1.1 Get current transaction, if not null, the tx role is 'GlobalTransactionRole.Participant'.
        //  获取本地已存在的事务
        GlobalTransaction tx = GlobalTransactionContext.getCurrent();

        // 1.2 Handle the transaction propagation.
        Propagation propagation = txInfo.getPropagation();
        SuspendedResourcesHolder suspendedResourcesHolder = null;
        try {
            /**
             *   突然想起来spring 的事务了 有一个是 保存点
             *   允许子事务 异常捕获 子事务回滚 外事务提交
             *
             *   如果不是保存点 是默认的 话 如果你catch 的话 spring 自己会抛出异常 的
             *   因为 即使你catch了 spring 也会知道 当外层 func 走完的时候 会检测出来直接 rollback 并且抛出异常
             */
            switch (propagation) {
                case NOT_SUPPORTED:
                    // If transaction is existing, suspend it.
                    if (existingTransaction(tx)) {
                        suspendedResourcesHolder = tx.suspend();
                    }
                    // Execute without transaction and return.
                    return business.execute();
                case REQUIRES_NEW:
                    // 存在事务挂起事务 并且新建一个事务。这个和spring 中 的一样 会吧之前的事务信息 保存到 一个 ThreadLocal 中 非回链表
                    if (existingTransaction(tx)) {
                        suspendedResourcesHolder = tx.suspend();
                        tx = GlobalTransactionContext.createNew();
                    }
                    // Continue and execute with new transaction
                    break;
                case SUPPORTS:
                    // If transaction is not existing, execute without transaction.
                    if (notExistingTransaction(tx)) {
                        return business.execute();
                    }
                    // Continue and execute with new transaction
                    break;

                case REQUIRED:
                    // 如果有事务加入当前事务，如果没有事务会创建一个新的事务
                    /**
                     *  如果存在事务 那么 gtx != null 成立 便是加入了事务了。愚钝啊 愚钝
                     */
                    break;
                case NEVER:
                    // If transaction is existing, throw exception.
                    if (existingTransaction(tx)) {
                        throw new TransactionException(
                            String.format("Existing transaction found for transaction marked with propagation 'never', xid = %s"
                                    , tx.getXid()));
                    } else {
                        // Execute without transaction and return.
                        return business.execute();
                    }
                case MANDATORY:
                    // If transaction is not existing, throw exception.
                    if (notExistingTransaction(tx)) {
                        throw new TransactionException("No existing transaction found for transaction marked with propagation 'mandatory'");
                    }
                    // Continue and execute with current transaction.
                    break;
                default:
                    throw new TransactionException("Not Supported Propagation:" + propagation);
            }

            // 1.3 创建一个新的全局事务
            if (tx == null) {
                tx = GlobalTransactionContext.createNew();
            }

            // 缓存事务信息
            /**
             *  这个动作 意义未明确 后续 需要回来看一下
             */
            GlobalLockConfig previousConfig = replaceGlobalLockConfig(txInfo);

            try {
                // 2. If the tx role is 'GlobalTransactionRole.Launcher', send the request of beginTransaction to TC,
                //    else do nothing. Of course, the hooks will still be triggered.
                // 向TC 发起一次 事务会话 并且将 TC返回的 xid 全局事务id 存储在 RootContext 中
                beginTransaction(txInfo, tx);

                Object rs;
                try {
                    // Do Your Business
                    // 执行后续的 target.method()
                    rs = business.execute();
                } catch (Throwable ex) {
                    // 3. The needed business exception to rollback.
                    completeTransactionAfterThrowing(txInfo, tx, ex);
                    throw ex;
                }

                // 4. everything is fine, commit.
                commitTransaction(tx);

                return rs;
            } finally {
                //5. clear
                resumeGlobalLockConfig(previousConfig);
                triggerAfterCompletion();
                cleanUp();
            }
        } finally {
            // If the transaction is suspended, resume it.
            if (suspendedResourcesHolder != null) {
                tx.resume(suspendedResourcesHolder);
            }
        }
    }

    private boolean existingTransaction(GlobalTransaction tx) {
        return tx != null;
    }

    private boolean notExistingTransaction(GlobalTransaction tx) {
        return tx == null;
    }

    private GlobalLockConfig replaceGlobalLockConfig(TransactionInfo info) {
        GlobalLockConfig myConfig = new GlobalLockConfig();
        myConfig.setLockRetryInternal(info.getLockRetryInternal());
        myConfig.setLockRetryTimes(info.getLockRetryTimes());
        return GlobalLockConfigHolder.setAndReturnPrevious(myConfig);
    }

    private void resumeGlobalLockConfig(GlobalLockConfig config) {
        if (config != null) {
            GlobalLockConfigHolder.setAndReturnPrevious(config);
        } else {
            GlobalLockConfigHolder.remove();
        }
    }

    private void completeTransactionAfterThrowing(TransactionInfo txInfo, GlobalTransaction tx, Throwable originalException) throws TransactionalExecutor.ExecutionException {
        //roll back
        if (txInfo != null && txInfo.rollbackOn(originalException)) {
            try {
                rollbackTransaction(tx, originalException);
            } catch (TransactionException txe) {
                // Failed to rollback
                throw new TransactionalExecutor.ExecutionException(tx, txe,
                        TransactionalExecutor.Code.RollbackFailure, originalException);
            }
        } else {
            // not roll back on this exception, so commit
            commitTransaction(tx);
        }
    }

    private void commitTransaction(GlobalTransaction tx) throws TransactionalExecutor.ExecutionException {
        try {
            triggerBeforeCommit();


            tx.commit();



            triggerAfterCommit();
        } catch (TransactionException txe) {
            // 4.1 Failed to commit
            throw new TransactionalExecutor.ExecutionException(tx, txe,
                TransactionalExecutor.Code.CommitFailure);
        }
    }

    private void rollbackTransaction(GlobalTransaction tx, Throwable originalException) throws TransactionException, TransactionalExecutor.ExecutionException {
        triggerBeforeRollback();
        tx.rollback();
        triggerAfterRollback();
        // 3.1 Successfully rolled back
        throw new TransactionalExecutor.ExecutionException(tx, GlobalStatus.RollbackRetrying.equals(tx.getLocalStatus())
            ? TransactionalExecutor.Code.RollbackRetrying : TransactionalExecutor.Code.RollbackDone, originalException);
    }

    private void beginTransaction(TransactionInfo txInfo, GlobalTransaction tx) throws TransactionalExecutor.ExecutionException {
        try {
            // 钩子
            triggerBeforeBegin();

            tx.begin(txInfo.getTimeOut(), txInfo.getName());


            triggerAfterBegin();
        } catch (TransactionException txe) {
            throw new TransactionalExecutor.ExecutionException(tx, txe,
                TransactionalExecutor.Code.BeginFailure);

        }
    }

    private void triggerBeforeBegin() {
        for (TransactionHook hook : getCurrentHooks()) {
            try {
                hook.beforeBegin();
            } catch (Exception e) {
                LOGGER.error("Failed execute beforeBegin in hook {}", e.getMessage(), e);
            }
        }
    }

    private void triggerAfterBegin() {
        for (TransactionHook hook : getCurrentHooks()) {
            try {
                hook.afterBegin();
            } catch (Exception e) {
                LOGGER.error("Failed execute afterBegin in hook {}", e.getMessage(), e);
            }
        }
    }

    private void triggerBeforeRollback() {
        for (TransactionHook hook : getCurrentHooks()) {
            try {
                hook.beforeRollback();
            } catch (Exception e) {
                LOGGER.error("Failed execute beforeRollback in hook {}", e.getMessage(), e);
            }
        }
    }

    private void triggerAfterRollback() {
        for (TransactionHook hook : getCurrentHooks()) {
            try {
                hook.afterRollback();
            } catch (Exception e) {
                LOGGER.error("Failed execute afterRollback in hook {}", e.getMessage(), e);
            }
        }
    }

    private void triggerBeforeCommit() {
        for (TransactionHook hook : getCurrentHooks()) {
            try {
                hook.beforeCommit();
            } catch (Exception e) {
                LOGGER.error("Failed execute beforeCommit in hook {}", e.getMessage(), e);
            }
        }
    }

    private void triggerAfterCommit() {
        for (TransactionHook hook : getCurrentHooks()) {
            try {
                hook.afterCommit();
            } catch (Exception e) {
                LOGGER.error("Failed execute afterCommit in hook {}", e.getMessage(), e);
            }
        }
    }

    private void triggerAfterCompletion() {
        for (TransactionHook hook : getCurrentHooks()) {
            try {
                hook.afterCompletion();
            } catch (Exception e) {
                LOGGER.error("Failed execute afterCompletion in hook {}", e.getMessage(), e);
            }
        }
    }

    private void cleanUp() {
        TransactionHookManager.clear();
    }

    private List<TransactionHook> getCurrentHooks() {
        return TransactionHookManager.getHooks();
    }
}
