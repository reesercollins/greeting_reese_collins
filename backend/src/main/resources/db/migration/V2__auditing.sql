CREATE SEQUENCE revinfo_seq INCREMENT BY 50;

CREATE TABLE revinfo
(
    rev INTEGER NOT NULL,
    revtstmp BIGINT,
    CONSTRAINT revinfo_pkey PRIMARY KEY (rev)
);

CREATE TABLE greeting_aud
(
    id BIGINT NOT NULL,
    rev INTEGER NOT NULL,
    revtype SMALLINT,
    content VARCHAR(255),
    CONSTRAINT greeting_aud_pkey PRIMARY KEY (id, rev),
    CONSTRAINT greeting_aud_revinfo FOREIGN KEY (rev) REFERENCES revinfo (rev) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);