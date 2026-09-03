# Guia de Início Rápido (Getting Started)

## Pré-requisitos
- **Java JDK 21 LTS** instalado.
- **Docker** e **Docker Compose** instalados.
- **Maven 3.9+** (ou utilizar o wrapper `./mvnw` do projeto).

---

## Passo a Passo para Execução Local

### 1. Clonar o Repositório
```bash
git clone <repository-url>
cd fincore
```

### 2. Validar a Compilação do Projeto
Execute o comando Maven para garantir que todos os módulos e dependências compilam corretamente:
```bash
./mvnw clean verify
```

### 3. Subir o Banco de Dados PostgreSQL Local
Execute o Docker Compose para iniciar a instância do PostgreSQL:
```bash
docker compose up -d
```

Você pode verificar a saúde do banco de dados executando:
```bash
docker compose ps
```

### 4. Parar a Infraestrutura Local
Para encerrar os containers e limpar os volumes locais:
```bash
docker compose down -v
```
