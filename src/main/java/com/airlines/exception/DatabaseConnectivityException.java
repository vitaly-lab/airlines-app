package com.airlines.exception;

import java.sql.SQLException;

public class DatabaseConnectivityException extends Exception{
   public DatabaseConnectivityException(String str, SQLException e){
       System.out.println(str + " " + e);
   }
}
