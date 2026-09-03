package br.com.fincore.services.customer_service.src.test.java.com.fincore.customer.domain.model;

import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.domain.exception.InvalidCpfException;
import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.domain.model.Cpf;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

public class CpfTest {

    @Test
    public void shouldAcceptValidCpf() {
        Cpf cpf = new Cpf("52998224725");

        assertNotNull(cpf.getHash());
        assertEquals(64, cpf.getHash().length());
        assertEquals("52998224725", cpf.getValue());
        assertEquals("4725", cpf.getLast4Digits());
    }

    @Test
    public void shouldNormalizeFormattedCpf() {
        Cpf cpf = new Cpf("529.982.247-25");

        assertEquals("52998224725", cpf.getValue());
        assertEquals("4725", cpf.getLast4Digits());
    }

    @Test
    public void shouldRejectInvalidCpf() {
        assertThrows(InvalidCpfException.class, () -> new Cpf("11111111111"));
        assertThrows(InvalidCpfException.class, () -> new Cpf("12345678900"));
        assertThrows(InvalidCpfException.class, () -> new Cpf(null));
    }
}
