# Diretrizes de Desenvolvimento e Convenções de Código

## 1. Regras de Tipos
- **Valores Monetários:** Usar obrigatoriamente `java.math.BigDecimal`. Nunca usar `float` ou `double`.
- **Datas e Horários:** Usar obrigatoriamente `java.time.Instant` ou `java.time.OffsetDateTime`. Nunca usar `java.util.Date` ou `long` para representação temporal de negócio.
- **Identificadores:** Usar `java.util.UUID` encapsulados em Value Objects (ex: `CustomerId`, `AccountId`).

## 2. Estrutura de Pacotes por Serviço
```text
br.com.fincore.<context>/
├── domain/
│   ├── model/         # Entidades, Value Objects e Enums puras
│   ├── service/       # Serviços de domínio puro
│   ├── exception/     # Exceções de negócio (sem HTTP Status)
│   └── port/          # Interfaces (Repositories, Clients, Publishers)
├── application/
│   ├── usecase/       # Casos de uso da aplicação
│   └── dto/           # Commands e Results internos
├── infrastructure/
│   ├── persistence/   # JpaEntities, SpringDataRepositories, Adapters, Mappers
│   ├── messaging/     # Kafka Producers/Consumers
│   └── client/        # Clientes HTTP / SDKs
├── interfaces/
│   └── rest/          # Controllers, Requests, Responses e GlobalExceptionHandler
└── configuration/     # Beans Spring, Security, OpenAPI
```

## 3. Padrão de Nomenclatura de Casos de Uso
- Sempre terminados em `UseCase` (ex: `CreateCustomerUseCase`, `FindCustomerUseCase`).
- Devem orquestrar entidades e portas, sem conter lógica de infraestrutura.
