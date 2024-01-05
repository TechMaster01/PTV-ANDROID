package com.devmaster.manager.NETWORK;

import com.devmaster.manager.MODELS.PRODUCTOS;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIProductos {
    @GET("productos")
    Call<List<PRODUCTOS>> getProductos();

    @GET("productos/{ID_PRODUCTO}")
    Call<List<PRODUCTOS>> getProductosById(@Path("ID_PRODUCTO") String ID_PRODUCTO);

    @FormUrlEncoded
    @POST("productos")
    Call<Respuesta> RegistrarProducto(
            @Field("ID_PRODUCTO") String ID_PRODUCTO,
            @Field("NOMBRE_PRODUCTO") String NOMBRE_PROCDUTO,
            @Field("DESCRIPCION") String DESCRIPCION,
            @Field("STOCK") String STOCK,
            @Field("PRECIO") String PRECIO,
            @Field("ID_PROVEEDOR") String ID_PROVEEDOR
    );
}
