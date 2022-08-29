package com.jsoft.dao;

import com.jsoft.util.DataSourceUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;


/**
 * 使用DBUtils依赖要求
 *      1.要求要查询的数据库表必须有相应的实体类
 *      2.要求实体类中必须有无参构造方法
 *      3.要求实体类中必须有Setter/Getter方法
 *
 * @param <T>
 */
public class DaoImpl<T> implements IDao<T> {

    private QueryRunner runner;
    private Class<T> type;

    public DaoImpl() {
        runner = new QueryRunner(DataSourceUtil.DATA_SOURCE);
        // 获取泛型类对象(在运行期，this实际是DaoImpl的子类)
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        if(genericSuperclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            type = (Class<T>)parameterizedType.getActualTypeArguments()[0];
        } else {
            throw new RuntimeException(this.getClass().getName() + " GenericSuperclass is not ParameterizedType");
        }

    }

    @Override
    public int update(String sql, Object... args) {
        try {
            return runner.update(sql,args);
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public List<T> getForList(String sql, Object... args){
        try {
            return runner.query(sql,new BeanListHandler<T>(type),args);
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public T get(String sql, Object... args) {
        try {
            return runner.query(sql,new BeanHandler<T>(type),args);
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public Object getForValue(String sql, Object... args) {
        try {
            return runner.query(sql,new ScalarHandler(),args);
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }
}
