CREATE TABLE IF NOT EXISTS users (
    id VARCHAR(255) not null primary key,
    username VARCHAR(255) not null unique,
    password VARCHAR(100) not null,
    name VARCHAR(100) not null,
    token VARCHAR(255) not null,
    state VARCHAR(20) not null,
    created datetime not null,
    modified datetime not null,
    last_login datetime not null
);

CREATE TABLE IF NOT EXISTS phone (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    number VARCHAR(100)  null,
    citycode VARCHAR(100)  null,
    contrycode VARCHAR(100)  null,
    id_user VARCHAR(255) not null,
    FOREIGN KEY (id_user) REFERENCES users(id)
);




