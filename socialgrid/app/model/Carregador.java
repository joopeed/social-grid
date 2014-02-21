package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Lê o arquivo de disciplinas e criar cada disciplina no sistema.
 */
public class Carregador {
	
	private final int LINHA_BRANCA = 1;
	
	/**
	 * Cria um conjunto de objetos disciplinas a partir de um arquivo.
	 * @return Conjunto de disciplinas com dependências e requisitos.
	 * @throws IOException Erro na leitura do arquivo.
	 */
	public Set<Disciplina> preencheGrade() throws IOException {
		Set<Disciplina> disciplinas = new HashSet<Disciplina>();
		String caminho = new File("res/disciplinas.txt").getCanonicalPath();
		BufferedReader reader = new BufferedReader(new FileReader(new File(caminho)));
		
		long id = 1l;
		
		while(reader.ready()){
			String[] info = reader.readLine().split(":");
			if (info.length == LINHA_BRANCA) {
				continue;
			}
			String nome = info[0];
			String creditos = info[2];
			
			Disciplina d = new Disciplina(nome, Integer.parseInt(creditos));
			d.id = id++;
			disciplinas.add(d);
		}
			
		reader.close();	
		disciplinas = adicionaDependentesERequisitos(disciplinas);
		
		return disciplinas;
	}
	
	/**
	 * Adiciona disciplinas dependentes e requisitos em cada disciplina.
	 * @param disciplinas Conjunto de disciplinas sem dependências e requisitos.
	 * @return Conjunto de disciplinas com dependências e requisitos.
	 * @throws IOException Erro na leitura do arquivo.
	 */
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
			}
		}
			
		reader.close();
		
		return disciplinas;
	}
		
	/**
	 * Procura, em um conjunto, uma disciplina pelo nome e retorna o objeto Disciplina correspondente.
	 * @param disciplinas Conjunto de objetos Disciplina.
	 * @param nome Nome da disciplina procurada.
	 * @return Objeto Disciplina procurado.
	 */
	private Disciplina buscaDisciplina(Set<Disciplina> disciplinas, String nome) {
		for(Disciplina disc: disciplinas) {
			if (disc.getNome().equals(nome))
				return disc;
		}
		return null;
	}
}