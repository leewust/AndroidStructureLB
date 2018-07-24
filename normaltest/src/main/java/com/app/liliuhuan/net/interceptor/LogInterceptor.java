package com.app.liliuhuan.net.interceptor;

import com.app.liliuhuan.util.LogUtil;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

public class LogInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        //这个chain里面包含了request和response，所以你要什么都可以从这里拿
        Request request = chain.request();

        long t1 = System.currentTimeMillis();//请求发起的时间
        StringBuilder sb1 = new StringBuilder();

        Response response = chain.proceed(request);
        long t2 = System.currentTimeMillis();//收到响应的时间
        //这里不能直接使用response.body().string()的方式输出日志
        //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
        //个新的response给应用层处理
        ResponseBody responseBody = response.peekBody(1024 * 1024);
        StringBuilder sb = new StringBuilder();
        sb1.append("------------------> 请求方式:" + request.method()).append("\n")
                .append("------------------> 请求时间:" + t1).append("\n")
                .append("------------------> 请求的URL:" + request.url()).append("\n")
//                .append("------------------> 请求的Headers:"+request.headers()).append("\n")
                .append("------------------> 请求的Json:" + bodyToString(request.body()));
        LogUtil.e("\n请求信息:\n" + sb1.toString());
        sb.append("------------------> 响应时间:" + t2).append("\n")
                .append("------------------> 响应的URL:" + response.request().url()).append("\n")
//                .append("------------------> 响应的Headers:"+response.headers()).append("\n")
                .append("------------------> 响应的Json:" + responseBody.string())
                .append("\n------------------>请求耗时:" + (t2 - t1));
        LogUtil.e("\n响应信息：\n" + sb.toString());
        return response;
    }

    /**
     * 读取请求中的json
     *
     * @param body
     * @return
     */
    private String bodyToString(RequestBody body) {
        try {
            if (body instanceof FormBody) {
                FormBody formBody = (FormBody) body;
                StringBuilder sb = new StringBuilder();
                int size = formBody.size();
                for (int i = 0; i < size; i++) {
                    if (i == size - 1) {
                        sb.append(formBody.name(i)).append("=").append(formBody.value(i));
                    } else {
                        sb.append(formBody.name(i)).append("=").append(formBody.value(i)).append("&");
                    }
                }
                return sb.toString();
            }
            RequestBody copy = body;
            Buffer buffer = new Buffer();
            if (copy != null) copy.writeTo(buffer);
            else return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }
}
