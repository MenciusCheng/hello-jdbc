package com.menga.dao;

import com.menga.domain.CommonLogisticsPrice;

import java.util.List;

/**
 * Created by Marvel on 2019/9/23.
 */
public class CommonLogisticsPriceDao {

    private CommonLogisticsPriceDao() {}

    private static class CommonLogisticsPriceDaoHolder {
        private static final CommonLogisticsPriceDao INSTANCE = new CommonLogisticsPriceDao();
    }

    /**
     * 单例模式
     */
    public static CommonLogisticsPriceDao getInstance() {
        return CommonLogisticsPriceDaoHolder.INSTANCE;
    }

    /**
     * 查询运价
     * @param sourceCode
     * @param destinationCode
     */
    public List<CommonLogisticsPrice> findByCode(String sourceCode, String destinationCode) {
        String sql = "SELECT * FROM `common_logistics_prices` WHERE `logistics_contractor_id` = 1 AND `warehouse_id` = 0 AND `source_code` = '" + sourceCode + "' AND `destination_code` = '" + destinationCode + "'";
        return JdbcResources.getLogisticsdb().rows(CommonLogisticsPrice.class, sql);
    }

    public static void main(String[] args) {
        List<CommonLogisticsPrice> prices = CommonLogisticsPriceDao.getInstance().findByCode("3302", "310230");
        for (CommonLogisticsPrice s : prices) {
            System.out.println(s);
        }
    }
}
