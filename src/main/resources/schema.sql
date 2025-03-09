/*
CREATE TABLE IF NOT EXISTS AUTHOR
(
    id              BIGINT  PRIMARY KEY,
    name            VARCHAR(250) NOT NULL,
    age             VARCHAR(250) NOT NULL,
    followersNumber DOUBLE       NOT NULL
);

CREATE TABLE IF NOT EXISTS BOOK
(
    id              BIGINT  PRIMARY KEY,
    title           VARCHAR(250) NOT NULL,
    type            VARCHAR(250) NOT NULL,
    publicationDate DATE         NOT NULL,
    author_id       BIGINT        NULL,
    CONSTRAINT fk_author FOREIGN KEY (author_id) REFERENCES author (id) ON DELETE CASCADE


);*/
