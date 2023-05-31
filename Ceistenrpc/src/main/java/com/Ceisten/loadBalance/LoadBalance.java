package com.Ceisten.loadBalance;

import com.Ceisten.common.URL;

import java.util.List;
import java.util.Random;

public class LoadBalance {
    //随机策略
    public static URL random(List<URL>urls){
        Random random = new Random();
        int i = random.nextInt(urls.size());
        return urls.get(i);
    }
}
