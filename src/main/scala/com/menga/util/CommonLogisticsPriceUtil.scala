package com.menga.util

import java.io.{BufferedWriter, FileWriter}
import java.util

import com.menga.dao.{CommonLogisticsPriceDao, SysAreaDao}
import com.menga.domain.{CommonLogistic, CommonLogisticsPrice, CommonLogisticsPricePT, SysArea}

import scala.collection.JavaConverters._
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * Created by Marvel on 2019/9/23.
  */
object CommonLogisticsPriceUtil {

  /**
    * 检查问题数据
    */
  def check(prices: List[CommonLogistic]): Unit = {

    val pm = prices.map(p => {
      p.getSourceCodeName -> p.getDestinationCodeName
    })

    // 地址个数
    System.out.println("地址个数：" + pm.size)
    System.out.println("地址去重后个数：" + pm.distinct.length)

    // 找出重复项
    val sm = new mutable.HashMap[(String, String), Int]()
    prices.foreach(p => {
      val key = p.getSourceCodeName -> p.getDestinationCodeName

      if (sm.contains(key)) {
        sm.put(key, sm(key) + 1)
      } else {
        sm.put(key, 1)
      }
    })
    sm.foreach(s => {
      if (s._2 > 1) {
        System.out.print("重复的地址：")
        System.out.println(s)
      }
    })

    // 所有地址
    val sysAreas = SysAreaDao.getInstance().findAll().asScala.toList

    val codeNames = new ArrayBuffer[String]

    // 找出不存在的地址
    prices.foreach(p => {
      val sourcesOpt = sysAreas.find(s => s.getFullName == p.getSourceCodeName)
//      val sources = SysAreaDao.getInstance().findByFullName(p.getSourceCodeName)
      if (sourcesOpt.isEmpty) {
//        System.out.println("不存在起始地：" + p.getSourceCodeName)
        codeNames += p.getSourceCodeName
      }

      val destinationOpt = sysAreas.find(s => s.getFullName == p.getDestinationCodeName)
//      val destinations = SysAreaDao.getInstance().findByFullName(p.getDestinationCodeName)
      if (destinationOpt.isEmpty) {
//        System.out.println("不存在目的地：" + p.getDestinationCodeName)
        codeNames += p.getDestinationCodeName
      }
    })

    codeNames.distinct.foreach(cn => {
      System.out.println("不存在目的地：" + cn)
    })
  }

  /**
    * 修复地址问题
    */
  def convertFullName(codeName: String): String = {
      val map = Map(
        "广东广州广州" -> "广东广州",
        "重庆重庆南岸" -> "重庆南岸",
        "重庆重庆渝北" -> "重庆渝北",
        "广东东莞樟木头" -> "广东东莞樟木头镇",
        "广东东莞东城街" -> "广东东莞东城街道",
        "广东中山火炬开发" -> "广东中山火炬开发区街道"
      )

      if (map.contains(codeName)) map(codeName) else codeName
  }

  /**
    * 封装成待打印的数组
    */
  def convertToPT(prices: List[CommonLogistic]): List[CommonLogisticsPricePT] = {
    // 所有地址
    val sysAreas: List[SysArea] = SysAreaDao.getInstance().findAll().asScala.toList
    // 所有内部运价
    val commons: List[CommonLogisticsPrice] = CommonLogisticsPriceDao.getInstance().findAll().asScala.toList

    prices.map(price => {
      val pt = new CommonLogisticsPricePT

      val sourceCode = sysAreas.find(s => s.getFullName == price.getSourceCodeName).get.getCode
      val destinationCode = sysAreas.find(s => s.getFullName == price.getDestinationCodeName).get.getCode

      pt.setSourceCode(sourceCode)
      pt.setDestinationCode(destinationCode)

      // 检查是否存在运价
      val commonOpt = commons.find(c => c.getSourceCode == sourceCode && c.getDestinationCode == destinationCode)
      if (commonOpt.isDefined) {
        pt.setId(commonOpt.get.getId)
      }

      val p1 = getPrice(price.getPrice1st)
      pt.setCost_1st_phase(p1._1)
      pt.setPrice_1st_phase(p1._2)

      val p2 = getPrice(price.getPrice2nd)
      pt.setCost_2nd_phase(p2._1)
      pt.setPrice_2nd_phase(p2._2)

      val p3 = getPrice(price.getPrice3nd)
      pt.setCost_3nd_phase(p3._1)
      pt.setPrice_3nd_phase(p3._2)

      val p4 = getPrice(price.getPrice4th)
      pt.setCost_4th_phase(p4._1)
      pt.setPrice_4th_phase(p4._2)

      val p5 = getPrice(price.getPrice5th)
      pt.setCost_5th_phase(p5._1)
      pt.setPrice_5th_phase(p5._2)

      val p6 = getPrice(price.getPrice6th)
      pt.setCost_6th_phase(p6._1)
      pt.setPrice_6th_phase(p6._2)

      val p7 = getPrice(price.getPrice7th)
      pt.setCost_7th_phase(p7._1)
      pt.setPrice_7th_phase(p7._2)

      val p8 = getPrice(price.getPrice8th)
      pt.setCost_8th_phase(p8._1)
      pt.setPrice_8th_phase(p8._2)

      pt
    })
  }

