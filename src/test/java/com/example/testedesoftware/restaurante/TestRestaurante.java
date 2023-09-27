package com.example.testedesoftware.restaurante;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
public class TestRestaurante {
    private Restaurante restaurante;

    @Before
    public void setUp() {
        restaurante = new Restaurante("Sabores", "Bela vista");
    }

    /*
     * executado apos cada metodo de teste
     */
    public void tearDown() {}

    @Test
    public void testAdicionarPrato() {
        Prato prato = new Prato("Pizza", 15.99);
        restaurante.adicionarPrato(prato);

        assertTrue(restaurante.getMenu().contains(prato));
    }

    @Test
    public void testRemoverPrato() {
        Prato prato1 = new Prato("Bife à Parmegiana", 19.99);
        Prato prato2 = new Prato("Salada Caesar", 8.99);

        restaurante.adicionarPrato(prato1);
        restaurante.adicionarPrato(prato2);

        restaurante.removerPrato(prato1);

        assertFalse(restaurante.getMenu().contains(prato1));
        assertTrue(restaurante.getMenu().contains(prato2));
    }

    @Test
    public void testExibirInformacoes() {
        Prato prato1 = new Prato("Sushi", 22.99);
        Prato prato2 = new Prato("Hambúrguer", 10.99);
        restaurante.adicionarPrato(prato1);
        restaurante.adicionarPrato(prato2);

        String expectedOutput = "Restaurante: Sabores\n" +
                "Endereço: Bela vista\n" +
                "Menu:\n" +
                "\tSushi - R$22.99\n" +
                "\tHambúrguer - R$10.99";

        assertEquals(expectedOutput, restaurante.exibirInformacoes());
    }
}
