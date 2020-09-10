package zztest.test;

import com.zz.ejlchina.okhttps.GsonMsgConvertor;
import com.zz.ejlchina.okhttps.HTTP;
import com.zz.ejlchina.okhttps.HttpResult;
import com.zz.region.vo.ResultVO;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/9/10 10:35
 */
public class HttpTest {

    public static void main(String[] args) {
        HTTP http = HTTP.builder()
                .baseUrl("http://139.159.184.129:9090/api/token?username=admin&password=123456")
                .addMsgConvertor(new GsonMsgConvertor())
                .build();

        ResultVO resultVO = new ResultVO<String>();
        resultVO = http.sync("").get().getBody().toBean(resultVO.getClass());
        System.out.println(resultVO);
    }

}
