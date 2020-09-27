package zztest.test.callback;

import java.util.Calendar;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/9/24 16:19
 */
public class T1 {

    public static void main(String[] args) {
        ts();
        test(new CallBack());
    }

    public static String test(ICallBack<String> iCallBack){
        return iCallBack.doCallBack();
    }

    public static String ts(){
        test(new ICallBack<String>() {
            @Override
            public String doCallBack() {
                System.out.println("123123123");
                return "";
            }
        });

        return "123";
    }

    static class CallBack implements ICallBack<String>{

        @Override
        public String doCallBack() {
            return "1111111111";
        }
    }

}
