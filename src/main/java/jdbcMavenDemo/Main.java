package jdbcMavenDemo;

import java.sql.SQLException;
import java.sql.Date;

public class Main {

	public static void main(String[] args) throws Exception {
		String name = "Da Wang";
		String phone_no = "122334";
		String dobStr = "2015-05-23";
		Date dob = Date.valueOf(dobStr);
		String username = "fw4h";
		String password = "231456";
		
		UserDTO user = new UserDTO();
		UserDAO userDao = new UserDAO();
		user.setName(name);
		user.setPhone_no(phone_no);
		user.setDob(dob);
		user.setUsername(username);
		user.setPassword(password);
		
		
		
		userDao.insert(user);
		
		System.out.println("Data inserted.");
		
//		user = userDao.findById(4);
		
//		System.out.println(user.getId()+" "+user.getName()+" "+user.getPhone_no()+" "+user.getDob()+" "+user.getUsername()+" "+user.getPassword());

	}

}
