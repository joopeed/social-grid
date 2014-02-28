package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import play.db.ebean.Model;

/**
 * Implementa um período do sistema.
 */
@Entity
public class Periodo extends Model {
	
	private static final long serialVersionUID = 4715181805704107893L;
	
	@Id
	public Long id;
	@ManyToMany(cascade = CascadeType.ALL)
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
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
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

	/**
	 * Pega quantidade total de créditos alocados no período.
	 * @return Total de créditos.
	 */
	public int getTotalDeCreditos() {
		int totalDeCreditos = 0;
		
		for (Disciplina disciplina: disciplinas) {
			totalDeCreditos += disciplina.getCreditos();
		}
		
		return totalDeCreditos;
	}
	
}
