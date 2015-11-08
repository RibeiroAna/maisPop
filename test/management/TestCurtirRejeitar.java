package management;

import org.junit.Assert;
import org.junit.Test;

public class TestCurtirRejeitar {

	@Test
	public void testRejeitarInconePop() throws Exception {
		Facade facade = new Facade();
		facade.iniciaSistema();
		
		facade.cadastraUsuario
		("Ana", "anita@ana.com", "poderosa", "15/05/1994");
		facade.login("anita@ana.com", "poderosa");
		facade.criaPost("A vida é #bela", "02/08/2015 09:30:00");
		facade.logout();
		
		facade.cadastraUsuario
		("Ana", "linda@ana.com", "poderosa", "15/05/1994");
		facade.login("linda@ana.com", "poderosa");
		facade.adicionaPops(1500);
		Assert.assertEquals(facade.getPopularidade(), "Icone Pop");
		facade.rejeitarPost("anita@ana.com", 0);
		facade.logout();
		
		facade.login("anita@ana.com", "poderosa");
		Assert.assertEquals(1, facade.getNotificacoes());
		Assert.assertEquals("#bela,#epicfail", facade.getPost("Hashtags", 0));
	}
	
	@Test
	public void testCurtirInconePop() throws Exception {
		Facade facade = new Facade();
		facade.iniciaSistema();
		
		facade.cadastraUsuario
		("Ana", "anita@ana.com", "poderosa", "15/05/1994");
		facade.login("anita@ana.com", "poderosa");
		facade.criaPost("A vida é #bela", "02/08/2015 09:30:00");
		facade.logout();
		
		facade.cadastraUsuario
		("Ana", "linda@ana.com", "poderosa", "15/05/1994");
		facade.login("linda@ana.com", "poderosa");
		facade.adicionaPops(1500);
		Assert.assertEquals(facade.getPopularidade(), "Icone Pop");
		facade.curtirPost("anita@ana.com", 0);
		facade.logout();
		
		facade.login("anita@ana.com", "poderosa");
		Assert.assertEquals(1, facade.getNotificacoes());
		Assert.assertEquals("#bela,#epicwin", facade.getPost("Hashtags", 0));
	}

}
