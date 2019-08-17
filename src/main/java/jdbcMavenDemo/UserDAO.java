package jdbcMavenDemo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.PreparedStatement;

public class UserDAO {
	Connection connection = null;
	
	void setupConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "19921104Mysql");
		connection.setAutoCommit(false);
	}

	void insert(UserDTO user) throws SQLException {

		try {
			setupConnection();

			PreparedStatement statement = connection
					.prepareStatement("insert into user(name, phone_no,dob,username,password) values(?,?,?,?,?)");
			statement.setString(1, user.getName());
			statement.setString(2, user.getPhone_no());
			statement.setDate(3, user.getDob());
			statement.setString(4, user.getUsername());
			statement.setString(5, user.getPassword());

			statement.executeUpdate();

//			ResultSet resultSet = statement.executeQuery(sql);
//			
//			while (resultSet.next()) {
//				System.out.println(resultSet.getInt(columnIndex));
//			}

			connection.commit();
		} catch (Exception e) {

			e.printStackTrace();
			connection.rollback();
		} finally {
			connection.close();
		}
	}
	
	void deleteById(int id) throws Exception {
		try {
			setupConnection();
			PreparedStatement deleteByIdPreparedStatement = connection.prepareStatement("delete from user where id = ?");
			deleteByIdPreparedStatement.setInt(1, id);
			deleteByIdPreparedStatement.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	public UserDTO findById(Integer id) throws Exception{
		try {
			setupConnection();

			PreparedStatement statement = connection
					.prepareStatement("select * from user where id = ?");
			statement.setInt(1, id);

			ResultSet resultSet = statement.executeQuery();
			
			UserDTO user = null;
			
			while (resultSet.next()){
				user = new UserDTO();
				user.setId(resultSet.getInt("id"));
				user.setName(resultSet.getString("name"));
				user.setPhone_no(resultSet.getString("phone_no"));
				user.setDob(resultSet.getDate("dob"));
				user.setUsername(resultSet.getString("username"));
				user.setPassword(resultSet.getString("password"));
				
				
			}

			return user;
		} catch (Exception e) {			
			e.printStackTrace();
			return null;
		} finally {
			connection.close();
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
