# Visão Geral do Banco de Dados - FinCore

## Tabela `customers` (Customer Service)

| Coluna | Tipo | Constraints | Descrição |
|---|---|---|---|
| `id` | UUID | PK | Identificador único do cliente |
| `cpf_hash` | VARCHAR(128) | UNIQUE NOT NULL | Hash do CPF para busca e unicidade |
| `cpf_last4` | VARCHAR(4) | | Últimos 4 dígitos para mascaramento |
| `full_name` | VARCHAR(160) | NOT NULL | Nome completo |
| `email` | VARCHAR(254) | UNIQUE NOT NULL | E-mail corporativo/pessoal |
| `status` | VARCHAR(30) | NOT NULL | Status (`ACTIVE`, `BLOCKED`) |
| `created_at` | TIMESTAMPTZ | NOT NULL | Timestamp de criação |
| `updated_at` | TIMESTAMPTZ | NOT NULL | Timestamp de atualização |
| `version` | BIGINT | NOT NULL | Controle de concorrência otimista |
