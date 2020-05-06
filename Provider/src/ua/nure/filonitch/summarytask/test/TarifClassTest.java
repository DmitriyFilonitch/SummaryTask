package ua.nure.filonitch.summarytask.test;

import org.junit.Assert;


import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.filonitch.summarytask.beans.Tarif;

/**
 * @author D.Filonich
 *
 *         Tarif Unit Testing
 *
 */
public class TarifClassTest {

	static Tarif tarif;

	@BeforeClass
	public static void constructorTest() {
		tarif = new Tarif();
	}

	@Test
	public void gettersSettersTest() {

		tarif.setCode("P001");
		tarif.setName("Супер безлимит");
		tarif.setPrice(100);
		tarif.setDescription("Самый быстрый инет");
		tarif.setService_id(1);
		tarif.setService_name("Интернет");
		tarif.setService_description("Дешевле и быстрее просто не найти");

		float delta = 0;

		Assert.assertEquals("P001", tarif.getCode());
		Assert.assertEquals("Супер безлимит", tarif.getName());
		Assert.assertEquals(100, tarif.getPrice(), delta);
		Assert.assertEquals("Самый быстрый инет", tarif.getDescription());
		Assert.assertEquals(1, tarif.getService_id());
		Assert.assertEquals("Интернет", tarif.getService_name());
		Assert.assertEquals("Дешевле и быстрее просто не найти", tarif.getService_description());

	}
}
