package com.jsoft;

import com.jsoft.dao.DeptDao;
import com.jsoft.domain.Dept;
import org.junit.Test;

import java.util.List;

public class Demo {
    @Test
    public void testGetForList() {
        DeptDao deptDao = new DeptDao();
        List<Dept> forList = deptDao.getForList("select * from dept", null);
        for (Dept dept : forList) {
            System.out.println(dept);
        }
    }

    @Test
    public void testGetForValue() {
        DeptDao deptDao = new DeptDao();
        Object obj = deptDao.getForValue("select count(*) from dept",null);
        System.out.println("部门总数：" + obj);
    }

    @Test
    public void testGet() {
        DeptDao deptDao = new DeptDao();
        Dept dept = deptDao.get("select * from dept where deptno=? limit 1", 10);
        System.out.println(dept);
    }

    @Test
    public void testUpate() {
        DeptDao deptDao = new DeptDao();
        int affectCount = deptDao.update("insert into dept values(?,?,?)", 100,"新部门","北京");
        System.out.println(affectCount > 0 ? "插入成功" : "插入失败");

    }
}
