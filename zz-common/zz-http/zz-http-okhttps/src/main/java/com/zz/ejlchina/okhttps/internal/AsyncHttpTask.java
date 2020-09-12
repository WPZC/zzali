package com.zz.ejlchina.okhttps.internal;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.zz.ejlchina.okhttps.*;
import com.zz.ejlchina.okhttps.HttpResult.State;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * 异步 Http 请求任务
 *  
 * @author Troy.Zhou
 * 
 */
public class AsyncHttpTask extends HttpTask<AsyncHttpTask> {

	
    private OnCallback<HttpResult> onResponse;
    private OnCallback<IOException> onException;
    private OnCallback<State> onComplete;
    
    private boolean responseOnIO;
    private boolean exceptionOnIO;
    private boolean completeOnIO;
    
    private OnCallback<HttpResult.Body> onResBody;
    private OnCallback<Mapper> onResMapper;
    private OnCallback<Array> onResArray;
    private OnCallback<String> onResString;
    private OnCallback<?> onResBean;
    private OnCallback<?> onResList;
    
    private boolean resBodyOnIO;
    private boolean resMapperOnIO;
    private boolean resArrayOnIO;
    private boolean resStringOnIO;
    private boolean resBeanOnIO;
    private boolean resListOnIO;
    
    private Type beanType;
	private Class<?> listType;
    
	public AsyncHttpTask(HttpClient client, String url) {
		super(client, url);
	}


	@Override
	public boolean isAsyncHttp() {
		return true;
	}

	/**
	 * 设置请求执行异常后的回调函数，设置后，相关异常将不再向上抛出
	 * @param onException 请求异常回调
	 * @return HttpTask 实例
	 */
    public AsyncHttpTask setOnException(OnCallback<IOException> onException) {
        this.onException = onException;
        exceptionOnIO = nextOnIO;
        nextOnIO = false;
        return this;
    }

	/**
	 * 设置请求执行完成后的回调函数，无论成功|失败|异常 都会被执行
	 * @param onComplete 请求完成回调
	 * @return HttpTask 实例
	 */
    public AsyncHttpTask setOnComplete(OnCallback<State> onComplete) {
        this.onComplete = onComplete;
        completeOnIO = nextOnIO;
        nextOnIO = false;
        return this;
    }
    
	/**
	 * 设置请求得到响应后的回调函数
	 * @param onResponse 请求响应回调
	 * @return HttpTask 实例
	 */
    public AsyncHttpTask setOnResponse(OnCallback<HttpResult> onResponse) {
        this.onResponse = onResponse;
        responseOnIO = nextOnIO;
        nextOnIO = false;
        return this;
    }
    
	/**
	 * 设置请求得到响应后的回调函数
	 * @param onResBody 响应报文体回调
	 * @return HttpTask 实例
	 */
    public AsyncHttpTask setOnResBody(OnCallback<HttpResult.Body> onResBody) {
    	this.onResBody = onResBody;
    	resBodyOnIO = nextOnIO;
        nextOnIO = false;
        return this;
    }
    
	/**
	 * 设置请求得到响应后的回调函数
	 * @param <T> 泛型
	 * @param type 期望的转换类型
	 * @param onResBean 响应 Bean 回调
	 * @return HttpTask 实例
	 */
    public <T> AsyncHttpTask setOnResBean(Class<T> type, OnCallback<T> onResBean) {
    	initBeanType(type);
    	this.onResBean = onResBean;
    	resBeanOnIO = nextOnIO;
        nextOnIO = false;
        return this;
    }

	/**
	 * 设置请求得到响应后的回调函数
	 * @param <T> 泛型
	 * @param type 期望的转换类型
	 * @param onResBean 响应 Bean 回调
	 * @return HttpTask 实例
	 */
	public <T> AsyncHttpTask setOnResBean(TypeRef<T> type, OnCallback<T> onResBean) {
		initBeanType(type.getType());
		this.onResBean = onResBean;
		resBeanOnIO = nextOnIO;
		nextOnIO = false;
		return this;
	}

	/**
	 * 设置请求得到响应后的回调函数
	 * @param <T> 泛型
	 * @param type 期望的转换类型
	 * @param onResList 请求响应回调
	 * @return HttpTask 实例
	 */
    public <T> AsyncHttpTask setOnResList(Class<T> type, OnCallback<List<T>> onResList) {
		if (type == null) {
			throw new IllegalArgumentException(" list type can not be null!");
		}
    	listType = type;
    	this.onResList = onResList;
    	resListOnIO = nextOnIO;
        nextOnIO = false;
        return this;
    }
    

    
	/**
	 * 设置请求得到响应后的回调函数
	 * @param onResMapper 请求响应回调
	 * @return HttpTask 实例
	 */
    public AsyncHttpTask setOnResMapper(OnCallback<Mapper> onResMapper) {
    	this.onResMapper = onResMapper;
    	resMapperOnIO = nextOnIO;
        nextOnIO = false;
        return this;
    }
    
