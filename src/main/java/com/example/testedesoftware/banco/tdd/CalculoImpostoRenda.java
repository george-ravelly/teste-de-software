package com.example.testedesoftware.banco.tdd;


public class CalculoImpostoRenda {

    /**
     * Este método calcula o imposto de renda a ser pago com base no valor da renda anual.
     *
     * Se 0 < renda <= 22847,76 deve pagar 0% de imposto
     * Se 22847,77 <= renda <= 33919,80 deve pagar 7,5% de imposto
     * Se 33919,81 <= renda <= 45012,60 deve pagar 15% de imposto
     * Se 45012,61 <= renda <= 55976,16 deve pagar 22,5% de imposto
     * Se renda > 55976,16 deve pagar 27,5% de imposto
     *
     * @param renda - renda anual para calcular o imposto de renda.
     */
    public static void calculaImposto(double renda) {
        double imposto;

        if (renda <= 22847.76) {
            imposto = 0;
        } else if (renda <= 33919.80) {
            imposto = (renda - 22847.76) * 0.075;
        } else if (renda <= 45012.60) {
            imposto = (renda - 33919.80) * 0.15 + 1100.43;
        } else if (renda <= 55976.16) {
            imposto = (renda - 45012.60) * 0.225 + 2826.21;
        } else {
            imposto = (renda - 55976.16) * 0.275 + 6172.13;
        }

        // Saída de dados
        System.out.println("Imposto de Renda a ser pago: R$" + imposto);
    }

    public static void main(String[] args) {
        calculaImposto(30000); // Exemplo de chamada com uma renda anual de 30.000 reais.
    }
}
