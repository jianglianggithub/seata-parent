//package io.seata.server.test;
//
//import org.checkerframework.checker.units.qual.C;
//
//public class Handle {
//    public static void main(String[] args) {
//
//    }
//}
//
//
//
//
//
//
//abstract class Req {}
//abstract class Resp{}
//
//class RegisterReq extends Req {}
//class RegisterResp extends Resp {}
//
//
//
//interface Compulate<T extends Req,S extends Resp> {
//    void execute(T req,S resp);
//    void OnSuccess(T req,S resp);
//}
//
//interface HandleProcessor<T extends Req,S extends Resp> {
//    void executeTempalte(T req,S resp,Compulate<T,S> call);
//}
//
//class Template {
//
//
//    public <T extends Req,S extends Resp> void executeTempalte(T req, S resp, Compulate<T, S> call) {
//
//    }
//}
//
//class Bott extends Template {
//    public void test() {
//        executeTempalte();
//    }
//}
