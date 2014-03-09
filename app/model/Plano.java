package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import play.db.ebean.Model;

/**
 * Implementa o plano de formação com períodos a serem preenchidos com disciplinas.
 */
@Entity
public class Plano extends Model {
	
	private static final long serialVersionUID = 5092375276705062916L;
	
	@Id
	public Long id;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Periodo> periodos;
	@OneToOne
	private Grade grade;
	private int qntPeriodos;
	
	/**
	 * Construtor
	 * @param nova_grade grade com todas as disciplinas do período do curso.
	 */
	public Plano(Grade nova_grade) {
		grade = nova_grade;
		periodos = new ArrayList<Periodo>();
	}
	
	/**
	 * Inicia o plano com as disciplinas sugeridas.
	 */
	public void iniciaPrePlano() {
		qntPeriodos = 10;
		for (int i = 0 ; i < qntPeriodos ; i++)
			periodos.add(new Periodo());
		
		addDisciplina(grade.getDisciplinaPorNome("Cálculo Diferencial e Integral I"), 0);
		addDisciplina(grade.getDisciplinaPorNome("Álgebra Vetorial e Geometria Analítica"), 0);
		addDisciplina(grade.getDisciplinaPorNome("Leitura e Produção de Textos"), 0);
		addDisciplina(grade.getDisciplinaPorNome("Programação I"), 0);
		addDisciplina(grade.getDisciplinaPorNome("Introdução à Computação"), 0);
		addDisciplina(grade.getDisciplinaPorNome("Laboratório de Programação I"), 0);
		
		addDisciplina(grade.getDisciplinaPorNome("Cálculo Diferencial e Integral II"), 1);
		addDisciplina(grade.getDisciplinaPorNome("Programação II"), 1);
		addDisciplina(grade.getDisciplinaPorNome("Laboratório de Programação II"), 1);
		addDisciplina(grade.getDisciplinaPorNome("Teoria dos Grafos"), 1);
		addDisciplina(grade.getDisciplinaPorNome("Matemática Discreta"), 1);
		addDisciplina(grade.getDisciplinaPorNome("Metodologia Científica"), 1);
		addDisciplina(grade.getDisciplinaPorNome("Fundamentos de Física Clássica"), 1);
		
		addDisciplina(grade.getDisciplinaPorNome("Álgebra Linear I"), 2);
		addDisciplina(grade.getDisciplinaPorNome("Probabilidade e Estatística"), 2);
		addDisciplina(grade.getDisciplinaPorNome("Teoria da Computação"), 2);
		addDisciplina(grade.getDisciplinaPorNome("Estrutura de Dados e Algoritmos"), 2);
		addDisciplina(grade.getDisciplinaPorNome("Fundamentos de Física Moderna"), 2);
		addDisciplina(grade.getDisciplinaPorNome("Gerência da Informação"), 2);
		addDisciplina(grade.getDisciplinaPorNome("Laboratório de Estrutura de Dados e Algoritmos"), 2);
		
		addDisciplina(grade.getDisciplinaPorNome("Métodos Estatísticos"), 3);
		addDisciplina(grade.getDisciplinaPorNome("Paradigmas de Linguagem de Programação"), 3);
		addDisciplina(grade.getDisciplinaPorNome("Lógica Matemática"), 3);
		addDisciplina(grade.getDisciplinaPorNome("Organização e Arquitetura de Computadores I"), 3);
		addDisciplina(grade.getDisciplinaPorNome("Engenharia de Software I"), 3);
		addDisciplina(grade.getDisciplinaPorNome("Sistemas de Informação I"), 3);
		addDisciplina(grade.getDisciplinaPorNome("Laboratório de Organização e Arquitetura de Computadores"), 3);
		
		addDisciplina(grade.getDisciplinaPorNome("Informática e Sociedade"), 4);
		addDisciplina(grade.getDisciplinaPorNome("Análise e Técnica de Algoritmos"), 4);
		addDisciplina(grade.getDisciplinaPorNome("Compiladores"), 4);
		addDisciplina(grade.getDisciplinaPorNome("Redes de Computadores"), 4);
		addDisciplina(grade.getDisciplinaPorNome("Banco de Dados I"), 4);
		addDisciplina(grade.getDisciplinaPorNome("Sistemas de Informação II"), 4);
		addDisciplina(grade.getDisciplinaPorNome("Laboratório de Engenharia de Software"), 4);
		
		addDisciplina(grade.getDisciplinaPorNome("Direito e Cidadania"), 5);
		addDisciplina(grade.getDisciplinaPorNome("Sistemas Operacionais"), 5);
		addDisciplina(grade.getDisciplinaPorNome("Interconexão de Redes de Computadores"), 5);
		addDisciplina(grade.getDisciplinaPorNome("Banco de Dados II"), 5);
		addDisciplina(grade.getDisciplinaPorNome("Inteligência Artificial I"), 5);
		addDisciplina(grade.getDisciplinaPorNome("Laboratório de Interconexão de Redes de Computadores"), 5);
		
		addDisciplina(grade.getDisciplinaPorNome("Métodos e Software Numéricos"), 6);
		addDisciplina(grade.getDisciplinaPorNome("Avaliação de Desempenho de Sistemas Discretos"), 6);
		addDisciplina(grade.getDisciplinaPorNome("Projeto em Computação I"), 6);
		
		addDisciplina(grade.getDisciplinaPorNome("Projeto em Computação II"), 7);

	}
	