  def getPrice(str: String): (String, String) = {
    val s = str.trim

    val price =
      if (s.contains(":")) {
        s.substring(s.indexOf(":") + 1, s.length)
      } else if (s.contains("：")) {
        s.substring(s.indexOf("：") + 1, s.length)
      } else {
        System.out.println("价格格式错误：" + s)
        "0.00"
      }

    if (s.contains("整车价")) {
      (price, "0.00")
    } else {
      ("0.00", price)
    }
  }

  def printSql(prices: List[CommonLogisticsPricePT]): Unit = {
    val writer = new FileWriter("common_logistics_prices.sql")
    val bufferedWriter = new BufferedWriter(writer)

    bufferedWriter.write("use logisticsdb;")
    bufferedWriter.newLine()
    prices.foreach(price => {
      bufferedWriter.write(priceToSql(price))
      bufferedWriter.newLine()
    })

    bufferedWriter.close()
  }

  def priceToSql(price: CommonLogisticsPricePT): String = {
    if (price.getId == null || price.getId == 0) {
      s"INSERT INTO `common_logistics_prices` SET " +
        s"`logistics_contractor_id` = 1, " +
        s"`logistics_contractor_name` = '内部报价', " +
        s"`warehouse_id` = 0, " +
        s"`warehouse_name` = '', " +
        s"`source_code` = ${price.getSourceCode}, " +
        s"`destination_code` = ${price.getDestinationCode}, " +
        s"`price_1st_phase` = ${price.getPrice_1st_phase}, " +
        s"`cost_1st_phase` = ${price.getCost_1st_phase}, " +
        s"`price_2nd_phase` = ${price.getPrice_2nd_phase}, " +
        s"`cost_2nd_phase` = ${price.getCost_2nd_phase}, " +
        s"`price_3nd_phase` = ${price.getPrice_3nd_phase}, " +
        s"`cost_3nd_phase` = ${price.getCost_3nd_phase}, " +
        s"`price_4th_phase` = ${price.getPrice_4th_phase}, " +
        s"`cost_4th_phase` = ${price.getCost_4th_phase}, " +
        s"`price_5th_phase` = ${price.getPrice_5th_phase}, " +
        s"`cost_5th_phase` = ${price.getCost_5th_phase}, " +
        s"`price_6th_phase` = ${price.getPrice_6th_phase}, " +
        s"`cost_6th_phase` = ${price.getCost_6th_phase}, " +
        s"`price_7th_phase` = ${price.getPrice_7th_phase}, " +
        s"`cost_7th_phase` = ${price.getCost_7th_phase}, " +
        s"`price_8th_phase` = ${price.getPrice_8th_phase}, " +
        s"`cost_8th_phase` = ${price.getCost_8th_phase}, " +
        s"`priority` = 0, " +
        s"`created_at` = NOW(), " +
        s"`created_by` = 1, " +
        s"`updated_at` = NOW(), " +
        s"`updated_by` = 1;"
    } else {
      s"UPDATE `common_logistics_prices` SET " +
      s"`logistics_contractor_id` = 1, " +
      s"`logistics_contractor_name` = '内部报价', " +
      s"`warehouse_id` = 0, " +
      s"`warehouse_name` = '', " +
      s"`source_code` = ${price.getSourceCode}, " +
      s"`destination_code` = ${price.getDestinationCode}, " +
      s"`price_1st_phase` = ${price.getPrice_1st_phase}, " +
      s"`cost_1st_phase` = ${price.getCost_1st_phase}, " +
      s"`price_2nd_phase` = ${price.getPrice_2nd_phase}, " +
      s"`cost_2nd_phase` = ${price.getCost_2nd_phase}, " +
      s"`price_3nd_phase` = ${price.getPrice_3nd_phase}, " +
      s"`cost_3nd_phase` = ${price.getCost_3nd_phase}, " +
      s"`price_4th_phase` = ${price.getPrice_4th_phase}, " +
      s"`cost_4th_phase` = ${price.getCost_4th_phase}, " +
      s"`price_5th_phase` = ${price.getPrice_5th_phase}, " +
      s"`cost_5th_phase` = ${price.getCost_5th_phase}, " +
      s"`price_6th_phase` = ${price.getPrice_6th_phase}, " +
      s"`cost_6th_phase` = ${price.getCost_6th_phase}, " +
      s"`price_7th_phase` = ${price.getPrice_7th_phase}, " +
      s"`cost_7th_phase` = ${price.getCost_7th_phase}, " +
      s"`price_8th_phase` = ${price.getPrice_8th_phase}, " +
      s"`cost_8th_phase` = ${price.getCost_8th_phase}, " +
      s"`priority` = 0, " +
      s"`created_at` = NOW(), " +
      s"`created_by` = 1, " +
      s"`updated_at` = NOW(), " +
      s"`updated_by` = 1 " +
      s" WHERE id = ${price.getId};"
    }
  }

  def main(args: Array[String]): Unit = {
  }
}
