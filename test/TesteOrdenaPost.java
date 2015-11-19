import management.Facade;

import org.junit.Assert;
import org.junit.Test;

public class TesteOrdenaPost {

	@Test
	public void test() throws Exception {
		Facade facade = TestCommonUtils.cadastraUsuarios();

		facade.login("linda@ana.com", "poderosa");
		facade.adicionaPops(1500);
		Assert.assertEquals(facade.getPopularidade(), "Icone Pop");
		facade.rejeitarPost("anita@ana.com", 0);
		facade.logout();
	}

}
