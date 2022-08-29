package com.jsoft.dao;


import java.util.List;

/**
 * 完成表的crud操作
 * @param <T> 要操作的表
 */
public interface IDao<T> {

    /**
     *  完成表的DML操作
     * @param sql sql语句
     * @param args 占位符参数
     * @return 该sql影响记录条数
     * @throws Exception
     */
    int update(String sql, Object... args) throws Exception;

    /**
     *  完成表的DQL操作，
     * @param sql sql语句
     * @param args 占位符参数
     * @return 该sql执行后返回的由结果集封装的List集合
     * @throws Exception
     */
    List<T> getForList(String sql, Object... args) throws Exception;

    /**
     *查找表中的某一条记录
     * @param sql sql语句
     * @param args 占位符参数
     * @return 由表记录封装好的实体类对象
     * @throws Exception
     */
    T get(String sql, Object... args) throws Exception;

    /**
     * 完成表的标量查询
     * @param sql sql语句
     * @param args 占位符参数
     * @param <E> sql语句返回标量的类型
     * @return 返回sql语句返回的标量
     * @throws Exception
     */
    <E> E getForValue(String sql, Object... args) throws Exception;


}
