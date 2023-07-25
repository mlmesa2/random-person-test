package com.test.randomuser.data.network;

import static com.test.randomuser.Util.TAG;
import android.util.Log;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TestingInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        switch (response.code()) {
            case 400 : {
                Log.d(TAG, "intercept: Error 400");
            }
            case 401 : {
                Log.d(TAG, "intercept: Error 401");
            }

            case 403 : {
                Log.d(TAG, "intercept: Error 403");
            }

            case 404 : {
                Log.d(TAG, "intercept: Error 404");
            }
            default: {
                Log.d(TAG, "intercept:" + response);
            }

        }
        assert response.body() != null;
        return response;
    }
}
