package zztest.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/9/9 14:58
 */
public class Test {

    public static void main(String[] args) {
        String pass = "admin";
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        String hashPass = bcryptPasswordEncoder.encode(pass);
        System.out.println(hashPass);

        boolean f = bcryptPasswordEncoder.matches("admin",hashPass);
        System.out.println(f);

    }
}
