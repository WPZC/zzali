package com.zz.security.exception;

import com.zz.domain.ResultVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * controller异常捕获
 * //默认加了@Controller和@RestController都能控制
 * @author wqy
 * @version 1.0
 * @date 2020/6/11 16:14
 */

@ControllerAdvice
public class ControllerException {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultVO exceptionHandler(Exception e){

        ResultVO<String> resultVO = new ResultVO<>();
        resultVO.setCode(11);
        resultVO.setMsg(e.getMessage());
        resultVO.setData(e.getMessage());
        e.printStackTrace();
        //发生异常进行日志记录，写入数据库或者其他处理，此处省略
        return resultVO;
    }

}
