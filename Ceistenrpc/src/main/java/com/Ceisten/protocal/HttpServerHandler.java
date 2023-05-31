package com.Ceisten.protocal;

import com.Ceisten.common.Invocation;
import com.Ceisten.register.LocalRegister;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HttpServerHandler {
    public HttpServerHandler() {
    }
    public void handle(HttpServletRequest req, HttpServletResponse resp) {
        //处理请求-->接口、方法、方法参数
        //反序列化
        try {
            Invocation invocation = (Invocation) new ObjectInputStream(req.getInputStream()).readObject();
            String interfaceName = invocation.getInterfaceName();
            Class classImpl = LocalRegister.get(interfaceName,"1.0");
            Method method = classImpl.getMethod(invocation.getMethodName(), invocation.getParametersTypes());
            String result = (String) method.invoke(classImpl.newInstance(), invocation.getParameters());
            IOUtils.write(result, resp.getOutputStream());
        } catch (IOException var8) {
            var8.printStackTrace();
        } catch (ClassNotFoundException var9) {
            var9.printStackTrace();
        } catch (NoSuchMethodException var10) {
            var10.printStackTrace();
        } catch (InvocationTargetException var11) {
            var11.printStackTrace();
        } catch (IllegalAccessException var12) {
            var12.printStackTrace();
        } catch (InstantiationException var13) {
            var13.printStackTrace();
        }

    }
}
