package model;

import static org.junit.Assert.*;
import static play.test.Helpers.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import play.test.WithApplication;
import controllers.CadastroUsuario;
import controllers.CadastroUsuarioException;
import controllers.Grade;
import controllers.Sistema;

public class SistemaTest extends WithApplication {
	
	private Sistema sistema;
	private CadastroUsuario cadastro;
	
	@Before
	public void setUp() throws IOException, CadastroUsuarioException {
        start(fakeApplication(inMemoryDatabase()));

		sistema = new Sistema();
		cadastro = new CadastroUsuario();
		cadastro.cadastrarUsuario("Nome", "email@email.com", "123456");
	}
	
	@Test
	public void iniciaPlanejamento() throws IOException {		
		Set<Disciplina> disciplinas = new HashSet<Disciplina>();		
		Set<Disciplina> todasDisciplinas = sistema.getDisciplinasAlocadas(cadastro.getUsuarioPorEmail("email@email.com"));
		
		disciplinas.add(sistema.getDisciplinaPorNome("Cálculo Diferencial e Integral I"));
		disciplinas.add(sistema.getDisciplinaPorNome("Álgebra Vetorial e Geometria Analítica"));
		disciplinas.add(sistema.getDisciplinaPorNome("Leitura e Produção de Textos"));
		disciplinas.add(sistema.getDisciplinaPorNome("Programação I"));
		disciplinas.add(sistema.getDisciplinaPorNome("Introdução à Computação"));
		disciplinas.add(sistema.getDisciplinaPorNome("Laboratório de Programação I"));
		disciplinas.add(sistema.getDisciplinaPorNome("Cálculo Diferencial e Integral II"));
		disciplinas.add(sistema.getDisciplinaPorNome("Programação II"));
		disciplinas.add(sistema.getDisciplinaPorNome("Laboratório de Programação II"));
		disciplinas.add(sistema.getDisciplinaPorNome("Teoria dos Grafos"));
		disciplinas.add(sistema.getDisciplinaPorNome("Matemática Discreta"));
		disciplinas.add(sistema.getDisciplinaPorNome("Metodologia Científica"));
		disciplinas.add(sistema.getDisciplinaPorNome("Fundamentos de Física Clássica"));
		disciplinas.add(sistema.getDisciplinaPorNome("Álgebra Linear I"));
		disciplinas.add(sistema.getDisciplinaPorNome("Probabilidade e Estatística"));
		disciplinas.add(sistema.getDisciplinaPorNome("Teoria da Computação"));
		disciplinas.add(sistema.getDisciplinaPorNome("Estrutura de Dados e Algoritmos"));
		disciplinas.add(sistema.getDisciplinaPorNome("Fundamentos de Física Moderna"));
		disciplinas.add(sistema.getDisciplinaPorNome("Gerência da Informação"));
		disciplinas.add(sistema.getDisciplinaPorNome("Laboratório de Estrutura de Dados e Algoritmos"));
		disciplinas.add(sistema.getDisciplinaPorNome("Métodos Estatísticos"));
		disciplinas.add(sistema.getDisciplinaPorNome("Paradigmas de Linguagem de Programação"));
		disciplinas.add(sistema.getDisciplinaPorNome("Lógica Matemática"));
		disciplinas.add(sistema.getDisciplinaPorNome("Organização e Arquitetura de Computadores I"));
		disciplinas.add(sistema.getDisciplinaPorNome("Engenharia de Software I"));
		disciplinas.add(sistema.getDisciplinaPorNome("Sistemas de Informação I"));
		disciplinas.add(sistema.getDisciplinaPorNome("Laboratório de Organização e Arquitetura de Computadores"));
		disciplinas.add(sistema.getDisciplinaPorNome("Informática e Sociedade"));
		disciplinas.add(sistema.getDisciplinaPorNome("Análise e Técnica de Algoritmos"));
		disciplinas.add(sistema.getDisciplinaPorNome("Compiladores"));
		disciplinas.add(sistema.getDisciplinaPorNome("Redes de Computadores"));
		disciplinas.add(sistema.getDisciplinaPorNome("Banco de Dados I"));
		disciplinas.add(sistema.getDisciplinaPorNome("Sistemas de Informação II"));
		disciplinas.add(sistema.getDisciplinaPorNome("Laboratório de Engenharia de Software"));
		disciplinas.add(sistema.getDisciplinaPorNome("Direito e Cidadania"));
		disciplinas.add(sistema.getDisciplinaPorNome("Sistemas Operacionais"));
		disciplinas.add(sistema.getDisciplinaPorNome("Interconexão de Redes de Computadores"));
		disciplinas.add(sistema.getDisciplinaPorNome("Banco de Dados II"));
		disciplinas.add(sistema.getDisciplinaPorNome("Inteligência Artificial I"));
		disciplinas.add(sistema.getDisciplinaPorNome("Laboratório de Interconexão de Redes de Computadores"));
		disciplinas.add(sistema.getDisciplinaPorNome("Métodos e Software Numéricos"));
		disciplinas.add(sistema.getDisciplinaPorNome("Avaliação de Desempenho de Sistemas Discretos"));
		disciplinas.add(sistema.getDisciplinaPorNome("Projeto em Computação I"));
		disciplinas.add(sistema.getDisciplinaPorNome("Projeto em Computação II"));		
		
		assertEquals(disciplinas, todasDisciplinas);
		
		Disciplina algebra = sistema.getDisciplinaPorNome("Álgebra Linear I");
		Disciplina metedosEst = sistema.getDisciplinaPorNome("Métodos Estatísticos");
		Disciplina metedosSoftNum = sistema.getDisciplinaPorNome("Métodos e Software Numéricos");
		Disciplina vetorial = sistema.getDisciplinaPorNome("Álgebra Vetorial e Geometria Analítica");

		assertTrue(algebra.getDependentes().contains(metedosEst));
		assertTrue(algebra.getDependentes().contains(metedosSoftNum));
		assertTrue(algebra.getRequisitos().contains(vetorial));
	}

