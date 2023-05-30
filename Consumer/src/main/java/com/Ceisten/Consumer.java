package com.Ceisten;


import com.Ceisten.common.Invocation;

public class Consumer {
    public static void main(String[] args) {
        Invocation invocation = new Invocation(HelloService.class.getName(),
                "sayHello", new Class[]{String.class}, new Object[]{"Ceisten"});
    }
}