	/**
	 * Pega todas as disciplinas já alocadas no sistema.
	 * @return Conjunto com as disciplinas alucadas.
	 */
	public Set<Disciplina> getDisciplinasAlocadas() {
		Set<Disciplina> disciplinas = new HashSet<Disciplina>();
		for (Periodo periodo: periodos) {
			for (Disciplina disciplina: periodo.getDisciplinas()) {
				disciplinas.add(disciplina);
			}
		}
		
		return disciplinas;
	}
	
	/**
	 * Pega as disciplinas da grade que não estão alocadas.
	 * @return Disciplinas não alocadas.
	 */
	public List<Disciplina> getDisciplinasOfertadas() {
		List<Disciplina> disciplinasOfertadas = grade.getTodasDisciplinas();
		
		for (Disciplina alocada: getDisciplinasAlocadas()) {
			if (disciplinasOfertadas.contains(alocada)) {
				disciplinasOfertadas.remove(alocada);
			}
		}
		
		return disciplinasOfertadas;
	} 

	/**
	 * Aloca uma disciplina para um período.
	 * @param disciplina Disciplina à ser alocada.
	 * @param idxPeriodo Índice do período que recebe-rá a disciplina.
	 */
	public void addDisciplina(Disciplina disciplina, int idxPeriodo) {
		periodos.get(idxPeriodo).adicionaDisciplina(disciplina);
	}
	
	
	
	private boolean temRequisitos(Disciplina disciplina, int  idxPeriodo) {
		boolean temRequisitos = true;
		for (Disciplina requisito: disciplina.getRequisitos()) {
			temRequisitos &= temDisciplinaAlocada(requisito, idxPeriodo);
		}
		return temRequisitos;
	}

	public boolean temDisciplinaIncorreta(int idxPeriodo) {
		boolean temTodosRequisitos = true;
		for(Disciplina disciplina: periodos.get(idxPeriodo).getDisciplinas()){
			temTodosRequisitos &= temRequisitos(disciplina, idxPeriodo);
		}
		return !temTodosRequisitos;
	}
	
	public boolean estaIncorreta(Disciplina disciplina, int idxPeriodo) {
		return !temRequisitos(disciplina, idxPeriodo);
		
	}
	

	private boolean temDisciplinaAlocada(Disciplina disciplina, int idxPeriodo) {
		for (int i = 0; i < idxPeriodo; i++) {
			Periodo periodo = periodos.get(i);
			if (periodo.contemDisciplina(disciplina)) {
				return true;
			}
		}
		return false;
	}


	/**
	 * Remove uma disciplina alocada.
	 * @param disciplina Disciplina à ser removida.
	 */
	public void removeDisciplina(Disciplina disciplina) {
		for (Disciplina dependente: disciplina.getDependentes()) {
			removeDisciplina(dependente);
		}
		desalocaDisciplina(disciplina);
	}
	
	private void desalocaDisciplina(Disciplina disciplina) {
		for (Periodo periodo: periodos) {
			if (periodo.contemDisciplina(disciplina)) {
				periodo.removeDisciplina(disciplina);
				return;
			}
		}
	}

	/**
	 * Calcula a diferença entre os planos.
	 * @param plano2 Plano à ser comparado.
	 * @return Direrença entre os planos.
	 */
	public int getDiferencaDePlanos(Plano plano2) {
		int diferenca = 0;
		Set<Disciplina> maisDisciplinas;
		Set<Disciplina> menosDisciplinas;
		
		if (getDisciplinasAlocadas().size() > plano2.getDisciplinasAlocadas().size()) {
			maisDisciplinas = getDisciplinasAlocadas();
			menosDisciplinas = plano2.getDisciplinasAlocadas();
		} else {
			maisDisciplinas = plano2.getDisciplinasAlocadas();
			menosDisciplinas = getDisciplinasAlocadas();
		}
		
		for (Disciplina disciplina: maisDisciplinas) {
			if (!menosDisciplinas.contains(disciplina)) {
				diferenca++;
			}
		}

		return diferenca;
	}
	
	public List<Periodo> getPeriodos() {
		return periodos;
	}

}
