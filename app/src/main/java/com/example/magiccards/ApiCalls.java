package com.example.magiccards;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCalls {


    static ApiCalls instance = null;

    private ApiCalls() {


    }
        private ApiCalls(int Type)
        {

        }

    public static ApiCalls getInstance() {
        if (instance == null) {
            instance = new ApiCalls();
        }
        return instance;
    }




    void login( CallbackListener listener3, int type)
    {
        Call<GetImagePojo> call3=RetrofitClient.getInstance().getapi().getimages(type);
        initiateCall(call3,listener3);

    }




    <T> void initiateCall(Call<T> call, final CallbackListener listener) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if(response.isSuccessful()){
                    listener.onSuccess(response.body());
                }else {
                    listener.onFailure("invalid credentials");
                }


            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {

                listener.onFailure(t.getLocalizedMessage());
            }
        });
    }


    public interface CallbackListener<T> {
        void onSuccess(T model);

        void onFailure(String message);


    }

}
