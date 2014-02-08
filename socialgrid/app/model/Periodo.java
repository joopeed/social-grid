package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Periodo {
	
	private List<Disciplina> disciplinas;
	
	public Periodo(){
		disciplinas = new ArrayList<Disciplina>();
	}

	public Set<Disciplina> getDisciplinas() {
		return new HashSet<Disciplina>(disciplinas);
	}

	public void adicionaDisciplina(Disciplina disciplina) {
		disciplinas.add(disciplina);
	}

	public void removeDisciplina(Disciplina disciplina) {
		disciplinas.remove(disciplina);
	}

}
