package model;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class PlanoTest {

	private Plano plano1;
	private Grade grade;
	
	@Before
	public void SetUp() throws IOException {
		grade = new Grade();
		plano1 = new Plano(grade);
	}
	
	@Test
	public void comparaDeferencasEntrePlanos() {
		Plano plano2 = new Plano(grade);
		
		assertEquals(0, plano1.getDiferencaDePlanos(plano2));
		
		plano2.removeDisciplina(grade.getDisciplinaPorNome("projeto em computação i"));
		assertEquals(2, plano1.getDiferencaDePlanos(plano2));
		
		plano2.addDisciplina(grade.getDisciplinaPorNome("projeto em computação i"), 6);
		assertEquals(1, plano1.getDiferencaDePlanos(plano2));
		
		plano2.addDisciplina(grade.getDisciplinaPorNome("projeto em computação ii"), 7);
		plano2.addDisciplina(grade.getDisciplinaPorNome("inglês"), 7);
		assertEquals(1, plano1.getDiferencaDePlanos(plano2));
	}

}
