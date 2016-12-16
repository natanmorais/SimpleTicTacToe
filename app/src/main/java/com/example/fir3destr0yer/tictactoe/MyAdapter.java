package com.example.fir3destr0yer.tictactoe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by fir3destr0yer on 14/12/16.
 */
public class MyAdapter extends RecyclerView.Adapter {

    public ArrayList<Icones> icons;
    public ArrayList<IconViewHolder> iconsHolder = new ArrayList<>();
    public Context context;
    public ChooseIcons ci;

    public MyAdapter(ArrayList<Icones> icons, Context context, ChooseIcons cicons){
        this.icons = icons;
        this.context = context;
        ci = cicons;
    }

    public void setSelecionado(String icone) {
        for(IconViewHolder ivh: iconsHolder){
            if(!ivh.getTitulo().getText().toString().equals(icone)){
                ivh.setSelecionado(false);
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.icons_cardview,parent,false);
        IconViewHolder ivh = new IconViewHolder(view, context, ci, this);
        iconsHolder.add(ivh);
        return ivh;
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        IconViewHolder ivh = (IconViewHolder) viewHolder;
        Icones i = icons.get(position);
        ivh.setImgO(i.getImgO(),context);
        ivh.setImgX(i.getImgX(),context);
        ivh.setTitulo(i.getNome());
        ivh.setDescricao(i.getValor());
        ivh.setSelecionado(i.isSelecionado());
    }

    @Override
    public int getItemCount() {
        return this.icons.size();
    }
}
