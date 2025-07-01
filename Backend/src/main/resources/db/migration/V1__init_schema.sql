CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    role VARCHAR(50) DEFAULT 'USER'
);

CREATE TABLE account (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES "users" (id),
    name VARCHAR(100),
    balance DECIMAL(12, 2)
);

CREATE TABLE transaction (
    id SERIAL PRIMARY KEY,
    account_id INTEGER REFERENCES account (id),
    label VARCHAR(255),
    amount DECIMAL(12, 2),
    date TIMESTAMP NOT NULL,
    type VARCHAR(50) -- income / expense
);