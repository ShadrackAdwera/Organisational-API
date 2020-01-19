package dao;

import models.Departments;
import models.News;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oNewsDaoTest {
    private Sql2oNewsDao newsDao;
    private Sql2oDepartmentsDao departmentsDao;
    private Sql2oUsersDao usersDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        newsDao = new Sql2oNewsDao(sql2o);
        departmentsDao = new Sql2oDepartmentsDao(sql2o);
        usersDao = new Sql2oUsersDao(sql2o);
        conn = sql2o.open();

    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void save_successfullySavesNewsInTheDBWithID() {
        News news = setUpNews();
        newsDao.save(news);
        int foundNews = newsDao.findById(news.getId()).getId();
        assertEquals(1,foundNews);
    }

    @Test
    public void allNews_returnsAllNews() {
        News news = setUpNews();
        newsDao.save(news);
        News news1 = new News("Gym","Leg Day twice a week");
        newsDao.save(news1);
        assertEquals(2, newsDao.allNews().size());
    }

    @Test
    public void allNewsInDepartment_returnsAllNewsInADepartment() {
        Departments departments = new Departments("HR","We HR",6);
        departmentsDao.save(departments);
        Departments departments1 = new Departments("IT", "We IT",7);
        departmentsDao.save(departments1);
        News news = new News("IT News", "MEETING AT 8 A.M", departments1.getId());
        newsDao.save(news);
        News news1 = new News("IT News","Follow up meeting at 10 a.m", departments1.getId());
        newsDao.save(news1);
        assertEquals(2, newsDao.allNewsInDepartment(departments1.getId()).size());
    }

    @Test
    public void findById_returnsNewsById() {
        News news = setUpNews();
        newsDao.save(news);
        int idOfNews = news.getId();
        int idOfFoundNews = newsDao.findById(news.getId()).getId();
        assertEquals(idOfNews, idOfFoundNews);
    }

    @Test
    public void delete_deleteNewsById() {
        News news = setUpNews();
        newsDao.save(news);
        News news1 = setUpNews();
        newsDao.save(news1);
        newsDao.delete(news.getId());
        assertEquals(1, newsDao.allNews().size());
        newsDao.delete(news1.getId());
        assertEquals(0, newsDao.allNews().size());
    }

    @Test
    public void clearAll_deletesAllNewsEntries() {
        News news = setUpNews();
        newsDao.save(news);
        News news1 = setUpNews();
        newsDao.save(news1);
        News news2 = setUpNews();
        newsDao.save(news2);
        News news3 = setUpNews();
        newsDao.save(news3);
        assertEquals(4, newsDao.allNews().size());
        newsDao.clearAll();
        assertEquals(0, newsDao.allNews().size());
    }

    //helpers
    public News setUpNews(){
        return new News("Miguna", "Miguna in Kenya",1);
    }
}