	@Test
	public void adicionaDisciplinaNoPlanejamento() {
		assertFalse(sistema.getDisciplinasAlocadas(cadastro.getUsuarioPorEmail("email@email.com")).contains(sistema.getDisciplinaPorNome("Engenharia de Software II")));
		sistema.alocarDisciplina(cadastro.getUsuarioPorEmail("email@email.com"), "Engenharia de Software II", 1);
		assertTrue(sistema.getDisciplinasAlocadas(cadastro.getUsuarioPorEmail("email@email.com")).contains(sistema.getDisciplinaPorNome("Engenharia de Software II")));
	}
	
	@Test
	public void removeDisciplinaDoPlanejamento0() {
		assertTrue(sistema.getDisciplinasAlocadas(cadastro.getUsuarioPorEmail("email@email.com")).contains(sistema.getDisciplinaPorNome("Projeto em Computação II")));
		sistema.desalocarDisciplina(cadastro.getUsuarioPorEmail("email@email.com"), "Projeto em Computação II");
		assertFalse(sistema.getDisciplinasAlocadas(cadastro.getUsuarioPorEmail("email@email.com")).contains(sistema.getDisciplinaPorNome("Projeto em Computação II")));
	}
	
	@Test
	public void removeDisciplinaDoPlanejamento1() {
		assertTrue(sistema.getDisciplinasAlocadas(cadastro.getUsuarioPorEmail("email@email.com")).contains(sistema.getDisciplinaPorNome("Teoria da Computação")));
		assertTrue(sistema.getDisciplinasAlocadas(cadastro.getUsuarioPorEmail("email@email.com")).contains(sistema.getDisciplinaPorNome("Lógica Matemática")));
		assertTrue(sistema.getDisciplinasAlocadas(cadastro.getUsuarioPorEmail("email@email.com")).contains(sistema.getDisciplinaPorNome("Análise e Técnica de Algoritmos")));
		assertTrue(sistema.getDisciplinasAlocadas(cadastro.getUsuarioPorEmail("email@email.com")).contains(sistema.getDisciplinaPorNome("Inteligência Artificial I")));
		sistema.desalocarDisciplina(cadastro.getUsuarioPorEmail("email@email.com"), "Lógica Matemática");
		assertTrue(sistema.getDisciplinasAlocadas(cadastro.getUsuarioPorEmail("email@email.com")).contains(sistema.getDisciplinaPorNome("Teoria da Computação")));
		assertFalse(sistema.getDisciplinasAlocadas(cadastro.getUsuarioPorEmail("email@email.com")).contains(sistema.getDisciplinaPorNome("Lógica Matemática")));
		assertFalse(sistema.getDisciplinasAlocadas(cadastro.getUsuarioPorEmail("email@email.com")).contains(sistema.getDisciplinaPorNome("Análise e Técnica de Algoritmos")));
		assertFalse(sistema.getDisciplinasAlocadas(cadastro.getUsuarioPorEmail("email@email.com")).contains(sistema.getDisciplinaPorNome("Inteligência Artificial I")));
	}
	
