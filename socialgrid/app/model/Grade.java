package model;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Armazena todas as disciplinas do sistema.
 */
public class Grade {
	
	private Set<Disciplina> disciplinas;

	/**
	 * Construtor
	 * @throws IOException Erro na leitura do arquivo.
	 */
	public Grade() throws IOException {
		disciplinas = new HashSet<Disciplina>();
		preencheGrade();
	}

	/**
	 * Preenche a grade com disciplinas.
	 * @throws IOException Erro na leitura do arquivo.
	 */
	private void preencheGrade() throws IOException {
		Carregador carregador = new Carregador();
		disciplinas = carregador.preencheGrade();
	}

	/**
	 * Busca e retorna por uma disciplina pelo nome.
	 * @param nome Nome da disciplina procurada.
	 * @return Disciplina procurada ou Null em caso de mesma não existir.
	 */
	public Disciplina getDisciplinaPorNome(String nome) {
		for(Disciplina disc: disciplinas) {
			if (disc.getNome().equals(nome))
				return disc;
		}
		
		return null;
	}
	
	/**
	 * Pega todas as disciplinas da grade.
	 * @return Conjunto de disciplinas.
	 */
	public Set<Disciplina> getTodasDisciplinas() {
		return disciplinas;
	}
	
}
