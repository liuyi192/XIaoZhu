package com.xiaozhu.common.http.exception;

import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.text.ParseException;

import retrofit2.HttpException;

/**
 * @说明 异常工具
 * @作者 liuyi
 * @时间 2018/4/10 10:34
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class ExceptionEngine {
    //未知错误
    public static final int UN_KNOWN_ERROR = 1000;
    //解析(服务器)数据错误
    public static final int ANALYTIC_SERVER_DATA_ERROR = 1001;
    //解析(客户端)数据错误
    public static final int ANALYTIC_CLIENT_DATA_ERROR = 1002;
    //网络连接错误
    public static final int CONNECT_ERROR = 1003;
    //网络连接超时
    public static final int TIME_OUT_ERROR = 1004;

    /**
     * 异常处理
     *
     * @param e
     * @return
     */
    public static ApiException handleException(Throwable e) {
        ApiException ex;
        if (e instanceof HttpException) {//HTTP错误
            HttpException httpExc = (HttpException) e;
            ex = new ApiException(e, httpExc.code());
            ex.setMsg(HttpError.getName(HttpError.HTTP_EXCEPTION.getType()));//均视为网络错误
            return ex;
        } else if (e instanceof ServerException) {//服务器返回的错误
            ServerException serverExc = (ServerException) e;
            ex = new ApiException(serverExc, serverExc.getCode());
            ex.setMsg(serverExc.getMsg());
            return ex;
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException || e instanceof MalformedJsonException) {  //解析数据错误
            ex = new ApiException(e, ANALYTIC_SERVER_DATA_ERROR);
            ex.setMsg(HttpError.getName(HttpError.ANALYTIC_SERVER_DATA_ERROR.getType()));
            return ex;
        } else if (e instanceof ConnectException) {//连接网络错误
            ex = new ApiException(e, CONNECT_ERROR);
            ex.setMsg(HttpError.getName(HttpError.CONNECT_ERROR.getType()));
            return ex;
        } else if (e instanceof SocketTimeoutException) {//网络超时
            ex = new ApiException(e, TIME_OUT_ERROR);
            ex.setMsg(HttpError.getName(HttpError.TIME_OUT_ERROR.getType()));
            return ex;
        } else {//未知错误
            ex = new ApiException(e, UN_KNOWN_ERROR);
            ex.setMsg(HttpError.getName(HttpError.UN_KNOWN_ERROR.getType()));
            return ex;
        }
    }
}
