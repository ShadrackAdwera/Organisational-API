package dao;

import models.Users;

import java.util.List;

public interface UsersDao {
    //save user information
    void save(Users users);
    //list all users
    List<Users> findAll();
    //find user by id
    Users findById(int id);

}
