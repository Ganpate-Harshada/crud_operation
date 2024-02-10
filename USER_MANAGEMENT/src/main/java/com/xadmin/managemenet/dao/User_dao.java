package com.xadmin.managemenet.dao;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.usermanagement.modal.User;

public class User_dao {
	
	private String jdbcURL= "jdbc:mysql://localhost:3306/userdb2";
	private String jdbcUsername= "root";
	private String jdbcPassword= "root";
	
	
	private static final String INSERT_USERS_SQL = "INSERT INTO user_table2" + " (name,email,country)VALUES"+"(?,?,?)";
    
    private static final String SELECT_USERS_BY_ID = "select id , name ,email,country from user_table where id=?;";
    
    		
    private static final String SELECT_ALL_USERS = "select * from user_table2";

		
    private static final String DELETE_USERS_SQL = "delete from user_table2 where id=?;";
    
    		
    private static final String UPDATE_USERS_SQL = "update user_table2 set name=?,email=?,country=?;";
	
    public  User_dao() {
    }

    
       

    protected Connection getConnection() {
    	 Connection connection = null;
    	 try {
    		 Class.forName("com.mysql.jdbc.Driver");
    		 connection  = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
    	 } catch (SQLException e) {
    		 e.printStackTrace();
    	 } catch (ClassNotFoundException e) {
    		 e.printStackTrace();
    	 }
    	 return connection;
    }
    public void insertUser(User user)throws SQLException{
    	System.out.println(INSERT_USERS_SQL);
    	try (Connection connection = getConnection();
    			PreparedStatement preparedStatement =connection.prepareStatement(INSERT_USERS_SQL)){
    			preparedStatement.setString(1,user.getName());
    			preparedStatement.setString(2,user.getEmail());
    			preparedStatement.setString(3,user.getCountry());
    			System.out.println(preparedStatement);
    			preparedStatement.executeUpdate();
    } catch (SQLException e) {
		 printSQLException(e);
    }
    }
    
    public User select1User(int id) {
    	
    	
    	User user = null;
    	try(Connection connection = getConnection();
    			PreparedStatement preparedStatement =connection.prepareStatement(SELECT_USERS_BY_ID) ;)
    	{
    		preparedStatement.setInt(1, id);
    		System.out.println(preparedStatement);
    		ResultSet rs =preparedStatement.executeQuery();
    		
    		while(rs.next()) {
    			String name = rs.getNString("name");
    			String email = rs.getNString("email");
    			String country = rs.getNString("country");
    			user = new User(id,name,email,country);
    		}
    	}catch(SQLException e) {
    		printSQLException(e);
    	}
    	return user;
    }
    public List<User>selectAllUsers(){
    	List<User>users =new ArrayList<User>();
    	try(Connection connection =getConnection();
    			
    	PreparedStatement preparedStatement=connection.prepareStatement(SELECT_ALL_USERS);){
        System.out.println(preparedStatement);
    	ResultSet rs = preparedStatement.executeQuery();
        
    	while (rs.next()) {
    		int id =rs.getInt("id");
    		String name = rs.getString("name");
    		String email = rs.getString("email");
    		String country = rs.getString("country");
    		users.add(new User(id,name,email,country));
    	}
    
    }catch(SQLException e) {
		printSQLException(e);
	}
	return users;
    }
    
    public boolean deleteUser(int id)throws SQLException{
    	boolean rowDeleted;
    	try(Connection connection=getConnection();
    			PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);){
    
       statement.setInt(1,id);
       rowDeleted = statement.executeUpdate()>0;
    	}
    	return rowDeleted;

    }

 public boolean updateUser(User user) throws SQLException{
	 boolean rowUpdated;
	 try (Connection connection =getConnection();
	 PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);){
		 System.out.println("updated User :" + statement);
		 statement.setString(1,user.getName());
		 statement.setString(2,user.getEmail());
		 statement.setString(3,user.getCountry());
		 statement.setInt(4,user.getId());
		 
		 rowUpdated = statement.executeUpdate() > 0;
	 }
	 return rowUpdated;
 }
 
 private void printSQLException(SQLException ex) {
	 for(Throwable e :ex) {
		 if (e instanceof SQLException) {
			 e.printStackTrace(System.err);
			 System.out.println("SQLState :" + ((SQLException)e).getSQLState());
			 System.out.println("Error Code :" + ((SQLException)e).getErrorCode());
			 System.out.println("Message :" + e.getMessage());
			 Throwable t =ex.getCause();
			  while (t != null) {
				  System.out.println("Cause :" + t);
				  t = t.getCause();
			  }
		 
	 }
	 
 }
 
 }
 }