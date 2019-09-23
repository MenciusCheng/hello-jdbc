package com.menga.dao;

import com.menga.domain.SysArea;

import java.util.List;

/**
 * Created by Marvel on 2019/9/23.
 */
public class SysAreaDao {

    private SysAreaDao() {}

    private static class SysAreaDaoHolder {
        private static final SysAreaDao INSTANCE = new SysAreaDao();
    }

    /**
     * 单例模式
     */
    public static SysAreaDao getInstance() {
        return SysAreaDaoHolder.INSTANCE;
    }

    public List<SysArea> findByFullName(String fullName) {
        String sql = "SELECT * FROM `sys_area` WHERE `full_name` = '" + fullName + "'";
        return JdbcResources.getProductdb().rows(SysArea.class, sql);
    }

    public List<SysArea> findAll() {
        String sql = "SELECT * FROM `sys_area` limit 0, 100000";
        return JdbcResources.getProductdb().rows(SysArea.class, sql);
    }

    public static void main(String[] args) {
        List<SysArea> sysAreas = SysAreaDao.getInstance().findByFullName("贵州黔东南黎平九潮镇");
        for (SysArea s : sysAreas) {
            System.out.println(s);
        }
    }
}
