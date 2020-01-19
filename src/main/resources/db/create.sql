SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS users (id serial PRIMARY KEY auto_increment, name VARCHAR, position VARCHAR, role VARCHAR, departmentid INTEGER );

CREATE TABLE IF NOT EXISTS departments (id serial PRIMARY KEY auto_increment, name VARCHAR, description VARCHAR, numOfEmployees INTEGER);

CREATE TABLE IF NOT EXISTS news (id serial PRIMARY KEY auto_increment, title VARCHAR, content VARCHAR, departmentid INTEGER);