	/**
	 * 设置请求得到响应后的回调函数
	 * @param onResArray 请求响应回调
	 * @return HttpTask 实例
	 */
    public AsyncHttpTask setOnResArray(OnCallback<Array> onResArray) {
    	this.onResArray = onResArray;
    	resArrayOnIO = nextOnIO;
        nextOnIO = false;
        return this;
    }
    
	/**
	 * 设置请求得到响应后的回调函数
	 * @param onResString 请求响应回调
	 * @return HttpTask 实例
	 */
    public AsyncHttpTask setOnResString(OnCallback<String> onResString) {
    	this.onResString = onResString;
    	resStringOnIO = nextOnIO;
        nextOnIO = false;
        return this;
    }
    
    /**
     * 发起 GET 请求（Rest：读取资源，幂等）
     * @return HttpCall
     */
    public HttpCall get() {
        return request(HTTP.GET);
    }

	/**
	 * 发起 HEAD 请求（Rest：读取资源头信息，幂等）
	 * @return HttpCall
	 */
	public HttpCall head() {
		return request(HTTP.HEAD);
	}

    /**
     * 发起 POST 请求（Rest：创建资源，非幂等）
     * @return HttpCall
     */
    public HttpCall post() {
        return request(HTTP.POST);
    }

    /**
     * 发起 PUT 请求（Rest：更新资源，幂等）
     * @return HttpCall
     */
    public HttpCall put() {
        return request(HTTP.PUT);
    }

	/**
	 * 发起 PATCH 请求（Rest：更新资源，部分更新，幂等）
	 * @return HttpCall
	 */
	public HttpCall patch() {
		return request(HTTP.PATCH);
	}

    /**
     * 发起 DELETE 请求（Rest：删除资源，幂等）
     * @return HttpCall
     */
    public HttpCall delete() {
        return request(HTTP.DELETE);
    }
    
    /**
     * 发起 HTTP 请求
     * @param method 请求方法
     * @return HttpCall
     */
    public HttpCall request(String method) {
    	if (method == null || method.isEmpty()) {
    		throw new IllegalArgumentException("HTTP 请求方法 method 不可为空！");
    	}
    	PreHttpCall call = new PreHttpCall();
		registeTagTask(call);
		//此处提交任务
    	httpClient.preprocess(this, () -> {
    		synchronized (call) {
    			if (call.canceled) {
					removeTagTask();
        		} else {
    				if (onResponse != null || onResBody != null) {
    					tag(CopyInterceptor.TAG);
					}
    				//发起请求，该executeCall方法中含有之前设置的IOException和OnResponse回调
					//setCall线程减1
					call.setCall(executeCall(prepareCall(method)));
				}
			}
    	}, skipPreproc, skipSerialPreproc);
    	return call;
    }
    
    
    class PreHttpCall implements HttpCall {

		HttpCall call;
		boolean canceled = false;
    	CountDownLatch latch = new CountDownLatch(1);

		@Override
		public synchronized boolean cancel() {
			canceled = call == null || call.cancel();
			latch.countDown();
			return canceled;
		}

		@Override
		public boolean isDone() {
			if (call != null) {
				return call.isDone();
			}
			return canceled;
		}

		@Override
		public boolean isCanceled() {
			return canceled;
		}

		void setCall(HttpCall call) {
			this.call = call;
			latch.countDown();
		}

		@Override
		public HttpResult getResult() {
			if (!timeoutAwait(latch)) {
				cancel();
				return timeoutResult();
			}
			if (canceled || call == null) {
				return new RealHttpResult(AsyncHttpTask.this, State.CANCELED);
			}
			return call.getResult();
		}

		@Override
		public AsyncHttpTask getTask() {
			return AsyncHttpTask.this;
		}

    }

    class OkHttpCall implements HttpCall {

		Call call;
		HttpResult result;
		CountDownLatch latch = new CountDownLatch(1);

		OkHttpCall(Call call) {
			this.call = call;
		}

		@Override
		public synchronized boolean cancel() {
			if (result == null) {
				call.cancel();
				return true;
			}
			return false;
		}

		@Override
		public boolean isDone() {
			return result != null;
		}

		@Override
		public boolean isCanceled() {
			return call.isCanceled();
		}

