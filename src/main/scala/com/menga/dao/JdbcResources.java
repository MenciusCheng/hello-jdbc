package com.menga.dao;

/**
 * Created by Marvel on 2019/9/23.
 */
public class JdbcResources {

    private JdbcResources() {}

    private volatile static DataSource productdb;

    public static DataSource getProductdb() {
        if (productdb == null) {
            synchronized (JdbcResources.class) {
                if (productdb == null) {
                    String user = "root";
                    String password = "root";
                    String url = "jdbc:mysql://192.168.3.116:3306/productdb?useUnicode=true&characterEncoding=utf8";
                    productdb = new DataSource(user, password, url);
                }
            }
        }
        return productdb;
    }

    private volatile static DataSource logisticsdb;

    public static DataSource getLogisticsdb() {
        if (logisticsdb == null) {
            synchronized (JdbcResources.class) {
                if (logisticsdb == null) {
                    String user = "root";
                    String password = "root";
                    String url = "jdbc:mysql://192.168.3.116:3306/logisticsdb?useUnicode=true&characterEncoding=utf8";
                    logisticsdb = new DataSource(user, password, url);
                }
            }
        }
        return logisticsdb;
    }
}
