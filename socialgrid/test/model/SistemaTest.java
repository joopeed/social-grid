package model;

import static org.junit.Assert.*;
import static play.test.Helpers.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import play.db.ebean.Model.Finder;
import play.test.WithApplication;
import controllers.CadastroUsuarioException;
import controllers.Sistema;

public class SistemaTest extends WithApplication {
	
	private Sistema controlador;
	
	@Before
	public void setUp() throws IOException, CadastroUsuarioException {
        start(fakeApplication(inMemoryDatabase()));

		controlador = new Sistema();
		controlador.cadastrarUsuario("Nome", "email@email.com", "123456");
	}
	
	@Test
	public void iniciaPlanejamento() throws IOException {		
		Set<Disciplina> disciplinas = new HashSet<Disciplina>();		
		Set<Disciplina> todasDisciplinas = controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com"));
		
		disciplinas.add(controlador.getDisciplinaPorNome("Cálculo Diferencial e Integral I"));
		disciplinas.add(controlador.getDisciplinaPorNome("Álgebra Vetorial e Geometria Analítica"));
		disciplinas.add(controlador.getDisciplinaPorNome("Leitura e Produção de Textos"));
		disciplinas.add(controlador.getDisciplinaPorNome("Programação I"));
		disciplinas.add(controlador.getDisciplinaPorNome("Introdução à Computação"));
		disciplinas.add(controlador.getDisciplinaPorNome("Laboratório de Programação I"));
		disciplinas.add(controlador.getDisciplinaPorNome("Cálculo Diferencial e Integral II"));
		disciplinas.add(controlador.getDisciplinaPorNome("Programação II"));
		disciplinas.add(controlador.getDisciplinaPorNome("Laboratório de Programação II"));
		disciplinas.add(controlador.getDisciplinaPorNome("Teoria dos Grafos"));
		disciplinas.add(controlador.getDisciplinaPorNome("Matemática Discreta"));
		disciplinas.add(controlador.getDisciplinaPorNome("Metodologia Científica"));
		disciplinas.add(controlador.getDisciplinaPorNome("Fundamentos de Física Clássica"));
		disciplinas.add(controlador.getDisciplinaPorNome("Álgebra Linear I"));
		disciplinas.add(controlador.getDisciplinaPorNome("Probabilidade e Estatística"));
		disciplinas.add(controlador.getDisciplinaPorNome("Teoria da Computação"));
		disciplinas.add(controlador.getDisciplinaPorNome("Estrutura de Dados e Algoritmos"));
		disciplinas.add(controlador.getDisciplinaPorNome("Fundamentos de Física Moderna"));
		disciplinas.add(controlador.getDisciplinaPorNome("Gerência da Informação"));
		disciplinas.add(controlador.getDisciplinaPorNome("Laboratório de Estrutura de Dados e Algoritmos"));
		disciplinas.add(controlador.getDisciplinaPorNome("Métodos Estatísticos"));
		disciplinas.add(controlador.getDisciplinaPorNome("Paradigmas de Linguagem de Programação"));
		disciplinas.add(controlador.getDisciplinaPorNome("Lógica Matemática"));
		disciplinas.add(controlador.getDisciplinaPorNome("Organização e Arquitetura de Computadores I"));
		disciplinas.add(controlador.getDisciplinaPorNome("Engenharia de Software I"));
		disciplinas.add(controlador.getDisciplinaPorNome("Sistemas de Informação I"));
		disciplinas.add(controlador.getDisciplinaPorNome("Laboratório de Organização e Arquitetura de Computadores"));
		disciplinas.add(controlador.getDisciplinaPorNome("Informática e Sociedade"));
		disciplinas.add(controlador.getDisciplinaPorNome("Análise e Técnica de Algoritmos"));
		disciplinas.add(controlador.getDisciplinaPorNome("Compiladores"));
		disciplinas.add(controlador.getDisciplinaPorNome("Redes de Computadores"));
		disciplinas.add(controlador.getDisciplinaPorNome("Banco de Dados I"));
		disciplinas.add(controlador.getDisciplinaPorNome("Sistemas de Informação II"));
		disciplinas.add(controlador.getDisciplinaPorNome("Laboratório de Engenharia de Software"));
		disciplinas.add(controlador.getDisciplinaPorNome("Direito e Cidadania"));
		disciplinas.add(controlador.getDisciplinaPorNome("Sistemas Operacionais"));
		disciplinas.add(controlador.getDisciplinaPorNome("Interconexão de Redes de Computadores"));
		disciplinas.add(controlador.getDisciplinaPorNome("Banco de Dados II"));
		disciplinas.add(controlador.getDisciplinaPorNome("Inteligência Artificial I"));
		disciplinas.add(controlador.getDisciplinaPorNome("Laboratório de Interconexão de Redes de Computadores"));
		disciplinas.add(controlador.getDisciplinaPorNome("Métodos e Software Numéricos"));
		disciplinas.add(controlador.getDisciplinaPorNome("Avaliação de Desempenho de Sistemas Discretos"));
		disciplinas.add(controlador.getDisciplinaPorNome("Projeto em Computação I"));
		disciplinas.add(controlador.getDisciplinaPorNome("Projeto em Computação II"));		
		
		assertEquals(disciplinas, todasDisciplinas);
		
		Disciplina algebra = controlador.getDisciplinaPorNome("Álgebra Linear I");
		Disciplina metedosEst = controlador.getDisciplinaPorNome("Métodos Estatísticos");
		Disciplina metedosSoftNum = controlador.getDisciplinaPorNome("Métodos e Software Numéricos");
		Disciplina vetorial = controlador.getDisciplinaPorNome("Álgebra Vetorial e Geometria Analítica");

		assertTrue(algebra.getDependentes().contains(metedosEst));
		assertTrue(algebra.getDependentes().contains(metedosSoftNum));
		assertTrue(algebra.getRequisitos().contains(vetorial));
	}

