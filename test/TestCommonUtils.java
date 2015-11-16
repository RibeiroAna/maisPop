import management.Facade;


public class TestCommonUtils {
	public static Facade cadastraUsuarios() throws Exception {
		Facade facade = new Facade();
		facade.iniciaSistema();
		
		facade.cadastraUsuario
		("Ana", "anita@ana.com", "poderosa", "15/05/1994");
		facade.login("anita@ana.com", "poderosa");
		facade.criaPost("A vida é #bela", "02/08/2015 09:30:00");
		facade.logout();
		
		facade.cadastraUsuario
		("Madona", "madona@ana.com", "poderosa", "15/05/1994");
		facade.login("madona@ana.com", "poderosa");
		facade.adicionaPops(2000);
		facade.logout();
		
		facade.cadastraUsuario
		("Ana", "linda@ana.com", "poderosa", "15/05/1994");
	
		return facade;
	}
}
