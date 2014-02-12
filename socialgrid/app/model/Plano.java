package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Plano {
	
	private List<Periodo> periodos;
	private Grade grade;
	
	
	public Plano(Grade nova_grade) {
		grade = nova_grade;
		periodos = new ArrayList<Periodo>();
		for (int i = 0 ; i < 10 ; i++)
			periodos.add(new Periodo());
		iniciaPrePlano();
	}
	
	
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
	
	public Set<Disciplina> getDisciplinasAlocadas() {
		Set<Disciplina> disciplinas = new HashSet<Disciplina>();
		for (Periodo periodo: periodos) {
			for (Disciplina disciplina: periodo.getDisciplinas()) {
				disciplinas.add(disciplina);
			}
		}
		
		return disciplinas;
	}

	public void addDisciplina(Disciplina disciplina, int idxPeriodo) {
		periodos.get(idxPeriodo).adicionaDisciplina(disciplina);
	}

	public void removeDisciplina(Disciplina disciplina, int idxPeriodo) {
		periodos.get(idxPeriodo).removeDisciplina(disciplina);
	}
}
