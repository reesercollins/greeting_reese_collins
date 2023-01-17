CREATE SEQUENCE greeting_seq;

CREATE TABLE greeting
(
    id BIGINT NOT NULL,
    content VARCHAR(255),
    PRIMARY KEY (id)
);