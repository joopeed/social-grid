package model;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import controllers.Controlador;

public class SistemaTest {
	
	private Grade grade;
	private Controlador controlador;
	
	@Before
	public void setUp() throws IOException {
		grade = Grade.getInstancia();
		controlador = new Controlador();
	}
	
	@Test
	public void iniciaPlanejamento_SemRequisitos() throws IOException {		
		Set<Disciplina> disciplinas = new HashSet<Disciplina>();
		Set<Disciplina> todasDisciplinas = controlador.getDisciplinasAlocadas();
		
		disciplinas.add(grade.getDisciplinaPorNome("cálculo diferencial e integral i"));
		disciplinas.add(grade.getDisciplinaPorNome("álgebra vetorial e geometria analítica"));
		disciplinas.add(grade.getDisciplinaPorNome("leitura e produção de textos"));
		disciplinas.add(grade.getDisciplinaPorNome("programação i"));
		disciplinas.add(grade.getDisciplinaPorNome("introdução à computação"));
		disciplinas.add(grade.getDisciplinaPorNome("laboratório de programação i"));
		disciplinas.add(grade.getDisciplinaPorNome("cálculo diferencial e integral ii"));
		disciplinas.add(grade.getDisciplinaPorNome("programação ii"));
		disciplinas.add(grade.getDisciplinaPorNome("laboratório de programação ii"));
		disciplinas.add(grade.getDisciplinaPorNome("teoria dos grafos"));
		disciplinas.add(grade.getDisciplinaPorNome("matemática discreta"));
		disciplinas.add(grade.getDisciplinaPorNome("metodologia científica"));
		disciplinas.add(grade.getDisciplinaPorNome("fundamentos de física clássica"));
		disciplinas.add(grade.getDisciplinaPorNome("álgebra linear"));
		disciplinas.add(grade.getDisciplinaPorNome("probabilidade e estatística"));
		disciplinas.add(grade.getDisciplinaPorNome("teoria da computação"));
		disciplinas.add(grade.getDisciplinaPorNome("estruturas de dados e algoritmos"));
		disciplinas.add(grade.getDisciplinaPorNome("fundamentos de física moderna"));
		disciplinas.add(grade.getDisciplinaPorNome("gerência da informação"));
		disciplinas.add(grade.getDisciplinaPorNome("laboratório de estruturas de dados e algoritmos"));
		disciplinas.add(grade.getDisciplinaPorNome("métodos estatísticos"));
		disciplinas.add(grade.getDisciplinaPorNome("paradigmas de linguagens de programação"));
		disciplinas.add(grade.getDisciplinaPorNome("lógica matemática"));
		disciplinas.add(grade.getDisciplinaPorNome("organização e arquitetura de computadores i"));
		disciplinas.add(grade.getDisciplinaPorNome("engenharia de software i"));
		disciplinas.add(grade.getDisciplinaPorNome("sistemas de informação i"));
		disciplinas.add(grade.getDisciplinaPorNome("laboratório de organização e arquitetura de computadores"));
		disciplinas.add(grade.getDisciplinaPorNome("informática e sociedade"));
		disciplinas.add(grade.getDisciplinaPorNome("análise e técnicas de algoritmos"));
		disciplinas.add(grade.getDisciplinaPorNome("compiladores"));
		disciplinas.add(grade.getDisciplinaPorNome("redes de computadores"));
		disciplinas.add(grade.getDisciplinaPorNome("bancos de dados i"));
		disciplinas.add(grade.getDisciplinaPorNome("sistemas de informação ii"));
		disciplinas.add(grade.getDisciplinaPorNome("laboratório de engenharia de software"));
		disciplinas.add(grade.getDisciplinaPorNome("direito e cidadania"));
		disciplinas.add(grade.getDisciplinaPorNome("sistemas operacionais"));
		disciplinas.add(grade.getDisciplinaPorNome("interconexão de redes de computadores"));
		disciplinas.add(grade.getDisciplinaPorNome("banco de dados ii"));
		disciplinas.add(grade.getDisciplinaPorNome("inteligência artificial i"));
		disciplinas.add(grade.getDisciplinaPorNome("laboratório de interconexão de redes de computadores"));
		disciplinas.add(grade.getDisciplinaPorNome("métodos e software numéricos"));
		disciplinas.add(grade.getDisciplinaPorNome("avaliação de desempenho de sistemas discretos"));
		disciplinas.add(grade.getDisciplinaPorNome("projeto em computação i"));
		disciplinas.add(grade.getDisciplinaPorNome("projeto em computação ii"));		
		
		assertTrue(disciplinas.equals(todasDisciplinas));
	}

	@Test
	public void adicionaDisciplinaNoPlanejamento_SemRequisitos() {
		assertFalse(controlador.getDisciplinasAlocadas().contains(grade.getDisciplinaPorNome("engenharia de software ii")));
		controlador.adicionaDisciplina("engenharia de software ii", 1);
		assertTrue(controlador.getDisciplinasAlocadas().contains(grade.getDisciplinaPorNome("engenharia de software ii")));
	}
	
	@Test
	public void removeDisciplinaDoPlanejamento_SemRequisitos() {
		assertTrue(controlador.getDisciplinasAlocadas().contains(grade.getDisciplinaPorNome("cálculo diferencial e integral i")));
		controlador.removeDisciplina("cálculo diferencial e integral i", 0);
		assertFalse(controlador.getDisciplinasAlocadas().contains(grade.getDisciplinaPorNome("cálculo diferencial e integral i")));
		assertTrue(controlador.getDisciplinasAlocadas().contains(grade.getDisciplinaPorNome("cálculo diferencial e integral ii")));
	}
}
