# ADR-002: Utilização do PostgreSQL e Flyway como Padrão de Persistência Relacional

## Status
Aceito

## Contexto
Operações financeiras exigem consistência ACID, integridade referencial forte, suporte a transações complexas, índices eficientes e controle de concorrência confiável.

## Decisão
1. Utilizar **PostgreSQL** como banco de dados relacional principal.
2. Adotar **Flyway** para versionamento de schema de banco de dados (`resources/db/migration/`).
3. Proibir o uso de auto-ddl (`hibernate.ddl-auto=create/update`) para garantir que toda alteração de schema seja reproduzível e rastreável.

## Consequências
### Positivas
- Migrações de schema totalmente determinísticas e reproduzíveis entre ambientes (dev, test, prod).
- Suporte nativo a tipos avançados, índices eficientes e isolamento de transações com controle de concorrência (`@Version` / optimistic locking).

### Negativas
- Toda alteração em tabela exige a criação explícita de script SQL versionado (`V1__...`, `V2__...`).
