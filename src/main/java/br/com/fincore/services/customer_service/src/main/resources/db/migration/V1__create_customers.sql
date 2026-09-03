CREATE TABLE customers (
    id UUID PRIMARY KEY,
    cpf_hash VARCHAR(128) NOT NULL UNIQUE,
    cpf_last4 VARCHAR(4),
    full_name VARCHAR(160) NOT NULL,
    email VARCHAR(254) NOT NULL UNIQUE,
    status VARCHAR(30) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL,
    version BIGINT NOT NULL
);
