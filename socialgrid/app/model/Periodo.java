package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Periodo {
	
	List<Disciplina> disciplinas;
	
	public Periodo(){
		disciplinas = new ArrayList<Disciplina>();
		
	}

	public Set<Disciplina> getDisciplinas() {
		Set<Disciplina> disciplinasSet = new HashSet<Disciplina>(disciplinas);
		return disciplinasSet;
		
	}

	public void adicionaDisciplina(Disciplina disciplina) {
		disciplinas.add(disciplina);
		
	}

	public void removeDisciplina(Disciplina disciplina) {
		disciplinas.remove(disciplina);
		
	}

}
