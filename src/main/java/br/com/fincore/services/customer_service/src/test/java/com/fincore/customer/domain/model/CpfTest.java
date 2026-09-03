package com.fincore.customer.domain.model;

import com.fincore.customer.domain.exception.InvalidCpfException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CpfTest {

    @Test
    void shouldAcceptValidCpf() {
        Cpf cpf = new Cpf("52998224725");
        assertNotNull(cpf.getHash());
        assertEquals("4725", cpf.getLast4Digits());
    }

    @Test
    void shouldRejectInvalidCpf() {
        assertThrows(InvalidCpfException.class, () -> new Cpf("11111111111"));
        assertThrows(InvalidCpfException.class, () -> new Cpf("12345678900"));
        assertThrows(InvalidCpfException.class, () -> new Cpf(null));
    }
}
