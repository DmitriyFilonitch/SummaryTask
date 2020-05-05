package ua.nure.filonitch.summarytask.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.filonitch.summarytask.beans.UserTarif;

/**
 * @author D.Filonich
 *
 *         UserTarif Unit Testing
 *
 */
public class UserTarifTestClass {
	static UserTarif usertarif;

	@BeforeClass
	public static void constructorTest() {
		usertarif = new UserTarif();
	}

	@Test
	public void gettersSettersTest() {

		usertarif.setId_user(3);
		usertarif.setCode("P005");
		usertarif.setPayment_status(2);

		Assert.assertEquals(3, usertarif.getId_user());
		Assert.assertEquals("P005", usertarif.getCode());
		Assert.assertEquals(2, usertarif.getPayment_status());

	}
}
