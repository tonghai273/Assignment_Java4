/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.khachHang;

/**
 *
 * @author Tong Duy Hai
 */
public class userDAO {
    private String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=QLBH";
	private String jdbcUsername = "sa";
	private String jdbcPassword = "haiha";

	private static final String INSERT_USERS_SQL = "INSERT INTO KHACHHANG" + "(tenKH,username, pass,tuoi,sdt,diachi) VALUES "
			+ " (?,?,?,?,?,?);";

	private static final String SELECT_USER_BY_USERNAME = "select * from from KHACHHANG where username =?";
	private static final String SELECT_ALL_USERS = "select * from KHACHHANG";
	private static final String DELETE_USERS_SQL = "delete from KHACHHANG where username = ?;";
	private static final String UPDATE_USERS_SQL = "update KHACHHANG set tenKH = ?,password= ?, tuoi =?,sdt=?,diachi=? where username = ?;";



	public static synchronized Connection getConnection(){
            Connection con = null;
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

	public void insertUser(khachHang user) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(2, user.getTen());
			preparedStatement.setString(3, user.getUsername());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setInt(5, user.getTuoi());
			preparedStatement.setString(6, user.getSdt());
			preparedStatement.setString(7, user.getDiachi());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public khachHang selectUser(String username) {
		khachHang kh = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME);) {
			preparedStatement.setString(1, username);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String ten = rs.getString(2);
				String user = rs.getString(3);
				String pass = rs.getString(4);
				int tuoi = rs.getInt(5);
				String sdt = rs.getString(6);
				String diachi = rs.getString(7);
				kh = new khachHang(ten, username, pass, tuoi, sdt, diachi);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return kh;
	}

	public List<khachHang> selectAllUsers() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<khachHang> users = new ArrayList<>();
		// Step 1: Establishing a Connection
		try  {
                        Connection connection = getConnection();
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement("select * from KHACHHANG");
//			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String ten = rs.getString(2);
				String username = rs.getString(3);
				String pass = rs.getString(4);
				String tuoii = rs.getString(5);
				int tuoi = Integer.parseInt(tuoii);
				String sdt = rs.getString(6);
				String diachi = rs.getString(7);
				users.add(new khachHang(ten, username, pass, tuoi, sdt, diachi));
//                                System.out.println(ten);
//                                System.out.println(user);
//                                System.out.println(pass);
//                                System.out.println(tuoi);
			}
		}
                catch (SQLException e) {
			printSQLException(e);
		}
		return users;
	}

	public boolean deleteUser(String user) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setString(1, user);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateUser(khachHang user) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			statement.setString(1, user.getTen());
			statement.setString(2, user.getPassword());
			statement.setInt(3, user.getTuoi());
			statement.setString(3, user.getSdt());
			statement.setString(3, user.getDiachi());
			statement.setString(4, user.getUsername());
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}
