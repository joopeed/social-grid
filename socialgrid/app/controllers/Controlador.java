package controllers;

import java.io.IOException;
import java.util.Set;

import model.Disciplina;
import model.Planejador;

public class Controlador {
	private Planejador planejador;
	
	public Controlador() throws IOException {
		planejador = new Planejador();
	}
	
	public Set<Disciplina> getDisciplinasAlocadas() {
		return planejador.getDisciplinasAlocadas();
	}

	public void adicionaDisciplina(String nome, int idxPeriodo) {
		planejador.adicionaDisciplina(nome, idxPeriodo);
	}

	public void removeDisciplina(String nome, int idxPeriodo) {
		planejador.removeDisciplina(nome, idxPeriodo);
		
	}
	
}
