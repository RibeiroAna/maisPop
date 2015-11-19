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

		 //Teste 8 lê arquivos dos testes anteriores, mas ele mesmo não conversa com eles
		 file.delete();
		 List<String> testesNovos = new ArrayList<String>();
		 testesNovos.add("easyAccept/teste_aceitacao/usecase_8.txt");
		 testesNovos.add("easyAccept/teste_aceitacao/usecase_9.txt");
		 testesNovos.add("easyAccept/teste_aceitacao/usecase_10.txt"); 
		 EasyAcceptFacade eaFacade8 = new EasyAcceptFacade(facade, testesNovos);
		 eaFacade8.executeTests();
		 System.out.println(eaFacade8.getCompleteResults());	 
	}
}
