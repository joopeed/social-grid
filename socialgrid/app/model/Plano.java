package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Plano {
	
	private List<Periodo> periodos;
	
	
	public Plano() {
		periodos = new ArrayList<Periodo>();
		for (int i = 0 ; i < 10 ; i++)
			periodos.add(new Periodo());
	}
	
	
	public Set<Disciplina> getDisciplinasAlocadas() {
		Set<Disciplina> disciplinas = new HashSet<Disciplina>();
		for (Periodo periodo: periodos) {
			for (Disciplina disciplina: periodo.getDisciplinas()) {
				disciplinas.add(disciplina);
			}
		}
		
		return disciplinas;
	}

	public void adicionaDisciplina(Disciplina disciplina, int idxPeriodo) {
		periodos.get(idxPeriodo).adicionaDisciplina(disciplina);
	}

	public void removeDisciplina(Disciplina disciplina, int idxPeriodo) {
		periodos.get(idxPeriodo).removeDisciplina(disciplina);
	}
}
