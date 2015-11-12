import management.Facade;

import org.junit.Test;

public class TestCriaPost {

	@Test
	public void testCriaPostHastag() throws Exception {
		Facade facade = new Facade();
		facade.iniciaSistema();
		
		facade.cadastraUsuario
		("Ana", "anita@ana.com", "poderosa", "15/05/1994");
		facade.login("anita@ana.com", "poderosa");
		facade.criaPost("#vida #bela", "02/08/2015 09:30:00");
		facade.logout();
	}
	

}
