package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

public class Carregador {
	private final int LINHA_BRANCA = 1;
	
	
	public void preencheGrade(Set<Disciplina> disciplinas) throws IOException {
		String caminho = new File("res/disciplinas.txt").getCanonicalPath();
		BufferedReader reader = new BufferedReader(new FileReader(new File(caminho)));
		
		while(reader.ready()){
			String[] info = reader.readLine().split(":");
			if (info.length == LINHA_BRANCA) {
				continue;
			}
			String nome = info[0];
			String creditos = info[2];
						
			disciplinas.add(new Disciplina(nome, Integer.parseInt(creditos)));
		}
			
		reader.close();
	}
}