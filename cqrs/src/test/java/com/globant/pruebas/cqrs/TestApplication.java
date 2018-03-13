package com.globant.pruebas.cqrs;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
public class TestApplication {
	
	@Test
	public void 	main_Runs_Correctly() {
		Application app = new Application();
		assertNotNull("app is null", app);
		app.main(new String[] {});
		ConfigurableApplicationContext appConfig = app.getConfiguragleAppContext();
		assertNotNull("ConfiguragleAppContext Not Null",  appConfig!=null);
	}
	
	
}
