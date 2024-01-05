package com.devmaster.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main_Options extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_options);

        Button BtnRegister = findViewById(R.id.BtnAddProduct);
        BtnRegister.setOnClickListener(v -> OpenRegisterProduct());

        Button BtnReporteProductos = findViewById(R.id.BtnProductsReport);
        BtnReporteProductos.setOnClickListener(v -> OpenProductReport());

        Button BtnReporteVentas = findViewById(R.id.BtnSalesReport);
        BtnReporteVentas.setOnClickListener(v -> OpenSalesReport());

        Button BtnReporteInventario = findViewById(R.id.BtnInventoryReport);
        BtnReporteInventario.setOnClickListener(v -> OpenInventoryReport());
    }

    private void OpenProductReport(){
        Intent i = new Intent(Main_Options.this, Products_Report.class);
        startActivity(i);
    }

    private void OpenSalesReport(){
        Intent i = new Intent(Main_Options.this, Sales_Report.class);
        startActivity(i);
    }

    private void OpenInventoryReport(){
        Intent i = new Intent(Main_Options.this, Inventory_Report.class);
        startActivity(i);
    }

    private void OpenRegisterProduct(){
        Intent i = new Intent(Main_Options.this, Register_Product.class);
        startActivity(i);
    }
}