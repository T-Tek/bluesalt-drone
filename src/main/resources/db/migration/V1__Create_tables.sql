CREATE TABLE drone
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    serial_number    VARCHAR(100) NOT NULL UNIQUE,
    model            VARCHAR(20)  NOT NULL,
    weight_limit     INT          NOT NULL CHECK (weight_limit <= 500),
    battery_capacity INT          NOT NULL CHECK (battery_capacity BETWEEN 0 AND 100),
    state            VARCHAR(20)  NOT NULL
);

CREATE TABLE medication
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    weight   INT          NOT NULL,
    code     VARCHAR(255) NOT NULL,
    image    VARCHAR(255),
    drone_id BIGINT,
    FOREIGN KEY (drone_id) REFERENCES drone (id)
);

CREATE TABLE battery_audit_log
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    drone_id      BIGINT    NOT NULL,
    battery_level INT       NOT NULL,
    timestamp     TIMESTAMP NOT NULL,
    FOREIGN KEY (drone_id) REFERENCES drone (id)
);