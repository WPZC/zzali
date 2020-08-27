package com.zz.gateway.config;

import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.DecodeException;
import feign.codec.Decoder;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;

public class FeignResultDecoder implements Decoder {

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        if (response.body() == null) {
            throw new DecodeException(response.status(), "没有返回有效的数据", response.request());
        }
        System.out.println(response.toString());
        Response.Body body = response.body();
        Reader reader = body.asReader(Util.UTF_8);
        char[] chars = new char[0];
        chars = new char[reader.read()];
        reader.read(chars);
        String str = new String(chars);

//        Reader reader = response.body().asReader(Util.UTF_8);
//        String bodyStr = Util.toString(reader);
        //对结果进行转换
//        ResultVO result = (ResultVO) JsonUtil.json2obj(bodyStr, type);
//        //如果返回错误，且为内部错误，则直接抛出异常
//        if (result.getCode() != ResultCode.SUCCESS.code) {
//            if (!result.isUserPrompt()) {
//                throw new DecodeException(response.status(), "接口返回错误：" + result.getMessage(), response.request());
//            }
//        }
        return null;
    }

}
