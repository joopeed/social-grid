package controllers;

import java.io.IOException;
import java.util.Set;

import model.Disciplina;
import model.Grade;
import model.Plano;

public class Controlador {
	private Grade grade;
	private Plano plano;
	
	
	public Controlador() throws IOException {
		grade = new Grade();
		plano = new Plano(grade);
	}
	
	
	public Disciplina getDisciplinaPorNome(String nome) {
		return grade.getDisciplinaPorNome(nome);
	}
	
	public Set<Disciplina> getDisciplinasAlocadas() {
		return plano.getDisciplinasAlocadas();
	}

	public void addDisciplina(String nome, int idxPeriodo) {
		plano.addDisciplina(grade.getDisciplinaPorNome(nome), idxPeriodo);
	}

	public void removeDisciplina(String nome, int idxPeriodo) {
		plano.removeDisciplina(grade.getDisciplinaPorNome(nome), idxPeriodo);
		
	}
	
}
