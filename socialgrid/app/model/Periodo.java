package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implementa um período do sistema.
 */
public class Periodo {
	
	private List<Disciplina> disciplinas;
	
	/**
	 * Construtor
	 */
	public Periodo(){
		disciplinas = new ArrayList<Disciplina>();
	}

	/**
	 * Pega as disciplinas do período.
	 * @return Conjunto de disciplinas no período.
	 */
	public Set<Disciplina> getDisciplinas() {
		return new HashSet<Disciplina>(disciplinas);
	}

	/**
	 * Adiciona uma disciplina ao período.
	 * @param disciplina Disciplina à ser adicionada.
	 */
	public void adicionaDisciplina(Disciplina disciplina) {
		disciplinas.add(disciplina);
	}

	/**
	 * Remove uma disciplina específica do período.
	 * @param disciplina Disciplina à ser removeida.
	 */
	public void removeDisciplina(Disciplina disciplina) {
		disciplinas.remove(disciplina);
	}

	/**
	 * Verifica se uma disciplina está no período.
	 * @param disciplina Disciplina à ser procurada
	 * @return True se a disciplina está no período. False caso contrário.
	 */
	public boolean contemDisciplina(Disciplina disciplina) {
		return disciplinas.contains(disciplina);
	}

}
