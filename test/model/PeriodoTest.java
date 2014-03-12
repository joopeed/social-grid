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

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

public class PeriodoTest {

	private Grade grade;
	private Periodo primeiroPeriodo;
	
	@Before
	public void setUp() throws IOException, ParserConfigurationException, SAXException {
		start(fakeApplication(inMemoryDatabase()));

		grade = new Grade();
		grade.preencheGrade();
		
		primeiroPeriodo = new Periodo();
		primeiroPeriodo.adicionaDisciplina(grade.getDisciplinaPorNome("Cálculo Diferencial e Integral I"));
		primeiroPeriodo.adicionaDisciplina(grade.getDisciplinaPorNome("Álgebra Vetorial e Geometria Analítica"));
		primeiroPeriodo.adicionaDisciplina(grade.getDisciplinaPorNome("Leitura e Produção de Textos"));
		primeiroPeriodo.adicionaDisciplina(grade.getDisciplinaPorNome("Programação I"));
		primeiroPeriodo.adicionaDisciplina(grade.getDisciplinaPorNome("Introdução à Computação"));
		primeiroPeriodo.adicionaDisciplina(grade.getDisciplinaPorNome("Laboratório de Programação II"));		
	}
	
	@Test
	public void testaSeDisciplinasEstaoNoPeriodo() {
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		
		disciplinas.add(grade.getDisciplinaPorNome("Cálculo Diferencial e Integral I"));
		disciplinas.add(grade.getDisciplinaPorNome("Álgebra Vetorial e Geometria Analítica"));
		disciplinas.add(grade.getDisciplinaPorNome("Leitura e Produção de Textos"));
		disciplinas.add(grade.getDisciplinaPorNome("Programação I"));
		disciplinas.add(grade.getDisciplinaPorNome("Introdução à Computação"));
		disciplinas.add(grade.getDisciplinaPorNome("Laboratório de Programação II"));
		
		assertTrue(disciplinas.equals(primeiroPeriodo.getDisciplinas()));
	}
	
	@Test
	public void adicionaDisciplina() {
		primeiroPeriodo.adicionaDisciplina(grade.getDisciplinaPorNome("Inglês"));
		assertTrue(primeiroPeriodo.getDisciplinas().contains(grade.getDisciplinaPorNome("Inglês")));
	}
	
	@Test
	public void naoAlocaAcimaDoMaximo() {
		primeiroPeriodo.adicionaDisciplina(grade.getDisciplinaPorNome("Inglês"));
		primeiroPeriodo.adicionaDisciplina(grade.getDisciplinaPorNome("Economia"));
		assertTrue(primeiroPeriodo.getDisciplinas().contains(grade.getDisciplinaPorNome("Inglês")));
		assertFalse(primeiroPeriodo.getDisciplinas().contains(grade.getDisciplinaPorNome("Economia")));
	}
	
	@Test
	public void alocaSemRestricao() {
		RegraDeAlocacao novaRegra = new SemRestricoes();
		primeiroPeriodo.setRegraDeAlocacao(novaRegra);
		primeiroPeriodo.adicionaDisciplina(grade.getDisciplinaPorNome("Inglês"));
		primeiroPeriodo.adicionaDisciplina(grade.getDisciplinaPorNome("Economia"));
		assertTrue(primeiroPeriodo.getDisciplinas().contains(grade.getDisciplinaPorNome("Inglês")));
		assertTrue(primeiroPeriodo.getDisciplinas().contains(grade.getDisciplinaPorNome("Economia")));
	}
	
	@Test
	public void removeDisciplina() {
		primeiroPeriodo.removeDisciplina(grade.getDisciplinaPorNome("Cálculo Diferencial e Integral I"));
		assertFalse(primeiroPeriodo.getDisciplinas().contains(grade.getDisciplinaPorNome("Cálculo Diferencial e Integral I")));
	}
	
	@Test
	public void quantidadeDeCreditos() {
		assertEquals(24, primeiroPeriodo.getTotalDeCreditos());
	}

	@Test
	public void dificuldadeDoPeriodo() {
		assertEquals(0, primeiroPeriodo.getDificuldadeTotal(), 0);
		
		Usuario usuarioA = new Usuario("Foo", "foo@gmail.com", "123456", new Plano(null));
		Usuario usuarioB = new Usuario("Boo", "boo@gmail.com", "654321", new Plano(null));

		grade.getDisciplinaPorNome("Cálculo Diferencial e Integral I").addDificuldade(usuarioA, 3);
		grade.getDisciplinaPorNome("Programação I").addDificuldade(usuarioB, 2);
		
		assertEquals(5, primeiroPeriodo.getDificuldadeTotal(), 0);
	}
	
	@Test
	public void disciplinaEstaNoPeriodo() {
		assertTrue(primeiroPeriodo.disciplinaEstaNoPeriodo(grade.getDisciplinaPorNome("Programação I")));
		assertFalse(primeiroPeriodo.disciplinaEstaNoPeriodo(grade.getDisciplinaPorNome("Programação II")));
	}
}
