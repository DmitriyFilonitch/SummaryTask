package ua.nure.filonitch.summarytask.test;

import org.junit.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.filonitch.summarytask.beans.UserAccount;

/**
 * @author D.Filonich
 *
 *         UserAccount Unit Testing
 *
 */
public class UserClassTest {

	static UserAccount user;

	@BeforeClass
	public static void constructorTest() {
		user = new UserAccount();
	}

	@Test
	public void gettersSettersTest() {

		user.setUser_id(1);
		user.setRole_id(2);
		user.setUserName("vovik");
		user.setPassword("222");
		user.setNameRole("client");
		user.setGender("Male");
		user.setFullname("Vladimir Khanjian");
		user.setBlock_status(false);
		user.setBalance(100);

		float delta = 0;

		Assert.assertEquals(1, user.getUser_id());
		Assert.assertEquals(2, user.getRole_id());
		Assert.assertEquals("vovik", user.getUserName());
		Assert.assertEquals("222", user.getPassword());
		Assert.assertEquals("client", user.getNameRole());
		Assert.assertEquals("Male", user.getGender());
		Assert.assertEquals("Vladimir Khanjian", user.getFullname());
		Assert.assertEquals(false, user.isBlock_status());
		Assert.assertEquals(100, user.getBalance(), delta);

	}
}
