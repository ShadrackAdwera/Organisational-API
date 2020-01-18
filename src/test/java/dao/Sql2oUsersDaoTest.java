package dao;

import models.Departments;
import models.Users;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;


import static org.junit.Assert.*;

public class Sql2oUsersDaoTest {
    private Sql2oUsersDao usersDao;
    private Sql2oDepartmentsDao departmentsDao;
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        usersDao = new Sql2oUsersDao(sql2o);
        departmentsDao = new Sql2oDepartmentsDao(sql2o);
        connection = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        connection.close();
    }

    @Test
    public void save_savesUserInformationInTheDatabase() throws Exception {
    Users users = setUpUser();
    usersDao.save(users);
    assertEquals(1, usersDao.findAll().size());
    }

    @Test
    public void findAll_ReturnsAllUsers() {
        Users users = setUpUser();
        assertEquals(0, usersDao.findAll().size());
        usersDao.save(users);
        assertEquals(1, usersDao.findAll().size());
    }
    @Test
    public void findById_returnsExistingUsersBasedOnId() {
        Users users = setUpUser();
        usersDao.save(users);
        Users users1 = usersDao.findById(users.getId());
        assertEquals(users, users1);
    }
    @Test
    public void delete_deletesUserById(){
        Users users = setUpUser();
        usersDao.save(users);
        assertEquals(1, usersDao.findAll().size());
        int idOfFoundUser = usersDao.findById(users.getId()).getId();
        usersDao.delete(idOfFoundUser);
        assertEquals(0, usersDao.findAll().size());
    }
    @Test
    public void clearAll_DeletesEverything(){
        Users users = setUpUser();
        usersDao.save(users);
        Users users1 = setUpUser();
        usersDao.save(users1);
        Users users2 = setUpUser();
        usersDao.save(users2);
        assertEquals(3, usersDao.findAll().size());
        usersDao.clearAll();
        assertEquals(0, usersDao.findAll().size());
    }
    @Test
    public void getAllEmployeesInDepartment_returnsAllEmployeesInDepartment(){
       Departments departments = setUpDepartment();
       departmentsDao.save(departments);
       Departments departments1 = setUpDepartment();
       departmentsDao.save(departments1);
       Users users = setUpUserForDepartment(departments);
       usersDao.save(users);
       Users users1 = setUpUserForDepartment(departments);
       usersDao.save(users1);
       assertEquals(2, usersDao.allUsersInADepartment(departments.getId()).size());
    }

    //helpers
    public Users setUpUser(){
    return new Users("Deez Nuts","Deeez Nuts", "Master Chief",1);
    }
    public Departments setUpDepartment(){
        return new Departments("Finance Department","Finance", 20);
    }
    public Users setUpUserForDepartment(Departments departments){
        return new Users("Deez Nuts", "Deez","Master Chef", departments.getId());
    }
}



