package com.zz;

import com.zz.domain.ResultVO;

/**
 * 返回和接收校验
 * @author wqy
 * @version 1.0
 * @date 2020/6/9 15:52
 */
public class Backtrack{

    /**
     * 成功的执行
     * @param t 数据体
     * @param msg 备注
     * @param <T> 泛型
     * @return
     */
    public static <T> ResultVO<T> success(T t, String msg){

        ResultVO<T> resultVO = new ResultVO<>();

        resultVO.setCode(10);
        resultVO.setData(t);
        resultVO.setMsg(msg);

        return resultVO;

    }

    /**
     * 成功的执行
     * @param t 数据体
     * @param <T> 泛型
     * @return
     */
    public static <T> ResultVO<T> success(T t){

        ResultVO<T> resultVO = new ResultVO<>();

        resultVO.setCode(10);
        resultVO.setData(t);

        return resultVO;

    }

    /**
     * 成功的执行
     * @param msg 备注
     * @param <T> 泛型
     * @return
     */
    public static <T> ResultVO<T> success(String msg){

        ResultVO<T> resultVO = new ResultVO<>();

        resultVO.setCode(10);
        resultVO.setMsg(msg);

        return resultVO;

    }

    /**
     * 失败的执行
     * @param t 数据体
     * @param msg 备注
     * @param <T> 泛型
     * @return
     */
    public static <T> ResultVO<T> errot(T t,String msg){

        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(11);
        resultVO.setData(t);
        resultVO.setMsg(msg);
        return resultVO;

    }

    /**
     * 失败的执行
     * @param msg 备注
     * @param <T> 泛型
     * @return
     */
    public static <T> ResultVO<T> errot(String msg){

        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(11);
        resultVO.setMsg(msg);
        return resultVO;

    }

    /**
     * 校验ResultVO结果
     * 如果code==10则成功其余则失败
     * @param resultVO
     * @return
     */
    public static <T> T checkData(ResultVO<T> resultVO){

        //判断返回结果是否是SuperEntity的子类
        //11为异常，失败
        if(resultVO.getCode()==11){
            throw new RuntimeException(resultVO.getMsg());
        }
        if(resultVO.getCode()==10){
            return resultVO.getData();
        }
        throw new RuntimeException("调用失败");

    }

    /**
     * 校验ResultVO结果
     * 如果code==10则成功其余则失败
     * @param resultVO
     * @return
     */
    public static String checkMsg(ResultVO<String> resultVO){

        //判断返回结果是否是SuperEntity的子类
        //11为异常，失败
        if(resultVO.getCode()==11){
            throw new RuntimeException(resultVO.getMsg());
        }
        if(resultVO.getCode()==10){
            return resultVO.getMsg();
        }
        throw new RuntimeException("调用失败");

    }

}
