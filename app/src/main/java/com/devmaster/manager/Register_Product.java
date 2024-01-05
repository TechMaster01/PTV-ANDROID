package com.devmaster.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.devmaster.manager.NETWORK.APIClient;
import com.devmaster.manager.NETWORK.APIProductos;
import com.devmaster.manager.NETWORK.Respuesta;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register_Product extends AppCompatActivity {

    EditText EdtBarcode;
    EditText EdtNombre;
    EditText EdtDescripcion;
    EditText EdtStock;
    EditText EdtPrecio;
    EditText EdtProveedor;
    Button BtnScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_product);

        EdtBarcode = findViewById(R.id.EdtIdProducto);
        EdtNombre = findViewById(R.id.EdtNombreProducto);
        EdtDescripcion = findViewById(R.id.EdtDescripcionProducto);
        EdtStock = findViewById(R.id.EdtStockProducto);
        EdtPrecio = findViewById(R.id.EdtPrecioProducto);
        EdtProveedor = findViewById(R.id.EdtIdProveedor);

        BtnScan = findViewById(R.id.BtnScan);

        Button RegistrarProducto = findViewById(R.id.BtnRegistrar);

        RegistrarProducto.setOnClickListener(v -> RegistrarNuevoProducto());

        BtnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator Scanner = new IntentIntegrator(Register_Product.this);
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

    private void RegistrarNuevoProducto(){
        String CodigoBarras = EdtBarcode.getText().toString();
        String NombreProducto = EdtNombre.getText().toString();
        String DescripcionProducto = EdtDescripcion.getText().toString();
        String StockProducto = EdtStock.getText().toString();
        String PrecioProducto = EdtPrecio.getText().toString();
        String Proveedor = EdtProveedor.getText().toString();

        if(CodigoBarras.isEmpty() || NombreProducto.isEmpty() || DescripcionProducto.isEmpty() || StockProducto.isEmpty() || PrecioProducto.isEmpty() || Proveedor.isEmpty()) {
            showToast("Complete todos los campos.");
            return;
        }

        APIProductos ApiRegisterProduct = APIClient.getClient().create(APIProductos.class);

        Call<Respuesta> call = ApiRegisterProduct.RegistrarProducto(CodigoBarras, NombreProducto, DescripcionProducto, StockProducto, PrecioProducto, Proveedor);

        call.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                if(response.isSuccessful()){
                    showToast("Producto registrado con exito");
                }else{
                    showToast("El producto ya esta registrado");
                }
            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                showToast("Error de conexion");
            }
        });
    }

    private void showToast(final String Texto) {
        Toast.makeText(Register_Product.this, Texto, Toast.LENGTH_SHORT).show();
    }

    //Prueba Git
    //Cuarto Commit
    //Quinto Commit
    //Sexto commit
    //Septimo Commit
}