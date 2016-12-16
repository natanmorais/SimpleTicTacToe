package com.example.fir3destr0yer.tictactoe;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

/**
 * Created by fir3destr0yer on 14/12/16.
 */
public class IconViewHolder extends RecyclerView.ViewHolder {

    ImageView imgO, imgX;
    TextView titulo, descricao, selecionado;
    View v;
    Context context;
    ChooseIcons cIcons;
    MyAdapter adapter;

    public IconViewHolder(View itemView, Context context, ChooseIcons ci, final MyAdapter adapter) {
        super(itemView);
        v = itemView;
        this.cIcons = ci;
        this.adapter = adapter;
        imgO = (ImageView) itemView.findViewById(R.id.Icon_O);
        imgX = (ImageView) itemView.findViewById(R.id.Icon_X);
        titulo = (TextView) itemView.findViewById(R.id.txtTitulo);
        descricao = (TextView) itemView.findViewById(R.id.txtDescricao);
        selecionado = (TextView) itemView.findViewById(R.id.txtSelecionado);
        this.context = context;
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Cardview", titulo.getText().toString());
                String value = descricao.getText().toString();
                if (value.equals("--")) {
                    setSelecionado(true);
                    adapter.setSelecionado(titulo.getText().toString());
                    selecionaLogo(titulo.getText().toString());
                } else {
                    Integer valor = Integer.parseInt(value);
                    compraLogo(valor);
                    cIcons.atualiza();
                }
            }
        });
    }

    public void compraLogo(int valor) {
        SharedPreferences settings = context.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        int coins = settings.getInt("Coins", 0);
        SharedPreferences.Editor editor = settings.edit();
        if (coins >= valor) {
            editor.putInt("Coins", coins - valor);
            editor.putInt(getTitulo().getText().toString(), 0);
            editor.commit();
            this.setSelecionado(true);
            this.setDescricao(0);
            adapter.setSelecionado(getTitulo().getText().toString());
            selecionaLogo(getTitulo().getText().toString());
        }
    }

    public void selecionaLogo(String logo){
        SharedPreferences settings = context.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("Selected",logo);
        editor.commit();
    }

    public ImageView getImgO() {
        return imgO;
    }

    public void setImgO(int i, Context ct) {
        Drawable d = ct.getResources().getDrawable(i);
        this.imgO.setImageDrawable(d);
    }

    public ImageView getImgX() {
        return imgX;
    }

    public void setImgX(int i, Context ct) {
        Drawable d = ct.getResources().getDrawable(i);
        this.imgX.setImageDrawable(d);
    }

    public TextView getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo.setText(titulo);
    }

    public TextView getDescricao() {
        return descricao;
    }

    public void setDescricao(int valor) {
        if (valor == 0) {
            this.descricao.setText("--");
        } else {
            this.descricao.setText(valor + "");
        }
    }

    public TextView getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        if (selecionado) {
            this.selecionado.setText("SELECTED");
        } else {
            this.selecionado.setText("");
        }
    }
}
