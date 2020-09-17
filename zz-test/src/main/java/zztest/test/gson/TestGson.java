package zztest.test.gson;

import lombok.Data;

import javax.persistence.Transient;
import java.util.Date;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/9/15 14:56
 */
@Data
public class TestGson {

    private String name;
    private String age;
    private Date date;
    private Date date2;

    private transient String nameage;

}