	@Test
	public void removeDisciplinaDoPlanejamento2() {
		assertTrue(sistema.getDisciplinasAlocadas(cadastro.getUsuarioPorEmail("email@email.com")).contains(sistema.getDisciplinaPorNome("Redes de Computadores")));
		assertTrue(sistema.getDisciplinasAlocadas(cadastro.getUsuarioPorEmail("email@email.com")).contains(sistema.getDisciplinaPorNome("Interconexão de Redes de Computadores")));
		assertTrue(sistema.getDisciplinasAlocadas(cadastro.getUsuarioPorEmail("email@email.com")).contains(sistema.getDisciplinaPorNome("Laboratório de Interconexão de Redes de Computadores")));
		sistema.desalocarDisciplina(cadastro.getUsuarioPorEmail("email@email.com"), "Redes de Computadores");
		assertFalse(sistema.getDisciplinasAlocadas(cadastro.getUsuarioPorEmail("email@email.com")).contains(sistema.getDisciplinaPorNome("Redes de Computadores")));
		assertFalse(sistema.getDisciplinasAlocadas(cadastro.getUsuarioPorEmail("email@email.com")).contains(sistema.getDisciplinaPorNome("Interconexão de Redes de Computadores")));
		assertFalse(sistema.getDisciplinasAlocadas(cadastro.getUsuarioPorEmail("email@email.com")).contains(sistema.getDisciplinaPorNome("Laboratório de Interconexão de Redes de Computadores")));
	}
	
	
	@Test
	public void adicionaDisciplinaDoPlanejamento0() {
		sistema.desalocarDisciplina(cadastro.getUsuarioPorEmail("email@email.com"), "Cálculo Diferencial e Integral II");
		assertFalse(sistema.getDisciplinasAlocadas(cadastro.getUsuarioPorEmail("email@email.com")).contains(sistema.getDisciplinaPorNome("Cálculo Diferencial e Integral II")));
		
		sistema.desalocarDisciplina(cadastro.getUsuarioPorEmail("email@email.com"), "Cálculo Diferencial e Integral I");
		assertFalse(sistema.getDisciplinasAlocadas(cadastro.getUsuarioPorEmail("email@email.com")).contains(sistema.getDisciplinaPorNome("Cálculo Diferencial e Integral I")));
		
		sistema.alocarDisciplina(cadastro.getUsuarioPorEmail("email@email.com"), "Cálculo Diferencial e Integral II", 1);
		assertFalse(sistema.getDisciplinasAlocadas(cadastro.getUsuarioPorEmail("email@email.com")).contains(sistema.getDisciplinaPorNome("Cálculo Diferencial e Integral II")));
		
		sistema.alocarDisciplina(cadastro.getUsuarioPorEmail("email@email.com"), "Cálculo Diferencial e Integral I", 0);
		assertTrue(sistema.getDisciplinasAlocadas(cadastro.getUsuarioPorEmail("email@email.com")).contains(sistema.getDisciplinaPorNome("Cálculo Diferencial e Integral I")));
		
		sistema.alocarDisciplina(cadastro.getUsuarioPorEmail("email@email.com"), "Cálculo Diferencial e Integral II", 1);
		assertTrue(sistema.getDisciplinasAlocadas(cadastro.getUsuarioPorEmail("email@email.com")).contains(sistema.getDisciplinaPorNome("Cálculo Diferencial e Integral II")));
		
	}
	
