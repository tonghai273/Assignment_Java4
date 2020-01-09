/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Tong Duy Hai
 */
public class connect {
    public static Connection con;
    public static synchronized Connection getConnection(){
        if(con != null){
            return con;
        }
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLBH;user=sa;password=haiha");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return con;
    }
    public  void thucThiSQL(String sql){
        try{
            Connection connect = getConnection();
            PreparedStatement st = connect.prepareStatement(sql);
            st.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public ResultSet chonDuLieu(String sql) throws Exception{
			Connection connect =getConnection();
			Statement stmt = connect.createStatement();
			ResultSet rs=	stmt.executeQuery(sql);
			return rs;
		}
}
