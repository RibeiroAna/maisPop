import management.Facade;

import org.junit.Assert;
import org.junit.Test;

public class TestCurtirRejeitar {

	@Test
	public void testRejeitarInconePop() throws Exception {
		Facade facade = TestCommonUtils.cadastraUsuarios();
		
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
		Facade facade = TestCommonUtils.cadastraUsuarios();
		facade.login("linda@ana.com", "poderosa");
		facade.adicionaPops(1500);
		Assert.assertEquals(facade.getPopularidade(), "Icone Pop");
		facade.curtirPost("anita@ana.com", 0);
		facade.logout();
		
		facade.login("anita@ana.com", "poderosa");
		Assert.assertEquals(1, facade.getNotificacoes());
		Assert.assertEquals("#bela,#epicwin", facade.getPost("Hashtags", 0));
	}
	
	@Test
	public void testUpdateTextOfThePost() throws Exception {
		Facade facade = TestCommonUtils.cadastraUsuarios();
		facade.login("linda@ana.com", "poderosa");
		facade.adicionaPops(1500);
		Assert.assertEquals(facade.getPopularidade(), "Icone Pop");
		facade.curtirPost("anita@ana.com", 0);
		facade.logout();
		
		facade.login("anita@ana.com", "poderosa");
		Assert.assertEquals(1, facade.getNotificacoes());
		Assert.assertEquals("#bela,#epicwin", facade.getPost("Hashtags", 0));
		Assert.assertEquals("A vida é #bela #epicwin (2015-08-02 09:30:00)",facade.getPost(0));
	}
	
	@Test
	public void fechaSistema() throws Exception {
		Facade facade = TestCommonUtils.cadastraUsuarios();
		
		facade.login("linda@ana.com", "poderosa");
		facade.adicionaPops(1500);
		Assert.assertEquals("Icone Pop", facade.getPopularidade());
		facade.rejeitarPost("anita@ana.com", 0);
		facade.logout();
		
		facade.login("anita@ana.com", "poderosa");
		Assert.assertEquals(1, facade.getNotificacoes());
		Assert.assertEquals(-50, facade.getPopsUsuario());
		facade.logout();
		
		facade.login("anita@ana.com", "poderosa");
		Assert.assertEquals(1, facade.getNotificacoes());
		Assert.assertEquals(-50, facade.getPopsUsuario());
		facade.logout();			
	}

}
