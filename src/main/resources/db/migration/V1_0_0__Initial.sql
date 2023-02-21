CREATE TABLE IF NOT EXISTS `contacts`
(
    `id_contact`         VARCHAR(16)     NOT NULL
        CONSTRAINT pk_product_id PRIMARY KEY,
    `name`        VARCHAR(125)    NOT NULL,
    `email`        VARCHAR(125)   NOT NULL,
    `phone`        VARCHAR(125)   NOT NULL,
    `created_at`  TIMESTAMP     NOT NULL,
    `updated_at`  TIMESTAMP     NOT NULL
);



