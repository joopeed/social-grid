import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

import play.Application;
import play.GlobalSettings;
import play.Logger;
import controllers.CadastroUsuario;
import controllers.CadastroUsuarioException;

public class Global extends GlobalSettings {
	@Override
	public void onStart(Application arg0) {
		super.onStart(arg0);
		
		Logger.info("Cadastrando usuários do arquivo...");
		cadastrarUsuarios();
	}

    public void cadastrarUsuarios() {
    	CadastroUsuario CADASTRO = new CadastroUsuario();
    	
    	InputStream usuariosArquivo = play.Play.application().resourceAsStream("res/usuarios.txt");
		
		if (usuariosArquivo == null) {
			Logger.error("Arquivo de usuários não encontrado no buildbath!");
			try {
				usuariosArquivo = new FileInputStream(new File("conf/res/usuarios.txt"));
			} catch (FileNotFoundException e) {
				Logger.error("Arquivo de usuários não encontrado.");
			}
		}
		
		int novosCadastros = 0;
		int jaCadastrados = 0;
		
		Scanner scUsuarios = new Scanner(usuariosArquivo);
		
		while(scUsuarios.hasNextLine()){
			String[] dados = scUsuarios.nextLine().split(":");
			
			try {
				CADASTRO.cadastrarUsuario(dados[0], dados[1], dados[2]);
				novosCadastros++;
			} catch (CadastroUsuarioException e) {
				jaCadastrados++;
			}
		}
		
		scUsuarios.close();
    	Logger.info("Banco de dados populado com " + String.valueOf(novosCadastros) + " novos usuarios! "
    			+ "Já estavam cadastrados " + String.valueOf(jaCadastrados) + " usuários.");
    }
}
