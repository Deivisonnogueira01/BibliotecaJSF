package br.edu.ifms.biblioteca.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {
  private final String USER = "root"; // a palavra final significa constante => Toda constante tem q ter caixa ALTA
  private final String PWD = "";
  private final String BD = "BibliotecaIFMS";
  private String url = "jdbc:mysql://localhost:3306/" + BD;
  private Connection conn = null;
  private String status = "Conexão não iniciada!";

  //-------------------------------------------------------------------------------
  //-------------------------------------------------------------------------------
  public ConexaoMySQL() {
    // no momento que eu crio o objeto eu já abro a conexao!!!
    //		conn = getConnection();
  }

  //-------------------------------------------------------------------------------
  public Connection getConnection() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection(url, USER, PWD);
      status = ("STATUS ---> Conectado com sucesso!");
      System.out.println(status);
      return conn;
    } catch (ClassNotFoundException | SQLException error) {
      status = ("STATUS ---> Não foi possível conectar!");
      System.out.println(status);
      error.printStackTrace();
    }
    return conn;
  }

  //-------------------------------------------------------------------------------
  public String statusConn() {
    return status;
  }

  //-------------------------------------------------------------------------------
  public boolean closeConn() {
    try {
      conn.close();
      status = ("STATUS ---> Conexão encerrada!");
      return true;
    } catch (SQLException e) {
      status = ("STATUS ---> Erro ao fechar a conexão!");
      System.out.println(status);
      //e.printStackTrace();
    }
    return false;
  }

  //-------------------------------------------------------------------------------
  public Connection getConn() {
    if (conn != null) return conn; else return getConnection();
  }
}
