package zztest.test;

import com.netflix.client.http.HttpRequest;
import com.zz.ejlchina.okhttps.GsonMsgConvertor;
import com.zz.ejlchina.okhttps.HTTP;
import com.zz.ejlchina.okhttps.HttpResult;

import java.io.IOException;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/9/10 10:35
 */
public class HttpTest {

    public static void main(String[] args) {
        HTTP http = HTTP.builder()
                .baseUrl("http://localhost:9503/apiswr/vzzjwt/token?username=admin&password=123456")
                .addMsgConvertor(new GsonMsgConvertor())
                .build();

        String s = "123";
        //ResultVO resultVO = new ResultVO<String>();
        //resultVO = http.sync("").get().getBody().toBean(resultVO.getClass());
        http.async("").setOnResponse(r->{
            System.out.println("回调了");
            System.out.println(s);
        }).setOnException((IOException r)->{
            r.printStackTrace();
        })
                .get();


        System.out.println("执行完毕");
        System.gc();
    }

}
