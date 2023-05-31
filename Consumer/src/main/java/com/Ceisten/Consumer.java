package com.Ceisten;


import com.Ceisten.common.Invocation;
import com.Ceisten.protocal.HttpClient;
import com.Ceisten.proxy.ProxyFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Consumer {
    public static void main(String[] args) throws IOException {
        /*Invocation invocation = new Invocation(HelloService.class.getName(),
                "sayHello", new Class[]{String.class}, new Object[]{"Ceisten"});

        HttpClient httpClient = new HttpClient();
        String result = httpClient.send("localhost", 8080, invocation);
        System.out.println(result);*/

        HelloService helloService  = ProxyFactory.getProxy(HelloService.class);
        String result = helloService.sayHello("Ceisten");
        System.out.println(result);


    }
}
