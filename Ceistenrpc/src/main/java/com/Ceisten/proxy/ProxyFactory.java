package com.Ceisten.proxy;

import com.Ceisten.common.Invocation;
import com.Ceisten.common.URL;
import com.Ceisten.loadBalance.LoadBalance;
import com.Ceisten.protocal.HttpClient;
import com.Ceisten.register.MapRemoteRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class ProxyFactory {
    public  static <T> T getProxy(Class interfaceClass){
        //用户配置
        Object proxyInstance = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Invocation invocation = new Invocation(interfaceClass.getName(),
                        method.getName(), method.getParameterTypes(), args);

                HttpClient httpClient = new HttpClient();
                //服务发现    根据接口名字找到当前服务有哪些IP地址和端口
                List<URL> list = MapRemoteRegister.get(interfaceClass.getName(),"1.0");

                /*//负载均衡
                URL url = LoadBalance.random(list);*/

                //服务调用（发送请求）
                String result = null;
                int maxTry= 3;
                List<URL>invokedUrls =  new ArrayList<>();

                while(maxTry >=0){
                    //负载均衡
                    list.remove(invokedUrls);
                    URL url = LoadBalance.random(list);
                    invokedUrls.add(url);

                    try {
                        result = httpClient.send(url.getHostname(), url.getPort(), invocation);
                    }catch (Exception e){
                        if (maxTry==0) continue;
                        //error - callback = com.Ceisten.HelloServiceErrorCallback implement helloService  (服务容错)
                        //可以调用别的方法
                        return "报错啦！";
                    }
                        maxTry--;
                }
                return result;

            }
        });
        return (T) proxyInstance;
    }
}
