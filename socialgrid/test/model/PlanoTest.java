package model;

import static org.junit.Assert.*;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.start;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PlanoTest {

	private Plano plano1;
	private Grade grade;
	
	@Before
	public void SetUp() throws IOException {
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
		
		plano2.removeDisciplina(grade.getDisciplinaPorNome("projeto em computação i"));
		assertEquals(2, plano1.getDiferencaDePlanos(plano2));
		
		plano2.addDisciplina(grade.getDisciplinaPorNome("projeto em computação i"), 6);
		assertEquals(1, plano1.getDiferencaDePlanos(plano2));
		
		plano2.addDisciplina(grade.getDisciplinaPorNome("projeto em computação ii"), 7);
		plano2.addDisciplina(grade.getDisciplinaPorNome("inglês"), 7);
		assertEquals(1, plano1.getDiferencaDePlanos(plano2));
	}

	@Test
	public void disciplinasOfertadas() {
		List<Disciplina> naoAlocadas = new ArrayList<Disciplina>();
		naoAlocadas.add(grade.getDisciplinaPorNome("inglês"));
		naoAlocadas.add(grade.getDisciplinaPorNome("engenharia de software ii"));
		
		assertTrue(plano1.getDisciplinasOfertadas().containsAll(naoAlocadas));
	}
}
