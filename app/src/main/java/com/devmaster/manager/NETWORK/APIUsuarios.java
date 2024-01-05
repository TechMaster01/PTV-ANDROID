package com.devmaster.manager.NETWORK;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIUsuarios {

    @FormUrlEncoded
    @POST("login")
    Call<Respuesta> loginusuario(
            @Field("USUARIO") String usuario,
            @Field("PASSWORD") String password
    );
}
