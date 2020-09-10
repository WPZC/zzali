package com.zz.region.methods.ead;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * security加解密工具
 * @author wqy
 * @version 1.0
 * @date 2020/9/9 14:59
 */
public class EAD {

    /**
     * 密钥
     */
    private static final String EAD_PASSWORD = "775727";

    private static final BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();

    /**
     * 随机加密生成
     * @return
     */
    public static String encode(){
        String hashPass = bcryptPasswordEncoder.encode(EAD_PASSWORD);
        return hashPass;
    }

    /**
     * 解密
     * @param hashPass
     * @return
     */
    public static Boolean decode(String hashPass){
        boolean f = bcryptPasswordEncoder.matches(EAD_PASSWORD,hashPass);
        return f;
    }


}
