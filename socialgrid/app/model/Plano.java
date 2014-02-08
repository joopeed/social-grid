package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Plano {
	
	private List<Periodo> periodos;
	
	
	public Plano() {
		periodos = new ArrayList<Periodo>();
		// preencher períodos
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


	public void adicionaDisciplina(String nome, int idxPeriodo) {
		// TODO Auto-generated method stub
		
	}

	public void removeDisciplina(String nome, int idxPeriodo) {
		// TODO Auto-generated method stub
		
	}

}
