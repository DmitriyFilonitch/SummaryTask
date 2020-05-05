package ua.nure.filonitch.summarytask.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.filonitch.summarytask.beans.Services;

/**
 * @author D.Filonich
 *
 *         Services Unit Testing
 *
 */
public class ServicesTestClass {
	static Services services;

	@BeforeClass
	public static void constructorTest() {
		services = new Services();
	}

	@Test
	public void gettersSettersTest() {

		services.setService_id(2);
		services.setService_name("Интернет");
		services.setService_description("Дешевле и быстрее просто не найти");

		Assert.assertEquals(2, services.getService_id());
		Assert.assertEquals("Интернет", services.getService_name());
		Assert.assertEquals("Дешевле и быстрее просто не найти", services.getService_description());

	}

}
