package com.menga.mysql

import java.sql.{DriverManager, ResultSet, ResultSetMetaData}

/**
  * Created by Marvel on 2019/7/11.
  */
object MySQLDemo {

  private val USER = "root"
  private val PASSWORD = "root"
  private val URL = "jdbc:mysql://192.168.3.116:3306/weiweicat?useUnicode=true&characterEncoding=utf8"

  def insertEmoji(name: String, mark: String): Unit = {
    // Register JDBC driver
    Class.forName("com.mysql.cj.jdbc.Driver")
    // Open a connection
    val connection = DriverManager.getConnection(URL, USER, PASSWORD)

    // Execute a query
    val statement = connection.createStatement()

    val sql = "INSERT INTO `emoji` SET `name` = \'" + name + "\', `mark` = \'" + mark + "\'"
    statement.executeUpdate(sql)

    // Clean-up environment
    statement.close()
    connection.close()
  }

  def insertMessage(): Unit = {
    // Register JDBC driver
    Class.forName("com.mysql.cj.jdbc.Driver")
    // Open a connection
    val connection = DriverManager.getConnection(URL, USER, PASSWORD)

    // Execute a query
    val statement = connection.createStatement()

    val sql = "INSERT INTO ipolymerdb.`messages` SET\n          `title` = 'title',\n          `content` = '<p>\uD83D\uDE01<br></p>',\n          `recipient_type` = '2',\n          `message_type` = '1',\n          `status` = '3',\n          `send_on` = '2019-07-12T15:09:04.655',\n         `group_id` = '7', \n          `created_by` = '88',\n          `updated_by` = '88'"
    statement.executeUpdate(sql)

    // Clean-up environment
    statement.close()
    connection.close()
  }

  def test(): Unit = {
    // Register JDBC driver
    Class.forName("com.mysql.cj.jdbc.Driver")
    // Open a connection
    val connection = DriverManager.getConnection(URL, USER, PASSWORD)

    // Execute a query
    val statement = connection.createStatement()
    val sql = "SELECT * FROM cat"
    val resultSet: ResultSet = statement.executeQuery(sql)

    val metaData: ResultSetMetaData = resultSet.getMetaData
    println(s"columnCount=${metaData.getColumnCount}")

    for (i <- 1 to metaData.getColumnCount) {
      println(s"columnTypeName=${metaData.getColumnTypeName(i)}, columnLabel=${metaData.getColumnLabel(i)}, columnName=${metaData.getColumnName(i)}")
    }

    while (resultSet.next()) {
      val id  = resultSet.getInt("id")
      val age = resultSet.getString("age")
      val name = resultSet.getString("name")

      println(s"id=$id, age=$age, name=$name")
    }
    resultSet.close()

    // Clean-up environment
    statement.close()
    connection.close()
  }

  def main(args: Array[String]): Unit = {
//    insertEmoji("<p>\uD83D\uDE01<br></p>", "wawa")
    insertMessage()
  }
}
