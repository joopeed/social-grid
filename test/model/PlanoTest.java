package model;

import static org.junit.Assert.*;
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

public class PlanoTest {

	private Plano plano1;
	private Grade grade;
	
	@Before
	public void SetUp() throws IOException, ParserConfigurationException, SAXException {
		start(fakeApplication(inMemoryDatabase()));
		
		grade = new Grade();
		grade.preencheGrade();
		plano1 = new Plano(grade);
		plano1.iniciaPrePlano();
	}
	
	@Test
	public void comparaDeferencasEntrePlanos() {
		Plano plano2 = new Plano(grade);
		plano2.iniciaPrePlano();
		
		assertEquals(0, plano1.getDiferencaDePlanos(plano2));
		
		plano2.removeDisciplina(grade.getDisciplinaPorNome("Projeto em Computação I"));
		assertEquals(2, plano1.getDiferencaDePlanos(plano2));
		
		plano2.addDisciplina(grade.getDisciplinaPorNome("Projeto em Computação I"), 6);
		assertEquals(1, plano1.getDiferencaDePlanos(plano2));
		
		plano2.addDisciplina(grade.getDisciplinaPorNome("Projeto em Computação II"), 7);
		plano2.addDisciplina(grade.getDisciplinaPorNome("Inglês"), 7);
		assertEquals(1, plano1.getDiferencaDePlanos(plano2));
	}

	@Test
	public void disciplinasOfertadas() {
		List<Disciplina> naoAlocadas = new ArrayList<Disciplina>();
		naoAlocadas.add(grade.getDisciplinaPorNome("Inglês"));
		naoAlocadas.add(grade.getDisciplinaPorNome("Engenharia de Software II"));
		
		assertTrue(plano1.getDisciplinasOfertadas().containsAll(naoAlocadas));
	}

	@Test
	public void mudarDisciplinaDePeriodo() {
		assertTrue(plano1.getPeriodos().get(6).contemDisciplina(grade.getDisciplinaPorNome("Métodos e Software Numéricos")));
		
		plano1.addDisciplina(grade.getDisciplinaPorNome("Métodos e Software Numéricos"), 7);
		assertFalse(plano1.getPeriodos().get(6).contemDisciplina(grade.getDisciplinaPorNome("Métodos e Software Numéricos")));
		assertTrue(plano1.getPeriodos().get(7).contemDisciplina(grade.getDisciplinaPorNome("Métodos e Software Numéricos")));
		
	}
	
	@Test
	public void definirPeriodoAtual() {
		plano1.setPeriodoAtual(3);
		
		plano1.addDisciplina(grade.getDisciplinaPorNome("Cálculo Diferencial e Integral III"), 2);
		assertFalse(plano1.getDisciplinasAlocadas().contains(grade.getDisciplinaPorNome("Cálculo Diferencial e Integral III")));

		plano1.addDisciplina(grade.getDisciplinaPorNome("Cálculo Diferencial e Integral III"), 3);
		assertFalse(plano1.getDisciplinasAlocadas().contains(grade.getDisciplinaPorNome("Cálculo Diferencial e Integral III")));
		
		plano1.removeDisciplina(grade.getDisciplinaPorNome("Projeto em Computação II"));
		plano1.addDisciplina(grade.getDisciplinaPorNome("Cálculo Diferencial e Integral III"), 6);
		plano1.addDisciplina(grade.getDisciplinaPorNome("Economia"), 6);
		plano1.addDisciplina(grade.getDisciplinaPorNome("Administração"), 6);
		plano1.addDisciplina(grade.getDisciplinaPorNome("Engenharia de Software II"), 6);
		plano1.addDisciplina(grade.getDisciplinaPorNome("Inglês"), 6);
		
		assertTrue(plano1.getDisciplinasAlocadas().contains(grade.getDisciplinaPorNome("Cálculo Diferencial e Integral III")));
		assertTrue(plano1.getDisciplinasAlocadas().contains(grade.getDisciplinaPorNome("Economia")));
		assertTrue(plano1.getDisciplinasAlocadas().contains(grade.getDisciplinaPorNome("Administração")));
		assertTrue(plano1.getDisciplinasAlocadas().contains(grade.getDisciplinaPorNome("Engenharia de Software II")));
		assertTrue(plano1.getDisciplinasAlocadas().contains(grade.getDisciplinaPorNome("Inglês")));
		
		assertEquals(33, plano1.getPeriodos().get(6).getTotalDeCreditos());
	}
	
	@Test
	public void planejarProximoPeriodoFacil() {
		for (Disciplina disciplina: grade.getTodasDisciplinas()) {
			disciplina.addDificuldade(null, 3);
		}
		
		grade.getDisciplinaPorNome("Economia").addDificuldade(null, 2);
		grade.getDisciplinaPorNome("Administração").addDificuldade(null, 2);
		grade.getDisciplinaPorNome("Informática e Sociedade").addDificuldade(null, 2);
		grade.getDisciplinaPorNome("Direito e Cidadania").addDificuldade(null, 2);
		
		plano1.setPlanejadorProximoPeriodo(new PlanejaPeriodoFacil());
		plano1.setPeriodoAtual(3);
		plano1.planejaProximoPeriodo();
		
		assertTrue(plano1.getDisciplinasAlocadas().contains(grade.getDisciplinaPorNome("Economia")));
		assertTrue(plano1.getDisciplinasAlocadas().contains(grade.getDisciplinaPorNome("Administração")));
		assertTrue(plano1.getDisciplinasAlocadas().contains(grade.getDisciplinaPorNome("Informática e Sociedade")));
		assertTrue(plano1.getDisciplinasAlocadas().contains(grade.getDisciplinaPorNome("Direito e Cidadania")));
	}
	
	@Test
	public void planejarProximoPeriodoDificil() {
		for (Disciplina disciplina: grade.getTodasDisciplinas()) {
			disciplina.addDificuldade(null, 3);
		}
		
		grade.getDisciplinaPorNome("Cálculo Diferencial e Integral III").addDificuldade(null, 4);
		grade.getDisciplinaPorNome("Compiladores").addDificuldade(null, 4);
		grade.getDisciplinaPorNome("Inglês").addDificuldade(null, 5);
		grade.getDisciplinaPorNome("Análise e Técnica de Algoritmos").addDificuldade(null, 4);
		
		plano1.setPlanejadorProximoPeriodo(new PlanejaPeriodoDificil());
		plano1.setPeriodoAtual(3);
		plano1.planejaProximoPeriodo();
		
		assertTrue(plano1.getDisciplinasAlocadas().contains(grade.getDisciplinaPorNome("Cálculo Diferencial e Integral III")));
		assertTrue(plano1.getDisciplinasAlocadas().contains(grade.getDisciplinaPorNome("Compiladores")));
		assertTrue(plano1.getDisciplinasAlocadas().contains(grade.getDisciplinaPorNome("Inglês")));
		assertTrue(plano1.getDisciplinasAlocadas().contains(grade.getDisciplinaPorNome("Análise e Técnica de Algoritmos")));
	}
}
