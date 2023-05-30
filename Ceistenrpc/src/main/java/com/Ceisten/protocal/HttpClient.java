package com.Ceisten.protocal;

import com.Ceisten.common.Invocation;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpClient {

    public String send(String hostname, Integer port, Invocation invocation) throws IOException {
        //这里发送一个http请求，也可以读取用户配置
        try {
            URL url = new URL("http", hostname, port, "/");
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(invocation);
            oos.flush();
            oos.close();
            InputStream inputStream = httpURLConnection.getInputStream();   //这里是阻塞式获取，后期可以用netty优化
            String result = IOUtils.toString(inputStream);
            return result;
        } catch (MalformedURLException var10) {
            return null;
        } catch (IOException var11) {
             throw var11;
        }
    }
}
