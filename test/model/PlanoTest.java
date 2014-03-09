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

import controllers.Grade;

public class PlanoTest {

	private Plano plano1;
	private Grade grade;
	
	@Before
	public void SetUp() throws IOException, ParserConfigurationException, SAXException {
		start(fakeApplication(inMemoryDatabase()));
		
		grade = new Grade();
		plano1 = new Plano();
		plano1.iniciaPrePlano(grade);
	}
	
	@Test
	public void comparaDeferencasEntrePlanos() {
		Plano plano2 = new Plano();
		plano2.iniciaPrePlano(grade);
		
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
		
		assertTrue(plano1.getDisciplinasOfertadas(grade).containsAll(naoAlocadas));
	}
}
