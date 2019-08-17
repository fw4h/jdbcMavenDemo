package jdbcMavenDemo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateMain {

	public static void main(String[] args) {
		UserDTO user = new UserDTO();
		Date date = new Date();
		user.setName("Reek");
		user.setPhone_no("2236564");
		user.setPassword("hibernate");
		user.setDob(new java.sql.Date(date.getTime()));
		user.setUsername("Kappa");
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		Session session = factory.openSession();
		session.beginTransaction();
		session.save(user);
		session.update(user);
		session.getTransaction().commit();
		session.close();

	}

}
