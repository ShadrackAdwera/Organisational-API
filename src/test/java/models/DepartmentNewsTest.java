package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class DepartmentNewsTest {

    @Test
    public void getDepartmentId_savesDepartmentIdSuccessfully() {
        DepartmentNews departmentNews = new DepartmentNews("News", "WEWE", 2);
        assertEquals(2, departmentNews.getDepartmentId());
    }

    @Test
    public void setDepartmentId_changesDepartmentID() {
        DepartmentNews departmentNews = new DepartmentNews("News", "WEWE", 2);
        departmentNews.setDepartmentId(2);
        assertEquals(2, departmentNews.getDepartmentId());
    }
}