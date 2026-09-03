-- Init script for FinCore Local Environment
CREATE DATABASE customer_db;
CREATE DATABASE account_db;
CREATE DATABASE transaction_db;

GRANT ALL PRIVILEGES ON DATABASE customer_db TO fincore_user;
GRANT ALL PRIVILEGES ON DATABASE account_db TO fincore_user;
GRANT ALL PRIVILEGES ON DATABASE transaction_db TO fincore_user;
