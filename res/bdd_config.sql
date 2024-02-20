DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS servers;
DROP TABLE IF EXISTS values;

CREATE TABLE users(
    uid SERIAL,
    login VARCHAR(25) NOT NULL,
    password VARCHAR(25) NOT NULL,

    CONSTRAINT pk_user PRIMARY KEY(uid)
);

CREATE TABLE servers(
    sid SERIAL,
    name VARCHAR(25) NOT NULL,
    current_players INTEGER NOT NULL,
    max_players INTEGER NOT NULL,

    CONSTRAINT pk_server PRIMARY KEY(sid)
);

CREATE TABLE values(
    vid INTEGER NOT NULL,
    value VARCHAR(16) NOT NULL,

    CONSTRAINT pk_value PRIMARY KEY(vid)
);