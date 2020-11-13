package com.zz.zzspringtest.inf;

import org.springframework.stereotype.Component;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/11/3 17:36
 */
@Component
public class PersionImpl implements Persion{
    @Override
    public String eat() {
        return "用筷子";
    }
}
