package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.start;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class DisciplinaTest {

	@Before
	public void setUp() {
		start(fakeApplication(inMemoryDatabase()));
	}
	
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
	
	@Test
	public void verificaDificuldade() {
		Disciplina d1 = new Disciplina("d1", 4);
		d1.save();
		
		assertEquals(0, d1.getDificuldadeMedia(), 0);
		
		Usuario usuarioA = new Usuario("Foo", "foo@gmail.com", "123456", new Plano());
		Usuario usuarioB = new Usuario("Boo", "boo@gmail.com", "654321", new Plano());
		Usuario usuarioC = new Usuario("FOo", "foo@gmail.com", "159753", new Plano());
		
		d1.addDificuldade(usuarioA, 2);
		d1.addDificuldade(usuarioB, 2);
		d1.addDificuldade(usuarioC, 3);
				
		assertEquals(2.33, d1.getDificuldadeMedia(), 0.01);
	}
}
