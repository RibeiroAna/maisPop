import management.Facade;

import org.junit.Assert;
import org.junit.Test;


public class TestTopics {

	@Test
	public void testGetTT() throws Exception {
		Facade facade = TestCommonUtils.cadastraUsuarios();		
		
		facade.login("linda@ana.com", "poderosa");
		facade.adicionaPops(1500);
		Assert.assertEquals(facade.getPopularidade(), "Icone Pop");
		facade.curtirPost("anita@ana.com", 0);
		facade.criaPost("A vida é #maravilhosa #bela #nadaAver", "02/08/2015 09:30:00");
		facade.logout();
		
		facade.login("madona@ana.com", "poderosa");
		facade.adicionaPops(1500);
		Assert.assertEquals(facade.getPopularidade(), "Icone Pop");
		facade.curtirPost("anita@ana.com", 0);
		System.out.println(facade.atualizaTrendingTopics());
		
		System.out.println(facade.atualizaTrendingTopics());
		facade.logout();
	}
	
	@Test
	public void testRanking() throws Exception {
		Facade facade = TestCommonUtils.cadastraUsuarios();		
		
		facade.login("linda@ana.com", "poderosa");
		facade.adicionaPops(1500);
		Assert.assertEquals(facade.getPopularidade(), "Icone Pop");
		facade.curtirPost("anita@ana.com", 0);
		facade.criaPost("A vida é #maravilhosa #bela #nadaAver", "02/08/2015 09:30:00");
		facade.logout();
		
		facade.login("madona@ana.com", "poderosa");
		facade.adicionaPops(1500);
		Assert.assertEquals(facade.getPopularidade(), "Icone Pop");
		facade.curtirPost("anita@ana.com", 0);
		System.out.println(facade.atualizaRanking());
		facade.logout();
	}
	

}
