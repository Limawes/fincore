# Regras de Negócio - Customer Service

## 1. Cadastro de Clientes
- **CPF**: Deve ser um CPF válido e único na base. O CPF nunca é salvo na forma original em texto limpo; é armazenado seu hash seguro (`cpfHash`) e os últimos 4 dígitos (`cpfLast4`).
- **E-mail**: Deve ser um e-mail válido e único na base.
- **Nome**: Deve ser preenchido (máximo 160 caracteres).
- **Status Inicial**: Todo cliente inicia no status `ACTIVE`.

## 2. Bloqueio e Movimentação
- Clientes no status `BLOCKED` continuam existentes na base, mas não devem ter permissão para abrir novas contas ou movimentar saldo.

## 3. Privacidade e Proteção de Dados (LGPD)
- O CPF completo nunca deve ser retornado via REST/API e nem registrado nos logs do sistema.
