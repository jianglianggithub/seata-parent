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
package io.seata.sqlparser.druid;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLDeleteStatement;
import com.alibaba.druid.sql.ast.statement.SQLInsertStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.ast.statement.SQLUpdateStatement;
import io.seata.common.util.CollectionUtils;
import io.seata.sqlparser.SQLRecognizer;
import io.seata.sqlparser.SQLRecognizerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * DruidSQLRecognizerFactoryImpl
 *
 * @author sharajava
 * @author ggndnn
 */
class DruidSQLRecognizerFactoryImpl implements SQLRecognizerFactory {
    public static void main(String[] args) {
        List<SQLRecognizer> mysql = new DruidSQLRecognizerFactoryImpl().create("insert into test(id,name,descc,dept_id) values ('2','333','1',null)", "mysql");
        SQLRecognizer sqlRecognizer = mysql.get(0);
        String tableName = sqlRecognizer.getTableName();
        System.out.println(tableName);
    }

    @Override
    public List<SQLRecognizer> create(String sql, String dbType) {
        List<SQLStatement> asts = SQLUtils.parseStatements(sql, dbType);
        if (CollectionUtils.isEmpty(asts)) {
            throw new UnsupportedOperationException("Unsupported SQL: " + sql);
        }

        // 如果执行的sql  是一个多sql 比如 insert xxx ; insert xxxx  多条sql一次执行 必须 是 update delete 同一种类型
        // insert 为什么没有呢。。。？ 不知道为什么insert 不支持 多条同时执行
        if (asts.size() > 1 && !(asts.stream().allMatch(statement -> statement instanceof SQLUpdateStatement)
                || asts.stream().allMatch(statement -> statement instanceof SQLDeleteStatement))) {
            throw new UnsupportedOperationException("ONLY SUPPORT SAME TYPE (UPDATE OR DELETE) MULTI SQL -" + sql);
        }
        List<SQLRecognizer> recognizers = null;
        SQLRecognizer recognizer = null;
        for (SQLStatement ast : asts) {
            SQLOperateRecognizerHolder recognizerHolder =
                    SQLOperateRecognizerHolderFactory.getSQLRecognizerHolder(dbType.toLowerCase());
            if (ast instanceof SQLInsertStatement) {
                recognizer = recognizerHolder.getInsertRecognizer(sql, ast);
            } else if (ast instanceof SQLUpdateStatement) {
                recognizer = recognizerHolder.getUpdateRecognizer(sql, ast);
            } else if (ast instanceof SQLDeleteStatement) {
                recognizer = recognizerHolder.getDeleteRecognizer(sql, ast);
            } else if (ast instanceof SQLSelectStatement) {
                recognizer = recognizerHolder.getSelectForUpdateRecognizer(sql, ast);
            }
            if (recognizer != null) {
                if (recognizers == null) {
                    recognizers = new ArrayList<>();
                }
                recognizers.add(recognizer);
            }
        }
        return recognizers;
    }
}
