package com.example.testedesoftware.tdd;

import com.example.testedesoftware.banco.Conta;

import java.util.ArrayList;
import java.util.List;

public class ContaCorrente {
    private String id;
    private String nome;
    private int saldo;
    private List<Deposito> historico = new ArrayList<>();

    public ContaCorrente() {
    }

    public ContaCorrente(int valor) {
        this.saldo = valor;
    }

    public ContaCorrente (String nome) {
        this.nome = nome;
    }

    public ContaCorrente (String nome, int saldo) {
        this.saldo = saldo;
        this.nome = nome;
        historico.add(new Deposito("", saldo));
    }

    public void creditar(Deposito deposito) {
        historico.add(deposito);
        setSaldo((getSaldo()+deposito.valor()));
    }

    public String extrato() {
        return String.format("Conta de %s\n" +
                "Saldo Inicial R$ %d\n" +
                "Saldo Final R$ %d\n" +
                "%s",
                getNome(),
                historico.isEmpty() ? 0 : historico.get(0).valor(),
                getSaldo(),
                getHistorico()
        );
    }

    public String getHistorico() {
        if (historico.size() < 2) return "Nenhuma trasacao realizada\n";
        StringBuilder historicoToString = new StringBuilder();
        for (int i = 1; i < historico.size(); i++) {
            historicoToString.append(historico.get(i).toString());
            historicoToString.append("\n");
        }
        return historicoToString.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

