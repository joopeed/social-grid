package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Carregador {
	private final int LINHA_BRANCA = 1;
		
	public Set<Disciplina> preencheGrade() throws IOException {
		Set<Disciplina> disciplinas = new HashSet<Disciplina>();
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
		disciplinas = adicionaDependentesERequisitos(disciplinas);
		
		return disciplinas;
	}
	
	private Set<Disciplina> adicionaDependentesERequisitos(Set<Disciplina> disciplinas) throws IOException {
		String caminho = new File("res/disciplinas.txt").getCanonicalPath();
		BufferedReader reader = new BufferedReader(new FileReader(new File(caminho)));
		
		while(reader.ready()){
			String[] info = reader.readLine().split(":");
			if (info.length == LINHA_BRANCA) {
				continue;
			}
			String[] dependentes = info[1].split(",");
			Disciplina disciplinaAtual = buscaDisciplina(disciplinas, info[0]);
					
			for (String nome_dependente: dependentes) {
				if (nome_dependente.equals("nenhum")) {
					break;
				}
				Disciplina dependente = buscaDisciplina(disciplinas, nome_dependente);
				disciplinaAtual.acrescentaDependente(dependente);
				dependente.acrescentaRequisitos(disciplinaAtual);
			}
		}
			
		reader.close();
		
		return disciplinas;
	}
		
	private Disciplina buscaDisciplina(Set<Disciplina> disciplinas, String nome) {
		for(Disciplina disc: disciplinas) {
			if (disc.getNome().equals(nome))
				return disc;
		}
		return null;
	}
}