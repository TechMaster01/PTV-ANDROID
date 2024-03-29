package com.devmaster.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.devmaster.manager.MODELS.PRODUCTOS;
import com.devmaster.manager.NETWORK.APIClient;
import com.devmaster.manager.NETWORK.APIProductos;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Products_Report extends AppCompatActivity {
    public static APIProductos apiProductos;

    EditText EdtBarcode;
    TextView Nombre_Producto;
    TextView Precio_Producto;
    TextView Descripcion_Producto;
    TextView Stock_Producto;
    Button BtnScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_report);

        apiProductos = APIClient.getClient().create(APIProductos.class);

        Button BuscarProducto = findViewById(R.id.BtnBuscar);

        Nombre_Producto = findViewById(R.id.TxtProducto);
        Descripcion_Producto = findViewById(R.id.TxtDescripcion);
        Precio_Producto = findViewById(R.id.TxtPrecio);
        Stock_Producto = findViewById(R.id.TxtStock);
        EdtBarcode = findViewById(R.id.EdtCodigo);
        BtnScan = findViewById(R.id.BtnScan);

        //BuscarProducto.setOnClickListener(v -> Imprimir(""));

        BuscarProducto.setOnClickListener(v -> ObtenerDatosProducto(EdtBarcode.getText().toString()));

        BtnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator Scanner = new IntentIntegrator(Products_Report.this);
                Scanner.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                Scanner.setPrompt("Lector - CDP");
                Scanner.setCameraId(0);
                Scanner.setBarcodeImageEnabled(true);
                Scanner.initiateScan();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null){
            if (result.getContents() == null){
                Toast.makeText(this, "Lectura Cancelada", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(this, result.getContents(), Toast.LENGTH_SHORT).show();
                EdtBarcode.setText(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void Imprimir(String codigo){
        Toast.makeText(this, "El codigo: " + EdtBarcode.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    public void ObtenerDatosProducto(String ID_PRODUCTO){
        Call<List<PRODUCTOS>> ObtenerProducto = apiProductos.getProductosById(ID_PRODUCTO);
        Log.d("ID Producto", ID_PRODUCTO);
        ObtenerProducto.enqueue(new Callback<List<PRODUCTOS>>() {
            @Override
            public void onResponse(Call<List<PRODUCTOS>> call, Response<List<PRODUCTOS>> response) {
                Log.d("RespuestaServidor", "Json: " + response);
                if(response.body() != null){
                    PRODUCTOS Producto = response.body().get(0);
                    Nombre_Producto.setText(Producto.getNOMBRE_PRODUCTO() != null ? "Nombre: " + Producto.getNOMBRE_PRODUCTO() : "N/A");
                    Descripcion_Producto.setText(Producto.getDESCRIPCION() != null ? "Descripción: " + Producto.getDESCRIPCION() : "N/A");
                    Precio_Producto.setText(Producto.getPRECIO() != null ? "Precio: $" + Producto.getPRECIO() : "N/A");
                    Stock_Producto.setText(Producto.getSTOCK() != null ? "Stock: " + Producto.getSTOCK() : "N/A");
                }else{
                    Toast.makeText(Products_Report.this, "El producto no existe", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<PRODUCTOS>> call, Throwable t) {
                Log.e("RespuestaServidor", "Error de conexion: " + t.getMessage());
                Toast.makeText(Products_Report.this, "Respuesta no exitosa", Toast.LENGTH_SHORT).show();
            }
        });
    }
}