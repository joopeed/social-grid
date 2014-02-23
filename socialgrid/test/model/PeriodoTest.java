package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.start;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PeriodoTest {

	private Grade grade;
	private Periodo primeiroPeriodo;
	
	@Before
	public void setUp() throws IOException {
		start(fakeApplication(inMemoryDatabase()));

		grade = new Grade();
		grade.preencheGrade();
		
		primeiroPeriodo = new Periodo();
		primeiroPeriodo.adicionaDisciplina(grade.getDisciplinaPorNome("cálculo diferencial e integral i"));
		primeiroPeriodo.adicionaDisciplina(grade.getDisciplinaPorNome("álgebra vetorial e geometria analítica"));
		primeiroPeriodo.adicionaDisciplina(grade.getDisciplinaPorNome("leitura e produção de textos"));
		primeiroPeriodo.adicionaDisciplina(grade.getDisciplinaPorNome("programação i"));
		primeiroPeriodo.adicionaDisciplina(grade.getDisciplinaPorNome("introdução à computação"));
		primeiroPeriodo.adicionaDisciplina(grade.getDisciplinaPorNome("laboratório de programação i"));		
	}
	
	@Test
	public void testaSeDisciplinasEstaoNoPeriodo() {
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		
		disciplinas.add(grade.getDisciplinaPorNome("cálculo diferencial e integral i"));
		disciplinas.add(grade.getDisciplinaPorNome("álgebra vetorial e geometria analítica"));
		disciplinas.add(grade.getDisciplinaPorNome("leitura e produção de textos"));
		disciplinas.add(grade.getDisciplinaPorNome("programação i"));
		disciplinas.add(grade.getDisciplinaPorNome("introdução à computação"));
		disciplinas.add(grade.getDisciplinaPorNome("laboratório de programação i"));
		
		assertTrue(disciplinas.equals(primeiroPeriodo.getDisciplinas()));
	}
	
	@Test
	public void adicionaDisciplina() {
		primeiroPeriodo.adicionaDisciplina(grade.getDisciplinaPorNome("inglês"));
		assertTrue(primeiroPeriodo.getDisciplinas().contains(grade.getDisciplinaPorNome("inglês")));
	}
	
	@Test
	public void removeDisciplina() {
		primeiroPeriodo.removeDisciplina(grade.getDisciplinaPorNome("cálculo diferencial e integral i"));
		assertFalse(primeiroPeriodo.getDisciplinas().contains(grade.getDisciplinaPorNome("inglês")));
	}
	
	@Test
	public void quantidadeDeCreditos() {
		assertEquals(24, primeiroPeriodo.getTotalDeCreditos());
	}
}
