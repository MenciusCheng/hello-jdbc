package com.menga.mysql

import java.io.File
import java.io.FileInputStream

import com.menga.domain.CommonLogistic
import org.apache.poi.xssf.usermodel.{XSSFCell, XSSFRow, XSSFSheet, XSSFWorkbook}

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * Created by Marvel on 2019/9/20.
  */
class POIDemo {

}

object POIDemo {

  def readExcel(): Unit = {
    //打开需要读取的文件
    val inputStream: FileInputStream = new FileInputStream(new File("第一批路线.xlsx"))
    //读取工作簿
    val wordBook: XSSFWorkbook = new XSSFWorkbook(inputStream)
    //读取工作表,从0开始
    val sheet: XSSFSheet = wordBook.getSheetAt(1)
    //读取第三行
    val row: XSSFRow = sheet.getRow(1)
    //读取单元格
    val cell: XSSFCell = row.getCell(1)
    //获取单元格对象
    val value: String = cell.getStringCellValue
    System.out.println(value)
    //关闭输入流
    inputStream.close()
    //关闭工作簿
    wordBook.close()
  }

  def readLogisticsExcel(): List[CommonLogistic] = {
    val filePath = "第一批路线.xlsx"
    //打开需要读取的文件
    val inputStream: FileInputStream = new FileInputStream(new File(filePath))
    //读取工作簿
    val wordBook: XSSFWorkbook = new XSSFWorkbook(inputStream)

    val prices = dealLogisticsBook(wordBook)

    //关闭输入流
    inputStream.close()
    //关闭工作簿
    wordBook.close()

    prices
  }

  def dealLogisticsBook(wordBook: XSSFWorkbook): List[CommonLogistic] = {
    val sheetNums = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    sheetNums.flatMap(it => dealLogisticsSheet(wordBook.getSheetAt(it)))
  }

  def dealLogisticsSheet(sheet: XSSFSheet): List[CommonLogistic] = {
    val rows = sheet.rowIterator()
    val list: ArrayBuffer[CommonLogistic] = new ArrayBuffer[CommonLogistic]
    while (rows.hasNext) {
      val row = rows.next()
      if (row.getRowNum != 0) {
        val price = new CommonLogistic
        price.setSourceCodeProvince(if (row.getCell(0) != null) row.getCell(0).getStringCellValue else "")
        price.setSourceCodeCity(if (row.getCell(1) != null) row.getCell(1).getStringCellValue else "")
        price.setSourceCodeZone(if (row.getCell(2) != null) row.getCell(2).getStringCellValue else "")
        price.setDestinationCodeProvince(if (row.getCell(3) != null) row.getCell(3).getStringCellValue else "")
        price.setDestinationCodeCity(if (row.getCell(4) != null) row.getCell(4).getStringCellValue else "")
        price.setDestinationCodeZone(if (row.getCell(5) != null) row.getCell(5).getStringCellValue else "")
        price.setPrice1st(if (row.getCell(6) != null) row.getCell(6).getStringCellValue else "")
        price.setPrice2nd(if (row.getCell(7) != null) row.getCell(7).getStringCellValue else "")
        price.setPrice3nd(if (row.getCell(8) != null) row.getCell(8).getStringCellValue else "")
        price.setPrice4th(if (row.getCell(9) != null) row.getCell(9).getStringCellValue else "")
        price.setPrice5th(if (row.getCell(10) != null) row.getCell(10).getStringCellValue else "")
        price.setPrice6th(if (row.getCell(11) != null) row.getCell(11).getStringCellValue else "")
        price.setPrice7th(if (row.getCell(12) != null) row.getCell(12).getStringCellValue else "")
        price.setPrice8th(if (row.getCell(13) != null) row.getCell(13).getStringCellValue else "")
        list += price
      }
    }
    list.toList
  }

  def main(args: Array[String]): Unit = {
//    readExcel()
    val prices = readLogisticsExcel()
    prices
  }
}
