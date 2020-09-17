package zztest.test.gson;

import com.google.gson.Gson;

import java.util.Date;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/9/15 14:57
 */
public class T1 {

    static Gson gson = new Gson();

    public static void main(String[] args) {

        TestGson testGson = new TestGson();

        testGson.setAge("1");
        testGson.setName("222");
        testGson.setNameage("333");
        testGson.setDate(new Date());

        String json = "{\"name\":\"222\",\"age\":\"1\",\"date\":\"2020-05-21 11:59:59\",\"date2\":\"2020-05-21\"}";

        System.out.println(testGson.getNameage());

        testGson = gson.fromJson(json,testGson.getClass());

        System.out.println(gson.toJson(testGson));


    }

}
