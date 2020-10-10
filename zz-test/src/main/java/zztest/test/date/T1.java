package zztest.test.date;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.Date;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/9/30 10:39
 */
public class T1 {


    public static void main(String[] args) {

        Student student = new Student();

        student.setDate(new Date());
        student.setName("dwa");

        System.out.println(JSONObject.toJSONString(student));

        System.out.println(new Date(1601433705468L));

    }


    @Data
    static
    class Student{

        private String name;
        private Date date;

    }

}
