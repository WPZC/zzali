package com.zz.zzspringtest.manage;

import com.zz.zzspringtest.inf.Persion;
import org.springframework.stereotype.Component;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/11/3 17:33
 */
@Component
public class PersionManage {

    public String persionEat(Persion persion){

        return persion.eat();

    }

}
