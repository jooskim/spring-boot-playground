CREATE TABLE person (
    id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE book_order (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    ordered_by_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (ordered_by_id) REFERENCES person(id)
);

INSERT INTO person (first_name, last_name)
    VALUES ('J', 'K');

INSERT INTO person (first_name, last_name)
    VALUES ('Z', 'K');