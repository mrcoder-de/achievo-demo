CREATE TABLE users
(
    email      VARCHAR(255) PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name  VARCHAR(100) NOT NULL,
    is_active  BOOLEAN   DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE customers
(
    customer_id     SERIAL PRIMARY KEY,
    name            VARCHAR(255) NOT NULL,
    contact_email   VARCHAR(255) NOT NULL,
    phone_number    VARCHAR(20)  NOT NULL,
    billing_address TEXT         NOT NULL,
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE cost_centers
(
    cost_center_id SERIAL PRIMARY KEY,
    name           VARCHAR(255) NOT NULL,
    manager_email  VARCHAR(255) REFERENCES users (email),
    is_active      BOOLEAN   DEFAULT TRUE,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE cost_center_activities
(
    activity_id    SERIAL PRIMARY KEY,
    cost_center_id INTEGER REFERENCES cost_centers (cost_center_id),
    name           VARCHAR(255) NOT NULL,
    is_active      BOOLEAN   DEFAULT TRUE,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
