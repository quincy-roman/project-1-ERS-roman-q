package com.revature;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import com.revature.model.Role;
import com.revature.model.Users;
import com.revature.utilities.HibernateUtil;

public class DriverTest {
	
	private Session session;

	@Before
	public void setUp() throws Exception {
		session = HibernateUtil.getSession();
		System.out.println("Session created.");
	}

	@Test
	public void testCreate() {
		session.beginTransaction();
		Role role = new Role(1, "Employee");
		Users user = new Users("bill1", "1bill", "Bill", "Billy", "bb@bb.com", role);
		
		Integer id = (Integer) session.save(user);
		
		session.getTransaction().commit();
		
		assertTrue(id > 0);
	}
	
	@Test
	public void testUpdate() {
		int id = 19;
		
		Role role = new Role(1, "Employee");
		Users user = new Users(19,"gill1", "1gill", "Chill", "Gilly", "gg@gg.com", role);
		
		session.beginTransaction();
		session.update(user);
		session.getTransaction().commit();
		
		Users user2 = session.find(Users.class, id);
		
		assertEquals("Chill", user2.getFirstName());
		
	}
	
	@Test
	public void testGet() {
		
		int id = 3;
		
		Users user = session.find(Users.class, id);
		
		assertEquals("Tester", user.getFirstName());
		
	}
	
	@Test
	public void testSelectAll() {
		
		List<Users> userList = new ArrayList<>();
		
		userList = session.createQuery("from users", Users.class).list();
		
		System.out.println(userList.size());
		
		assertEquals(3, userList.size());
	}

}
