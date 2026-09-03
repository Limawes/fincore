package br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.domain.model;

import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.domain.exception.InvalidCpfException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public final class Cpf {
    private final String value;

    public Cpf(String value) {
        if (!isValid(value)) {
            throw new InvalidCpfException();
        }
        this.value = normalize(value);
    }

    public String getValue() {
        return value;
    }

    public String getLast4Digits() {
        if (value.length() >= 4) {
            return value.substring(value.length() - 4);
        }
        return value;
    }

    public String getHash() {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(value.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 algorithm not available", e);
        }
    }

    private boolean isValid(String cpf) {
        if (cpf == null) {
            return false;
        }

        cpf = normalize(cpf);
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int sum = 0;
        int weight = 10;

        for (int i = 0; i < 9; i++) {
            sum += Integer.parseInt(String.valueOf(cpf.charAt(i))) * weight--;
        }
        int digit1 = (sum * 10) % 11;
        if (digit1 == 10) {
            digit1 = 0;
        }

        sum = 0;
        weight = 11;

        for (int i = 0; i < 10; i++) {
            sum += Integer.parseInt(String.valueOf(cpf.charAt(i))) * weight--;
        }
        int digit2 = (sum * 10) % 11;
        if (digit2 == 10) {
            digit2 = 0;
        }

        return digit1 == Integer.parseInt(String.valueOf(cpf.charAt(9)))
                && digit2 == Integer.parseInt(String.valueOf(cpf.charAt(10)));

    }

    private String normalize(String cpf) {
        return cpf.replaceAll("\\D", "");
    }
}
