package com.devmaster.manager.ADAPTERS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.devmaster.manager.MODELS.PRODUCTOS;
import com.devmaster.manager.R;

import java.util.List;

public class ProductosADAPTER extends RecyclerView.Adapter<ProductosADAPTER.ViewHolder> {
    private List<PRODUCTOS> Productos;
    private Context context;

    public ProductosADAPTER(List<PRODUCTOS> productos, Context context) {
        Productos = productos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_productos,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_nombre_producto.setText(Productos.get(position).getNOMBRE_PRODUCTO());
        holder.tv_descripcion_producto.setText(Productos.get(position).getDESCRIPCION());
        holder.tv_stock_producto.setText(Productos.get(position).getSTOCK());
    }

    @Override
    public int getItemCount() {
        return Productos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_nombre_producto;
        private TextView tv_descripcion_producto;
        private TextView tv_stock_producto;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nombre_producto = itemView.findViewById(R.id.TxtNombreProductoItem);
            tv_descripcion_producto = itemView.findViewById(R.id.TxtDescripcionItem);
            tv_stock_producto = itemView.findViewById(R.id.TxtStockItem);
        }
    }
}
