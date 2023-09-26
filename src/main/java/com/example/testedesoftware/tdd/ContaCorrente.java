package com.example.testedesoftware.tdd;

import com.example.testedesoftware.banco.OperacaoIlegalException;

import java.util.ArrayList;
import java.util.List;

public class ContaCorrente {
    private String id;
    private String nome;
    private double saldo;
    private List<Historico> historico = new ArrayList<>();

    public ContaCorrente() {
    }

    public ContaCorrente(double valor) {
        this.saldo = valor;
    }

    public ContaCorrente (String nome) {
        this.nome = nome;
    }

    public ContaCorrente (String nome, double saldo) {
        this.saldo = saldo;
        this.nome = nome;
        historico.add(new Historico("", "Deposito", saldo));
    }

    public void creditar(Deposito deposito) throws OperacaoIlegalException {
        if (deposito.valor() < 0) throw new OperacaoIlegalException();

        historico.add((new Historico(deposito.data(), "Deposito", deposito.valor())));
        setSaldo((getSaldo()+deposito.valor()));
    }

    public double debitar(Deposito debito) throws OperacaoIlegalException {
        if (debito.valor() > getSaldo() || debito.valor() <= 0) throw new OperacaoIlegalException();

        historico.add((new Historico(debito.data(), "Retirada", debito.valor())));
        setSaldo((getSaldo()-debito.valor()));
        return getSaldo();
    }

    public void transferir (ContaCorrente destino, double valor) throws OperacaoIlegalException {
        debitar(new Deposito("2015-03-14", valor));
        destino.creditar(new Deposito("2015-03-14", valor));
    }

    public String extrato() {
        return String.format("Conta de %s\n" +
                "Saldo Inicial R$ %.2f\n" +
                "Saldo Final R$ %.2f\n" +
                "%s",
                getNome(),
                historico.isEmpty() ? 0 : historico.get(0).getValor(),
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

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

