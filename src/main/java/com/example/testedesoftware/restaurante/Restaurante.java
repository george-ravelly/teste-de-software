package com.example.testedesoftware.restaurante;
import java.util.ArrayList;
import java.util.List;

public class Restaurante {

    private String nome;
    private String endereco;
    private List<Prato> menu;

    public Restaurante(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.menu = new ArrayList<>();
    }

    // Método para adicionar um prato ao menu
    public void adicionarPrato(Prato prato) {
        menu.add(prato);
    }

    // Método para remover um prato do menu
    public void removerPrato(Prato prato) {
        menu.remove(prato);
    }

    // Método para obter o menu do restaurante
    public List<Prato> getMenu() {
        return menu;
    }

    // Método para exibir informações sobre o restaurante
    public String exibirInformacoes() {
        StringBuilder output = new StringBuilder(String.format("Restaurante: %s\nEndereço: %s\nMenu:", nome, endereco));
        for (Prato prato : menu) {
            output.append(String.format("\n\t%s", prato.getNome() + " - R$" + prato.getPreco()));
        }
        System.out.println(output);
        return output.toString();
    }

    @Override
    public String toString() {
        return "Restaurante{" +
                "nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", menu=" + menu +
                '}';
    }
}
