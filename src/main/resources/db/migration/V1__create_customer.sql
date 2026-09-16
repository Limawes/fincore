CREATE TABLE customers (
    id UUID PRIMARY KEY,
    cpf_hash VARCHAR(255) NOT NULL,
    cpf_last4 VARCHAR(4) NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
    version BIGINT NOT NULL,

    CONSTRAINT uk_customer_cpf_hash UNIQUE (cpf_hash),
    CONSTRAINT chk_customer_cpf_last4 CHECK (cpf_last4 ~ '^[0-9]{4}$'),
    CONSTRAINT chk_customer_status CHECK (status IN ('ACTIVE', 'BLOCKED'))
);
