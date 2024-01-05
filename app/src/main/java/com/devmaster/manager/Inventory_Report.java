package com.devmaster.manager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.devmaster.manager.ADAPTERS.ProductosADAPTER;
import com.devmaster.manager.MODELS.PRODUCTOS;
import com.devmaster.manager.NETWORK.APIClient;
import com.devmaster.manager.NETWORK.APIProductos;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Inventory_Report extends AppCompatActivity {

    private List<PRODUCTOS> Productos;
    private RecyclerView recyclerView;
    private ProductosADAPTER ProductosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_report);
        recyclerView=findViewById(R.id.rv_inventario);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        showProductos();
    }

    public void showProductos(){
        Call<List<PRODUCTOS>> call= APIClient.getClient().create(APIProductos.class).getProductos();
        call.enqueue(new Callback<List<PRODUCTOS>>() {
            @Override
            public void onResponse(Call<List<PRODUCTOS>> call, Response<List<PRODUCTOS>> response) {
                if(response.isSuccessful()){
                    Productos=response.body();
                    ProductosAdapter= new ProductosADAPTER(Productos, getApplicationContext());
                    recyclerView.setAdapter(ProductosAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<PRODUCTOS>> call, Throwable t) {
                Toast.makeText(Inventory_Report.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}