		@Override
		public HttpResult getResult() {
			if (result == null) {
				if (!timeoutAwait(latch)) {
					cancel();
					return timeoutResult();
				}
			}
			return result;
		}

		@Override
		public AsyncHttpTask getTask() {
			return AsyncHttpTask.this;
		}

		void setResult(HttpResult result) {
			this.result = result;
			latch.countDown();
		}

    }

	
    private HttpCall executeCall(Call call) {
        OkHttpCall httpCall = new OkHttpCall(call);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException error) {
				State state = toState(error);
				HttpResult result = new RealHttpResult(AsyncHttpTask.this, state, error);
				onCallback(httpCall, result, () -> {
					TaskExecutor executor = httpClient.executor();
					executor.executeOnComplete(AsyncHttpTask.this, onComplete, state, completeOnIO);
					if (!executor.executeOnException(AsyncHttpTask.this, onException, error, exceptionOnIO)
							&& !nothrow) {
						throw new HttpException(state, error.getMessage(), error);
					}
				});
            }

            @Override
            public void onResponse(Call call, Response response) {
            	TaskExecutor executor = httpClient.executor();
				HttpResult result = new RealHttpResult(AsyncHttpTask.this, response, executor);
				onCallback(httpCall, result, () -> {
					executor.executeOnComplete(AsyncHttpTask.this, onComplete, State.RESPONSED, completeOnIO);
					executor.executeOnResponse(AsyncHttpTask.this, complexOnResponse(), result, true);
				});
            }

        });
		return httpCall;
    }

    private OnCallback<HttpResult> complexOnResponse() {
		return res -> {
			int count = responseCallbackCount();
			HttpResult.Body body = res.getBody();
			if (count > 1) {
				body.cache();
			}
			if (onResponse != null) {
				execute(() -> onResponse.on(res), responseOnIO);
			}
			if (onResBody != null) {
				execute(() -> onResBody.on(body), resBodyOnIO);
			}
			if (onResMapper != null) {
				Mapper mapper = body.toMapper();
				execute(() -> onResMapper.on(mapper), resMapperOnIO);
			}
			if (onResArray != null) {
				Array array = body.toArray();
				execute(() -> onResArray.on(array), resArrayOnIO);
			}
			if (onResBean != null) {
				Object bean = body.toBean(beanType);
				execute(() -> {
					try {
						callbackMethod(onResBean.getClass(), bean.getClass()).invoke(onResBean, bean);
					} catch (IllegalAccessException | InvocationTargetException e) {
						throw new HttpException("回调方法调用失败！", e);
					}
				}, resBeanOnIO);
			}
			if (onResList != null) {
				List<?> list = body.toList(listType);
				execute(() -> {
					try {
						callbackMethod(onResList.getClass(), list.getClass()).invoke(onResList, list);
					} catch (IllegalAccessException | InvocationTargetException e) {
						throw new HttpException("回调方法调用失败！", e);
					}
				}, resListOnIO);
			}
			if (onResString != null) {
				String string = body.toString();
				execute(() -> onResString.on(string), resStringOnIO);
			}
		};
	}

	static final String OnCallbackMethod = OnCallback.class.getDeclaredMethods()[0].getName();

	private Method callbackMethod(Class<?> clazz, Class<?> paraType) {
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			Class<?>[] paraTypes = method.getParameterTypes();
			if (method.getName().equals(OnCallbackMethod) && paraTypes.length == 1
					&& paraTypes[0].isAssignableFrom(paraType)) {
				method.setAccessible(true);
				return method;
			}
		}
		throw new IllegalStateException("没有可调用的方法");
	}

	private int responseCallbackCount() {
    	int count = 0;
		if (onResponse != null)
			count++;
		if (onResBody != null)
			count++;
		if (onResMapper != null)
			count++;
		if (onResArray != null)
			count++;
		if (onResBean != null)
			count++;
		if (onResList != null)
			count++;
		if (onResString != null)
			count++;
		return count;
	}

	@SuppressWarnings("all")
    private void onCallback(OkHttpCall httpCall, HttpResult result, Runnable runnable) {
		synchronized (httpCall) {
			removeTagTask();
			if (httpCall.isCanceled() || result.getState() == State.CANCELED) {
				httpCall.setResult(new RealHttpResult(AsyncHttpTask.this, State.CANCELED));
				return;
			}
			httpCall.setResult(result);
			runnable.run();
		}
	}
	
    private void initBeanType(Type type) {
    	if (type == null) {
    		throw new IllegalArgumentException(" bean type can not be null!");
    	}
    	if (beanType != null) {
    		throw new IllegalStateException("已经添加了 OnResBean 回调！");
    	}
    	beanType = type;
    }

}
