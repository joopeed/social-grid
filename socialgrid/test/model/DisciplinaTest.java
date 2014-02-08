package model;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class DisciplinaTest {

	@Test
	public void VerificaAdicaoDeDependentes() {
		Disciplina d1 = new Disciplina("d1", 4);
		Disciplina d2 = new Disciplina("d2", 4);
		Set<Disciplina> dependentesD1 = new HashSet<Disciplina>();
		Set<Disciplina> requisitosD2 = new HashSet<Disciplina>();
		
		assertTrue(dependentesD1.equals(d1.getDependentes()));
		assertTrue(requisitosD2.equals(d1.getRequisitos()));
		
		d1.acrescentaDependente(d2);
		dependentesD1.add(d2);
		requisitosD2.add(d1);
		
		assertTrue(dependentesD1.equals(d1.getDependentes()));
		assertTrue(requisitosD2.equals(d2.getRequisitos()));
	}
}
