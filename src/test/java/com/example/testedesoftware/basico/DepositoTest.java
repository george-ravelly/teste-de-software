package com.example.testedesoftware.basico;

import com.example.testedesoftware.tdd.Deposito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepositoTest {
    public void testCreate() {
        String depositDate = "2015-03-13";
        int valor = 3000;

        Deposito d = new Deposito(depositDate, valor);
        assertEquals(depositDate, d.data());
    }
}
