package management;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import easyaccept.EasyAcceptFacade;

public class Main {
	
	public static void main(String[] args) throws Exception {
		 List<String> testes = new ArrayList<String>();
		 Facade facade = new Facade();
		 
		 testes.add("easyAccept/teste_aceitacao/usecase_1.txt");
		 testes.add("easyAccept/teste_aceitacao/usecase_2.txt");
		 testes.add("easyAccept/teste_aceitacao/usecase_3.txt");
		 testes.add("easyAccept/teste_aceitacao/usecase_4.txt");
		 testes.add("easyAccept/teste_aceitacao/usecase_5.txt");
		 testes.add("easyAccept/teste_aceitacao/usecase_6.txt");
		 testes.add("easyAccept/teste_aceitacao/usecase_7.txt");
		 
		 //Deletando para sempre passar nos testes
		 File file = new File("backupSistema/sistemaPop"); 
		 file.delete();
		 
		 EasyAcceptFacade eaFacade = new EasyAcceptFacade(facade, testes);
		 eaFacade.executeTests();
		 System.out.println(eaFacade.getCompleteResults());
	}
}
