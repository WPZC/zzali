package com.zz.region.methods.thread.local.task.callable;

import java.util.concurrent.Callable;

/**
 * Callable任务
 * @author wqy
 * @version 1.0
 * @date 2020/8/21 16:15
 */
public interface CallableTask<V> extends Callable<V> {

    @Override
    V call() throws Exception;

}
