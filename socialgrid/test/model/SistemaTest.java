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
		disciplinas.add(controlador.getDisciplinaPorNome("Laboratório de Programação II"));
		disciplinas.add(controlador.getDisciplinaPorNome("Cálculo Diferencial e Integral II"));
		disciplinas.add(controlador.getDisciplinaPorNome("Programação II"));
		disciplinas.add(controlador.getDisciplinaPorNome("Laboratório de Programação II"));
		disciplinas.add(controlador.getDisciplinaPorNome("Teoria dos Grafos"));
		disciplinas.add(controlador.getDisciplinaPorNome("Matemática Discreta"));
		disciplinas.add(controlador.getDisciplinaPorNome("metodologia científica"));
		disciplinas.add(controlador.getDisciplinaPorNome("fundamentos de física clássica"));
		disciplinas.add(controlador.getDisciplinaPorNome("álgebra linear"));
		disciplinas.add(controlador.getDisciplinaPorNome("probabilidade e estatística"));
		disciplinas.add(controlador.getDisciplinaPorNome("teoria da computação"));
		disciplinas.add(controlador.getDisciplinaPorNome("estruturas de dados e algoritmos"));
		disciplinas.add(controlador.getDisciplinaPorNome("fundamentos de física moderna"));
		disciplinas.add(controlador.getDisciplinaPorNome("gerência da informação"));
		disciplinas.add(controlador.getDisciplinaPorNome("laboratório de estruturas de dados e algoritmos"));
		disciplinas.add(controlador.getDisciplinaPorNome("métodos estatísticos"));
		disciplinas.add(controlador.getDisciplinaPorNome("paradigmas de linguagens de programação"));
		disciplinas.add(controlador.getDisciplinaPorNome("lógica matemática"));
		disciplinas.add(controlador.getDisciplinaPorNome("organização e arquitetura de computadores i"));
		disciplinas.add(controlador.getDisciplinaPorNome("engenharia de software i"));
		disciplinas.add(controlador.getDisciplinaPorNome("sistemas de informação i"));
		disciplinas.add(controlador.getDisciplinaPorNome("laboratório de organização e arquitetura de computadores"));
		disciplinas.add(controlador.getDisciplinaPorNome("informática e sociedade"));
		disciplinas.add(controlador.getDisciplinaPorNome("análise e técnicas de algoritmos"));
		disciplinas.add(controlador.getDisciplinaPorNome("Compiladores"));
		disciplinas.add(controlador.getDisciplinaPorNome("redes de computadores"));
		disciplinas.add(controlador.getDisciplinaPorNome("bancos de dados i"));
		disciplinas.add(controlador.getDisciplinaPorNome("sistemas de informação ii"));
		disciplinas.add(controlador.getDisciplinaPorNome("laboratório de engenharia de software"));
		disciplinas.add(controlador.getDisciplinaPorNome("direito e cidadania"));
		disciplinas.add(controlador.getDisciplinaPorNome("sistemas operacionais"));
		disciplinas.add(controlador.getDisciplinaPorNome("interconexão de redes de computadores"));
		disciplinas.add(controlador.getDisciplinaPorNome("banco de dados ii"));
		disciplinas.add(controlador.getDisciplinaPorNome("inteligência artificial i"));
		disciplinas.add(controlador.getDisciplinaPorNome("laboratório de interconexão de redes de computadores"));
		disciplinas.add(controlador.getDisciplinaPorNome("Métodos e Software Numéricos"));
		disciplinas.add(controlador.getDisciplinaPorNome("Avaliação de Desempenho de Sistemas Discretos"));
		disciplinas.add(controlador.getDisciplinaPorNome("Projeto em Computação I"));
		disciplinas.add(controlador.getDisciplinaPorNome("Projeto em Computação II"));		
		
		assertTrue(disciplinas.equals(todasDisciplinas));
		
		Disciplina algebra = controlador.getDisciplinaPorNome("álgebra linear");
		Disciplina metedosEst = controlador.getDisciplinaPorNome("métodos estatísticos");
		Disciplina metedosSoftNum = controlador.getDisciplinaPorNome("Métodos e Software Numéricos");
		Disciplina vetorial = controlador.getDisciplinaPorNome("Álgebra Vetorial e Geometria Analítica");

		assertTrue(algebra.getDependentes().contains(metedosEst));
		assertTrue(algebra.getDependentes().contains(metedosSoftNum));
		assertTrue(algebra.getRequisitos().contains(vetorial));
	}

	@Test
	public void adicionaDisciplinaNoPlanejamento() {
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("engenharia de software ii")));
		controlador.alocarDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "engenharia de software ii", 1);
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("engenharia de software ii")));
	}
	
	@Test
	public void removeDisciplinaDoPlanejamento0() {
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Projeto em Computação II")));
		controlador.desalocarDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "Projeto em Computação II");
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Projeto em Computação II")));
	}
	
	@Test
	public void removeDisciplinaDoPlanejamento1() {
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("teoria da computação")));
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("lógica matemática")));
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("análise e técnicas de algoritmos")));
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("inteligência artificial i")));
		controlador.desalocarDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "lógica matemática");
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("teoria da computação")));
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("lógica matemática")));
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("análise e técnicas de algoritmos")));
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("inteligência artificial i")));
	}
	
	@Test
	public void removeDisciplinaDoPlanejamento2() {
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("redes de computadores")));
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("interconexão de redes de computadores")));
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("laboratório de interconexão de redes de computadores")));
		controlador.desalocarDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "redes de computadores");
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("redes de computadores")));
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("interconexão de redes de computadores")));
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("laboratório de interconexão de redes de computadores")));
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
		controlador.desalocarDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "Laboratório de Programação II");
		controlador.desalocarDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "Programação II");
		controlador.desalocarDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "Laboratório de Programação II");
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Programação I")));
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Introdução à Computação")));
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("Laboratório de Programação II")));
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
		controlador.alocarDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "Laboratório de Programação II", 0);
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
