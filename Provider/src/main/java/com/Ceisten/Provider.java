package com.Ceisten;

import com.Ceisten.protocal.HttpServer;

public class Provider {
    public static void main(String[] args) {
        //Netty,Tomcat，SocketServer
        HttpServer httpServer = new HttpServer();
        httpServer.start("localhost",8080);
    }
}
