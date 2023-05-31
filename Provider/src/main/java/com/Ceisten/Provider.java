package com.Ceisten;

import com.Ceisten.protocal.HttpServer;
import com.Ceisten.register.LocalRegister;

public class Provider {
    public static void main(String[] args) {
        //服务本地注册 ，把接口名，版本号和对应实现类注册
        LocalRegister.regist(HelloService.class.getName(),"1.0",HelloServiceImpl.class);
        LocalRegister.regist(HelloService.class.getName(),"2.0",HelloServiceImpl2.class);
        //注册中心注册（remoteRegist）
        //Netty,Tomcat，SocketServer
        HttpServer httpServer = new HttpServer();
        httpServer.start("localhost",8080);
    }
}
