package br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.domain.model;

import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.domain.exception.InvalidCpfException;

public final class Cpf {
    private final String value;

    public String getValue() {
        return value;
    }
    public Cpf(String value) {
        if (!isValid(value)) {
            throw new InvalidCpfException();
        }
        this.value = normalize(value);
    }

    private boolean isValid(String cpf) {
        if (cpf == null) {
            return false;
        }

        cpf = normalize(cpf);
        int sum = 0;
        int weight = 10;

        for (int i = 0; i < cpf.length() - 1; i++) {
            sum += Integer.parseInt(String.valueOf(cpf.charAt(i))) * weight--;
        }
        int digit1 = (sum * 10) % 11;

        sum = 0;
        weight = 11;

        for (int i = 0; i < cpf.length() - 1; i++) {
            sum += Integer.parseInt(String.valueOf(cpf.charAt(i))) * weight--;
        }
        int digit2 = (sum * 10) % 11;

        return digit1 == Integer.parseInt(String.valueOf(cpf.charAt(9)))
                && digit2 == Integer.parseInt(String.valueOf(cpf.charAt(10)));

    }

    private String normalize(String cpf) {
        return cpf.replaceAll("\\D", "");
    }

}
