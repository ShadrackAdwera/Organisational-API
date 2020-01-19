package dao;

import models.Departments;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oDepartmentsDaoTest {
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
    public void save_departmentsSavedSuccessfully() {
        Departments departments = setUpDepartments();
        departmentsDao.save(departments);
        assertEquals(1, departmentsDao.findAll().size());
    }

    @Test
    public void findAll_returnsAllDepartments() {
        Departments departments = setUpDepartments();
        departmentsDao.save(departments);
        Departments departments1 = setUpDepartments();
        departmentsDao.save(departments1);
        assertEquals(2, departmentsDao.findAll().size());
    }

    @Test
    public void findById_returnsDepartmentBasedOnID() {
        Departments departments = setUpDepartments();
        departmentsDao.save(departments);
        int idOfDepartment = departments.getId();
        int idOfFoundDepartment = departmentsDao.findById(departments.getId()).getId();
        assertEquals(idOfDepartment, idOfFoundDepartment);
    }

    @Test
    public void delete_deletesADepartmentBasedOnID() {
        Departments departments = setUpDepartments();
        departmentsDao.save(departments);
        Departments departments1 = setUpDepartments();
        departmentsDao.save(departments1);
        assertEquals(2, departmentsDao.findAll().size());
        departmentsDao.delete(departments.getId());
        assertEquals(1, departmentsDao.findAll().size());
    }

    @Test
    public void clearAll_deletesAllDepartments() {
        Departments departments = setUpDepartments();
        departmentsDao.save(departments);
        Departments departments1 = setUpDepartments();
        departmentsDao.save(departments1);
        assertEquals(2, departmentsDao.findAll().size());
        departmentsDao.clearAll();
        assertEquals(0, departmentsDao.findAll().size());
    }

    //helper methods
    public Departments setUpDepartments(){
        return new Departments("Finance","We finance",6);
    }
}