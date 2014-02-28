package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DicaTest {

	private Disciplina disciplina;
	
	@Before
	public void SetUp() {
		disciplina = new Disciplina("d1", 4);
	}
	
	@Test
	public void AdicionaDicasEmDisciplina() {
		Dica dica1 = new Dica("Trancar!");
		Dica dica2 = new Dica("Estudar pelo livro.");
		Dica dica3 = new Dica("Tirar dúvidas pela lista.");
		List<Dica> dicas = new ArrayList<Dica>(); 
				
		assertTrue(dicas.equals(disciplina.getDicas()));
		
		disciplina.addDica(dica2);
		disciplina.addDica(dica3);
		disciplina.addDica(dica1);
		
		dicas.add(dica2);
		dicas.add(dica3);
		dicas.add(dica1);
		
		assertTrue(dicas.equals(disciplina.getDicas()));
	}
	
	@Test
	public void AdicionaERemoveLikeEmDica(){
		Dica dica1 = new Dica("Procurar tutoriais.");
		
		assertEquals(0, dica1.getLikes());
		
		dica1.adicionaLike();
		dica1.adicionaLike();
		
		assertEquals(2, dica1.getLikes());
		
		dica1.removeLike();
		
		assertEquals(1, dica1.getLikes());
		
	}

}
