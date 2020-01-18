SET MODE PostreSQL;

CREATE TABLE IF NOT EXISTS users (id serial PRIMARY KEY auto_increament, name VARCHAR, position VARCHAR, role VARCHAR, departmentid INTEGER );

CREATE TABLE IF NOT EXISTS departments (id serial PRIMARY KEY, name VARCHAR, description VARCHAR, numberofemployees INTEGER);

CREATE TABLE IF NOT EXISTS news (id serial PRIMARY KEY, title VARCHAR, content VARCHAR, departmentid INTEGER);
