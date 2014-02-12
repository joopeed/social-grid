package model;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Grade {

	private Set<Disciplina> disciplinas;

	
	public Grade() throws IOException {
		disciplinas = new HashSet<Disciplina>();
		preencheGrade();
	}

	
	private void preencheGrade() throws IOException {
		Carregador carregador = new Carregador();
		disciplinas = carregador.preencheGrade();
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
