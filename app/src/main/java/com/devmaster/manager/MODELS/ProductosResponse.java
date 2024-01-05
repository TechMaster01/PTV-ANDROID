package com.devmaster.manager.MODELS;

import com.google.gson.annotations.SerializedName;

public class ProductosResponse {
    @SerializedName("productos")
    private PRODUCTOS Productos;

    public PRODUCTOS getProductos(){
        return Productos;
    }
}
