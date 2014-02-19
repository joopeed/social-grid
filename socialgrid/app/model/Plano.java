package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implementa o plano de formação com períodos a serem preenchidos com disciplinas.
 */
public class Plano {
	
	private List<Periodo> periodos;
	private Grade grade;

	/**
	 * Construtor
	 * @param nova_grade grade com todas as disciplinas do período do curso.
	 */
	public Plano(Grade nova_grade) {
		grade = nova_grade;
		periodos = new ArrayList<Periodo>();
		for (int i = 0 ; i < 10 ; i++)
			periodos.add(new Periodo());
		iniciaPrePlano();
	}
	
	/**
	 * Inicia o plano com as disciplinas sugeridas.
	 */
	private void iniciaPrePlano() {		
		addDisciplina(grade.getDisciplinaPorNome("cálculo diferencial e integral i"), 0);
		addDisciplina(grade.getDisciplinaPorNome("álgebra vetorial e geometria analítica"), 0);
		addDisciplina(grade.getDisciplinaPorNome("leitura e produção de textos"), 0);
		addDisciplina(grade.getDisciplinaPorNome("programação i"), 0);
		addDisciplina(grade.getDisciplinaPorNome("introdução à computação"), 0);
		addDisciplina(grade.getDisciplinaPorNome("laboratório de programação i"), 0);
		
		addDisciplina(grade.getDisciplinaPorNome("cálculo diferencial e integral ii"), 1);
		addDisciplina(grade.getDisciplinaPorNome("programação ii"), 1);
		addDisciplina(grade.getDisciplinaPorNome("laboratório de programação ii"), 1);
		addDisciplina(grade.getDisciplinaPorNome("teoria dos grafos"), 1);
		addDisciplina(grade.getDisciplinaPorNome("matemática discreta"), 1);
		addDisciplina(grade.getDisciplinaPorNome("metodologia científica"), 1);
		addDisciplina(grade.getDisciplinaPorNome("fundamentos de física clássica"), 1);
		
		addDisciplina(grade.getDisciplinaPorNome("álgebra linear"), 2);
		addDisciplina(grade.getDisciplinaPorNome("probabilidade e estatística"), 2);
		addDisciplina(grade.getDisciplinaPorNome("teoria da computação"), 2);
		addDisciplina(grade.getDisciplinaPorNome("estruturas de dados e algoritmos"), 2);
		addDisciplina(grade.getDisciplinaPorNome("fundamentos de física moderna"), 2);
		addDisciplina(grade.getDisciplinaPorNome("gerência da informação"), 2);
		addDisciplina(grade.getDisciplinaPorNome("laboratório de estruturas de dados e algoritmos"), 2);
		
		addDisciplina(grade.getDisciplinaPorNome("métodos estatísticos"), 3);
		addDisciplina(grade.getDisciplinaPorNome("paradigmas de linguagens de programação"), 3);
		addDisciplina(grade.getDisciplinaPorNome("lógica matemática"), 3);
		addDisciplina(grade.getDisciplinaPorNome("organização e arquitetura de computadores i"), 3);
		addDisciplina(grade.getDisciplinaPorNome("engenharia de software i"), 3);
		addDisciplina(grade.getDisciplinaPorNome("sistemas de informação i"), 3);
		addDisciplina(grade.getDisciplinaPorNome("laboratório de organização e arquitetura de computadores"), 3);
		
		addDisciplina(grade.getDisciplinaPorNome("informática e sociedade"), 4);
		addDisciplina(grade.getDisciplinaPorNome("análise e técnicas de algoritmos"), 4);
		addDisciplina(grade.getDisciplinaPorNome("compiladores"), 4);
		addDisciplina(grade.getDisciplinaPorNome("redes de computadores"), 4);
		addDisciplina(grade.getDisciplinaPorNome("bancos de dados i"), 4);
		addDisciplina(grade.getDisciplinaPorNome("sistemas de informação ii"), 4);
		addDisciplina(grade.getDisciplinaPorNome("laboratório de engenharia de software"), 4);
		
		addDisciplina(grade.getDisciplinaPorNome("direito e cidadania"), 5);
		addDisciplina(grade.getDisciplinaPorNome("sistemas operacionais"), 5);
		addDisciplina(grade.getDisciplinaPorNome("interconexão de redes de computadores"), 5);
		addDisciplina(grade.getDisciplinaPorNome("banco de dados ii"), 5);
		addDisciplina(grade.getDisciplinaPorNome("inteligência artificial i"), 5);
		addDisciplina(grade.getDisciplinaPorNome("laboratório de interconexão de redes de computadores"), 5);
		
		addDisciplina(grade.getDisciplinaPorNome("métodos e software numéricos"), 6);
		addDisciplina(grade.getDisciplinaPorNome("avaliação de desempenho de sistemas discretos"), 6);
		addDisciplina(grade.getDisciplinaPorNome("projeto em computação i"), 6);
		
		addDisciplina(grade.getDisciplinaPorNome("projeto em computação ii"), 7);
		
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
	 * Aloca uma disciplina para um período.
	 * @param disciplina Disciplina à ser alocada.
	 * @param idxPeriodo Índice do período que recebe-rá a disciplina.
	 */
	public void addDisciplina(Disciplina disciplina, int idxPeriodo) {
		boolean temRequisitos = true;
		for (Disciplina requisito: disciplina.getRequisitos()) {
			temRequisitos &= temDisciplinaAlocada(requisito, idxPeriodo);
		}
		if(temRequisitos) periodos.get(idxPeriodo).adicionaDisciplina(disciplina);
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
}
