package com.Ceisten;

import com.Ceisten.common.URL;
import com.Ceisten.protocal.HttpServer;
import com.Ceisten.register.LocalRegister;
import com.Ceisten.register.MapRemoteRegister;

public class Provider {
    public static void main(String[] args) {
        //服务本地注册 ，把接口名，版本号和对应实现类注册
        LocalRegister.regist(HelloService.class.getName(),"1.0",HelloServiceImpl.class);
        LocalRegister.regist(HelloService.class.getName(),"2.0",HelloServiceImpl2.class);
        //注册中心注册（remoteRegist）服务注册
        URL url = new URL("localhost",8080);
        MapRemoteRegister.regist(HelloService.class.getName(),url);

        //启动网络通信服务：Netty,Tomcat，SocketServer
        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostname(), url.getPort());
    }
}