	@Test
	public void adicionaDisciplinaDoPlanejamento1() {
		sistema.desalocarDisciplina(cadastro.getUsuarioPorEmail("email@email.com"), "Programação I");
		sistema.desalocarDisciplina(cadastro.getUsuarioPorEmail("email@email.com"), "Introdução à Computação");
		sistema.desalocarDisciplina(cadastro.getUsuarioPorEmail("email@email.com"), "Laboratório de Programação I");
		sistema.desalocarDisciplina(cadastro.getUsuarioPorEmail("email@email.com"), "Programação II");
		sistema.desalocarDisciplina(cadastro.getUsuarioPorEmail("email@email.com"), "Laboratório de Programação II");
		assertFalse(sistema.getDisciplinasAlocadas(cadastro.getUsuarioPorEmail("email@email.com")).contains(sistema.getDisciplinaPorNome("Programação I")));
		assertFalse(sistema.getDisciplinasAlocadas(cadastro.getUsuarioPorEmail("email@email.com")).contains(sistema.getDisciplinaPorNome("Introdução à Computação")));
		assertFalse(sistema.getDisciplinasAlocadas(cadastro.getUsuarioPorEmail("email@email.com")).contains(sistema.getDisciplinaPorNome("Laboratório de Programação I")));
		assertFalse(sistema.getDisciplinasAlocadas(cadastro.getUsuarioPorEmail("email@email.com")).contains(sistema.getDisciplinaPorNome("Programação I")));
		assertFalse(sistema.getDisciplinasAlocadas(cadastro.getUsuarioPorEmail("email@email.com")).contains(sistema.getDisciplinaPorNome("Laboratório de Programação II")));
		sistema.alocarDisciplina(cadastro.getUsuarioPorEmail("email@email.com"), "Programação II", 1);
		assertFalse(sistema.getDisciplinasAlocadas(cadastro.getUsuarioPorEmail("email@email.com")).contains(sistema.getDisciplinaPorNome("Programação I")));
		sistema.alocarDisciplina(cadastro.getUsuarioPorEmail("email@email.com"), "Programação I", 0);
		assertTrue(sistema.getDisciplinasAlocadas(cadastro.getUsuarioPorEmail("email@email.com")).contains(sistema.getDisciplinaPorNome("Programação I")));
		sistema.alocarDisciplina(cadastro.getUsuarioPorEmail("email@email.com"), "Programação II", 1);
		assertFalse(sistema.getDisciplinasAlocadas(cadastro.getUsuarioPorEmail("email@email.com")).contains(sistema.getDisciplinaPorNome("Programação II")));
		sistema.alocarDisciplina(cadastro.getUsuarioPorEmail("email@email.com"), "Introdução à Computação", 0);
		assertTrue(sistema.getDisciplinasAlocadas(cadastro.getUsuarioPorEmail("email@email.com")).contains(sistema.getDisciplinaPorNome("Introdução à Computação")));
		sistema.alocarDisciplina(cadastro.getUsuarioPorEmail("email@email.com"), "Programação II", 1);
		assertFalse(sistema.getDisciplinasAlocadas(cadastro.getUsuarioPorEmail("email@email.com")).contains(sistema.getDisciplinaPorNome("Programação II")));
		sistema.alocarDisciplina(cadastro.getUsuarioPorEmail("email@email.com"), "Laboratório de Programação I", 0);
		assertTrue(sistema.getDisciplinasAlocadas(cadastro.getUsuarioPorEmail("email@email.com")).contains(sistema.getDisciplinaPorNome("Laboratório de Programação I")));
		sistema.alocarDisciplina(cadastro.getUsuarioPorEmail("email@email.com"), "Laboratório de Programação II", 1);
		assertTrue(sistema.getDisciplinasAlocadas(cadastro.getUsuarioPorEmail("email@email.com")).contains(sistema.getDisciplinaPorNome("Laboratório de Programação II")));
		sistema.alocarDisciplina(cadastro.getUsuarioPorEmail("email@email.com"), "Programação II", 1);
		assertTrue(sistema.getDisciplinasAlocadas(cadastro.getUsuarioPorEmail("email@email.com")).contains(sistema.getDisciplinaPorNome("Programação II")));
	}
	
	@Test
	public void removeDoPlanoSemRemoverDaGrade() {
		sistema.desalocarDisciplina(cadastro.getUsuarioPorEmail("email@email.com"), "Programação I");
		Grade grade = new Grade();
		
		assertNotNull(grade.getDisciplinaPorNome("Programação I"));
		assertNotNull(grade.getDisciplinaPorNome("Programação II"));
	}
	
}
