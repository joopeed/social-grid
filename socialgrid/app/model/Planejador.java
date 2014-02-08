package model;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class Planejador {

	private Grade grade;
	private Plano plano;
	
	
	public Planejador() throws IOException {
		grade = Grade.getInstancia();
		plano = new Plano();
	}


	public Set<Disciplina> getDisciplinasAlocadas() {
		return plano.getDisciplinasAlocadas();
	}

	public void adicionaDisciplina(String nome, int idxPeriodo) {
		plano.adicionaDisciplina(grade.getDisciplinaPorNome(nome), idxPeriodo);
	}

	public void removeDisciplina(String nome, int idxPeriodo) {
		plano.removeDisciplina(grade.getDisciplinaPorNome(nome), idxPeriodo);
	}

}
