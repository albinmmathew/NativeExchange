/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NativeExchange;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Albin
 */
public class DBConnect {
    public static Connection getConnection() {
        try {
            String url = "jdbc:oracle:thin:@//localhost:1521/FREEPDB1";
            String user = "albin";
            String password = "albin";
            Connection con = DriverManager.getConnection(url,user,password);
            System.out.println("connected.."+con);
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static Connection getConnectionn() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
