package model;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class Planejador {

	private Grade grade;
	private Plano plano;
	
	
	public Planejador() throws IOException {
		grade = Grade.getInstancia();
		plano = new Plano();
		iniciaPrePlano();
	}


	private void iniciaPrePlano() {
		adicionaDisciplina("cálculo diferencial e integral i", 0);
		adicionaDisciplina("álgebra vetorial e geometria analítica", 0);
		adicionaDisciplina("leitura e produção de textos", 0);
		adicionaDisciplina("programação i", 0);
		adicionaDisciplina("introdução à computação", 0);
		adicionaDisciplina("laboratório de programação i", 0);
		
		adicionaDisciplina("cálculo diferencial e integral ii", 1);
		adicionaDisciplina("programação ii", 1);
		adicionaDisciplina("laboratório de programação ii", 1);
		adicionaDisciplina("teoria dos grafos", 1);
		adicionaDisciplina("matemática discreta", 1);
		adicionaDisciplina("metodologia científica", 1);
		adicionaDisciplina("fundamentos de física clássica", 1);
		
		adicionaDisciplina("álgebra linear", 2);
		adicionaDisciplina("probabilidade e estatística", 2);
		adicionaDisciplina("teoria da computação", 2);
		adicionaDisciplina("estruturas de dados e algoritmos", 2);
		adicionaDisciplina("fundamentos de física moderna", 2);
		adicionaDisciplina("gerência da informação", 2);
		adicionaDisciplina("laboratório de estruturas de dados e algoritmos", 2);
		
		adicionaDisciplina("métodos estatísticos", 3);
		adicionaDisciplina("paradigmas de linguagens de programação", 3);
		adicionaDisciplina("lógica matemática", 3);
		adicionaDisciplina("organização e arquitetura de computadores i", 3);
		adicionaDisciplina("engenharia de software i", 3);
		adicionaDisciplina("sistemas de informação i", 3);
		adicionaDisciplina("laboratório de organização e arquitetura de computadores", 3);
		
		adicionaDisciplina("informática e sociedade", 4);
		adicionaDisciplina("análise e técnicas de algoritmos", 4);
		adicionaDisciplina("compiladores", 4);
		adicionaDisciplina("redes de computadores", 4);
		adicionaDisciplina("bancos de dados i", 4);
		adicionaDisciplina("sistemas de informação ii", 4);
		adicionaDisciplina("laboratório de engenharia de software", 4);
		
		adicionaDisciplina("direito e cidadania", 5);
		adicionaDisciplina("sistemas operacionais", 5);
		adicionaDisciplina("interconexão de redes de computadores", 5);
		adicionaDisciplina("banco de dados ii", 5);
		adicionaDisciplina("inteligência artificial i", 5);
		adicionaDisciplina("laboratório de interconexão de redes de computadores", 5);
		
		adicionaDisciplina("métodos e software numéricos", 6);
		adicionaDisciplina("avaliação de desempenho de sistemas discretos", 6);
		adicionaDisciplina("projeto em computação i", 6);
		
		adicionaDisciplina("projeto em computação ii", 7);
	}

	public Set<Disciplina> getDisciplinasAlocadas() {
		return plano.getDisciplinasAlocadas();
	}

	public void adicionaDisciplina(String nome, int idxPeriodo) {
		plano.adicionaDisciplina(grade.getDisciplinaPorNome(nome), idxPeriodo);
	}

	public void removeDisciplina(String nome, int idxPeriodo) {
		plano.removeDisciplina(grade.getDisciplinaPorNome(nome), idxPeriodo);
	}

}
