package model;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Grade {

	private Set<Disciplina> disciplinas;
	
	/**
	 * Construtor do classe
	 * @throws IOException
	 * 		se o arquivo de disciplina não foi encontrado ou
	 * 		apresenta algum erro
	 */
	public Grade() throws IOException {
		disciplinas = new HashSet<Disciplina>();
		preencheGrade();
	}

	/**
	 * Popula a grade de acordo com um arquivo
	 * @throws IOException
	 * 		se o arquivo de disciplina não foi encontrado ou
	 * 		apresenta algum erro
	 */
	private void preencheGrade() throws IOException {
		Carregador carregador = new Carregador();
		carregador.preencheGrade(disciplinas);
	}

	public Disciplina buscaDisciplinaPorNome(String string) {
		return null;
	}
	
}
