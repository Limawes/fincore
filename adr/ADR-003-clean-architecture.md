# ADR-003: Contrato de Isolamento do Domínio em Java Puro

## Status
Aceito

## Contexto
A inclusão acidental de dependências de infraestrutura (JPA, Spring, Jackson) no domínio enfraquece o isolamento das regras de negócio e dificulta os testes unitários.

## Decisão
Fica estritamente proibido o uso das seguintes anotações nos pacotes `domain`:
- `@Entity`, `@Table`, `@Column`, `@Id`, `@Version`
- `@Service`, `@Component`, `@Repository`
- `@RestController`, `@RequestMapping`
- `@KafkaListener`
- `@Configuration`
- `@JsonProperty`

Anotações permitidas no domínio: apenas anotações padrão do Java JDK (`java.lang`, `java.util`, `java.math`, `java.time`).

## Consequências
### Positivas
- Domínio 100% reutilizável e independente de infraestrutura.
- Testes de domínio executam em milissegundos sem subir o container do Spring.

### Negativas
- Exige criação de adapters de repositório e mappers de persistência para conversão entre o modelo puro e as entidades JPA.
