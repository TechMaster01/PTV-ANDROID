package com.devmaster.manager.MODELS;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PRODUCTOS implements Serializable {

    @SerializedName("ID_PRODUCTO")
    private String ID_PRODUCTO;
    @SerializedName("NOMBRE_PRODUCTO")
    private String NOMBRE_PRODUCTO;
    @SerializedName("DESCRIPCION")
    private String DESCRIPCION;
    @SerializedName("STOCK")
    private String STOCK;
    @SerializedName("PRECIO")
    private String PRECIO;
    @SerializedName("ID_PROVEEDOR")
    private String ID_PROVEEDOR;

    public String getID_PRODUCTO() {
        return ID_PRODUCTO;
    }

    public void setID_PRODUCTO(String ID_PRODUCTO) {
        this.ID_PRODUCTO = ID_PRODUCTO;
    }

    public String getNOMBRE_PRODUCTO() {
        return NOMBRE_PRODUCTO;
    }

    public void setNOMBRE_PRODUCTO(String NOMBRE_PRODUCTO) {
        this.NOMBRE_PRODUCTO = NOMBRE_PRODUCTO;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public String getSTOCK() {
        return STOCK;
    }

    public void setSTOCK(String STOCK) {
        this.STOCK = STOCK;
    }

    public String getPRECIO() {
        return PRECIO;
    }

    public void setPRECIO(String PRECIO) {
        this.PRECIO = PRECIO;
    }

    public String getID_PROVEEDOR() {
        return ID_PROVEEDOR;
    }

    public void setID_PROVEEDOR(String ID_PROVEEDOR) {
        this.ID_PROVEEDOR = ID_PROVEEDOR;
    }
}