	@Test
	public void adicionaDisciplinaNoPlanejamento() {
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Engenharia de Software II")));
		controlador.alocarDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "Engenharia de Software II", 1);
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Engenharia de Software II")));
	}
	
	@Test
	public void removeDisciplinaDoPlanejamento0() {
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Projeto em Computação II")));
		controlador.desalocarDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "Projeto em Computação II");
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Projeto em Computação II")));
	}
	
	@Test
	public void removeDisciplinaDoPlanejamento1() {
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Teoria da Computação")));
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Lógica Matemática")));
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Análise e Técnica de Algoritmos")));
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Inteligência Artificial I")));
		controlador.desalocarDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "Lógica Matemática");
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Teoria da Computação")));
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Lógica Matemática")));
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Análise e Técnica de Algoritmos")));
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Inteligência Artificial I")));
	}
	
	@Test
	public void removeDisciplinaDoPlanejamento2() {
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Redes de Computadores")));
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Interconexão de Redes de Computadores")));
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Laboratório de Interconexão de Redes de Computadores")));
		controlador.desalocarDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "Redes de Computadores");
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Redes de Computadores")));
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Interconexão de Redes de Computadores")));
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Laboratório de Interconexão de Redes de Computadores")));
	}
	
	
	@Test
	public void adicionaDisciplinaDoPlanejamento0() {
		controlador.desalocarDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "Cálculo Diferencial e Integral II");
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Cálculo Diferencial e Integral II")));
		
		controlador.desalocarDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "Cálculo Diferencial e Integral I");
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Cálculo Diferencial e Integral I")));
		
		controlador.alocarDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "Cálculo Diferencial e Integral II", 1);
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Cálculo Diferencial e Integral II")));
		
		controlador.alocarDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "Cálculo Diferencial e Integral I", 0);
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Cálculo Diferencial e Integral I")));
		
		controlador.alocarDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "Cálculo Diferencial e Integral II", 1);
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Cálculo Diferencial e Integral II")));
		
	}
	
	@Test
	public void adicionaDisciplinaDoPlanejamento1() {
		controlador.desalocarDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "Programação I");
		controlador.desalocarDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "Introdução à Computação");
		controlador.desalocarDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "Laboratório de Programação I");
		controlador.desalocarDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "Programação II");
		controlador.desalocarDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "Laboratório de Programação II");
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Programação I")));
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Introdução à Computação")));
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Laboratório de Programação I")));
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Programação I")));
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Laboratório de Programação II")));
		controlador.alocarDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "Programação II", 1);
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Programação I")));
		controlador.alocarDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "Programação I", 0);
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Programação I")));
		controlador.alocarDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "Programação II", 1);
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Programação II")));
		controlador.alocarDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "Introdução à Computação", 0);
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Introdução à Computação")));
		controlador.alocarDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "Programação II", 1);
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Programação II")));
		controlador.alocarDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "Laboratório de Programação I", 0);
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Laboratório de Programação I")));
		controlador.alocarDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "Laboratório de Programação II", 1);
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Laboratório de Programação II")));
		controlador.alocarDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "Programação II", 1);
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Programação II")));
	}
	
	@Test
	public void removeDoPlanoSemRemoverDaGrade() {
		Finder<Integer, Grade> gradeFinder = new Finder<Integer, Grade>(Integer.class, Grade.class);
		
		controlador.desalocarDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "Programação I");
		Grade grade = gradeFinder.all().get(0);
		
		assertNotNull(grade.getDisciplinaPorNome("Programação I"));
		assertNotNull(grade.getDisciplinaPorNome("Programação II"));
	}
	
}
