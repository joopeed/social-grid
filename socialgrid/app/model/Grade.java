package model;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Grade {

	private static Grade GRADE_UNICA = null;
	private Set<Disciplina> disciplinas;
	
	public static Grade getInstancia()  throws IOException {
		if (GRADE_UNICA == null) {
			GRADE_UNICA = new Grade();
		}
		return GRADE_UNICA;
	}
	
	/**
	 * Construtor do classe
	 * @throws IOException
	 * 		se o arquivo de disciplina não foi encontrado ou
	 * 		apresenta algum erro
	 */
	private Grade() throws IOException {
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

	public Disciplina getDisciplinaPorNome(String nome) {
		Disciplina disciplina;
		
		for(Disciplina disc: disciplinas) {
			if (disc.getNome().equals(nome))
				return disc;
		}
		
		return null;
	}
	
	public Set<Disciplina> getTodasDisciplinas() {
		return disciplinas;
	}
	
}
