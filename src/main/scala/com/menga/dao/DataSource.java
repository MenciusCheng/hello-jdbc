package com.menga.dao;

import com.menga.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by Marvel on 2019/9/23.
 */
public class DataSource {

    public DataSource(String user, String password, String url) {
        this.user = user;
        this.password = password;
        this.url = url;
    }

    private final String user;
    private final String password;
    private final String url;

    private Connection getConnection() throws Exception {
        // Register JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Open a connection
        return DriverManager.getConnection(url, user, password);
    }

    public <T> List<T> rows(Class<T> clazz, String sql) {
        List<T> list = new ArrayList<>();
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            list = resultSetToList2(clazz, resultSet);

            resultSet.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 数据库集合转成列表
     * 通过类的字段名赋值
     */
    public <T> List<T> resultSetToList(Class<T> clazz, ResultSet resultSet) {
        List<T> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                T t = clazz.newInstance();
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    boolean accessible = field.isAccessible();
                    field.setAccessible(true);
                    String typeName = field.getGenericType().getTypeName();
                    Object column = null;
                    String fieldName = StringUtils.humpToLine2(field.getName());
                    if ("java.lang.Integer".equals(typeName)) {
                        column = resultSet.getInt(fieldName);
                    } else if ("java.lang.String".equals(typeName)) {
                        column = resultSet.getString(fieldName);
                    }
                    field.set(t, column);
                    field.setAccessible(accessible);
                }
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 数据库集合转成列表
     * 通过类的字段名和 Set 方法赋值
     */
    public <T> List<T> resultSetToList2(Class<T> clazz, ResultSet resultSet) {
        List<T> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                T t = clazz.newInstance();
                // 声明的属性
                Field[] fields = clazz.getDeclaredFields();
                // 声明的方法
                Method[] methods = clazz.getDeclaredMethods();
                for (Field field : fields) {
                    // 属性名
                    String fieldName = field.getName();
                    // Set 方法名
                    String methodSetName = "set" + StringUtils.upperFirst(fieldName);
                    Optional<Method> methodOpt = Arrays.stream(methods).filter(m -> m.getName().equals(methodSetName)).findFirst();
                    if (methodOpt.isPresent()) {
                        // 数据库字段名
                        String columnName = StringUtils.humpToLine2(fieldName);
                        // 属性名的类型
                        String typeName = field.getGenericType().getTypeName();
                        // 数据库字段值
                        Object columnValue = null;
                        if ("java.lang.Integer".equals(typeName)) {
                            columnValue = resultSet.getInt(columnName);
                        } else if ("java.lang.String".equals(typeName)) {
                            columnValue = resultSet.getString(columnName);
                        }
                        methodOpt.get().invoke(t, columnValue);
                    }
                }
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
