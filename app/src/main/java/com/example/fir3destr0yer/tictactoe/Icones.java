package com.example.fir3destr0yer.tictactoe;

import android.widget.ImageView;

/**
 * Created by fir3destr0yer on 14/12/16.
 */
public class Icones {
    int imgO, imgX;
    String nome;
    boolean selecionado, comprado;
    int valor;

    public Icones(int imgO, int imgX, String nome, boolean selecionado, boolean comprado, int valor) {
        this.imgO = imgO;
        this.imgX = imgX;
        this.nome = nome;
        this.selecionado = selecionado;
        this.comprado = comprado;
        this.valor = valor;
    }

    public int getImgO() {
        return imgO;
    }

    public void setImgO(int imgO) {
        this.imgO = imgO;
    }

    public int getImgX() {
        return imgX;
    }

    public void setImgX(int imgX) {
        this.imgX = imgX;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }

    public boolean isComprado() {
        return comprado;
    }

    public void setComprado(boolean comprado) {
        this.comprado = comprado;
        setValor(0);
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
