# ADR-001: Arquitetura Multimódulo e Clean Architecture

## Status
Aceito

## Contexto
O FinCore precisa evoluir de um monólito modular para microsserviços distribuídos sem acoplar regras de negócio financeiras a tecnologias ou frameworks específicos.

## Decisão
1. Adotar a estrutura Maven Multi-Module para isolamento e build independente.
2. Aplicar Clean Architecture (Ports & Adapters) em todos os serviços.
3. Manter o módulo de domínio (`domain`) estritamente em Java puro (LTS 21), sem anotações de JPA (`@Entity`), Spring (`@Component`, `@Service`) ou Jackson (`@JsonProperty`).

## Consequências
### Positivas
- Testabilidade alta e rápida do domínio sem necessidade de contexto Spring.
- Isolamento total de tecnologia (possibilidade de trocar banco de dados ou mensageria sem afetar regras financeiras).
- Evolução incremental simplificada para microsserviços.

### Negativas
- Necessidade de Mappers explícitos entre Entidades de Domínio, entidades JPA e DTOs REST.
- Maior quantidade inicial de classes e interfaces.
