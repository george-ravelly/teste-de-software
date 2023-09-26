package com.example.testedesoftware.tdd;

public class Deposito {

    String data = "Undefined";
    int valor = -1;

    public Deposito(String data, int valor) {
        this.data = data;
        this.valor = valor;
    }

    public int valor() {
        return valor;
    }

    public String data() {
        return data;
    }

    @Override
    public String toString() {
        return data()+"\tDeposito\tR$ "+valor();
    }
}
