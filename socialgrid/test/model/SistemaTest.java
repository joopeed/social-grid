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
		
		disciplinas.add(controlador.getDisciplinaPorNome("cálculo diferencial e integral i"));
		disciplinas.add(controlador.getDisciplinaPorNome("álgebra vetorial e geometria analítica"));
		disciplinas.add(controlador.getDisciplinaPorNome("leitura e produção de textos"));
		disciplinas.add(controlador.getDisciplinaPorNome("programação i"));
		disciplinas.add(controlador.getDisciplinaPorNome("introdução à computação"));
		disciplinas.add(controlador.getDisciplinaPorNome("laboratório de programação i"));
		disciplinas.add(controlador.getDisciplinaPorNome("cálculo diferencial e integral ii"));
		disciplinas.add(controlador.getDisciplinaPorNome("programação ii"));
		disciplinas.add(controlador.getDisciplinaPorNome("laboratório de programação ii"));
		disciplinas.add(controlador.getDisciplinaPorNome("teoria dos grafos"));
		disciplinas.add(controlador.getDisciplinaPorNome("matemática discreta"));
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
		disciplinas.add(controlador.getDisciplinaPorNome("compiladores"));
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
		disciplinas.add(controlador.getDisciplinaPorNome("métodos e software numéricos"));
		disciplinas.add(controlador.getDisciplinaPorNome("avaliação de desempenho de sistemas discretos"));
		disciplinas.add(controlador.getDisciplinaPorNome("projeto em computação i"));
		disciplinas.add(controlador.getDisciplinaPorNome("projeto em computação ii"));		
		
		assertTrue(disciplinas.equals(todasDisciplinas));
		
		Disciplina algebra = controlador.getDisciplinaPorNome("álgebra linear");
		Disciplina metedosEst = controlador.getDisciplinaPorNome("métodos estatísticos");
		Disciplina metedosSoftNum = controlador.getDisciplinaPorNome("métodos e software numéricos");
		Disciplina vetorial = controlador.getDisciplinaPorNome("álgebra vetorial e geometria analítica");

		assertTrue(algebra.getDependentes().contains(metedosEst));
		assertTrue(algebra.getDependentes().contains(metedosSoftNum));
		assertTrue(algebra.getRequisitos().contains(vetorial));
	}

	@Test
	public void adicionaDisciplinaNoPlanejamento() {
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("engenharia de software ii")));
		controlador.addDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "engenharia de software ii", 1);
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("engenharia de software ii")));
	}
	
	@Test
	public void removeDisciplinaDoPlanejamento0() {
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("projeto em computação ii")));
		controlador.removeDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "projeto em computação ii");
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("projeto em computação ii")));
	}
	
	@Test
	public void removeDisciplinaDoPlanejamento1() {
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("teoria da computação")));
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("lógica matemática")));
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("análise e técnicas de algoritmos")));
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("inteligência artificial i")));
		controlador.removeDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "lógica matemática");
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
		controlador.removeDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "redes de computadores");
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("redes de computadores")));
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("interconexão de redes de computadores")));
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("laboratório de interconexão de redes de computadores")));
	}
	
	
	@Test
	public void adicionaDisciplinaDoPlanejamento0() {
		controlador.removeDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "cálculo diferencial e integral ii");
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("cálculo diferencial e integral ii")));
		
		controlador.removeDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "cálculo diferencial e integral i");
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("cálculo diferencial e integral i")));
		
		controlador.addDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "cálculo diferencial e integral ii", 1);
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("cálculo diferencial e integral ii")));
		
		controlador.addDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "cálculo diferencial e integral i", 0);
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("cálculo diferencial e integral i")));
		
		controlador.addDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "cálculo diferencial e integral ii", 1);
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("cálculo diferencial e integral ii")));
		
	}
	
	@Test
	public void adicionaDisciplinaDoPlanejamento1() {
		controlador.removeDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "programação i");
		controlador.removeDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "introdução à computação");
		controlador.removeDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "laboratório de programação i");
		controlador.removeDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "programação ii");
		controlador.removeDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "laboratório de programação ii");
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("programação i")));
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("introdução à computação")));
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("laboratório de programação i")));
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("programação i")));
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("laboratório de programação ii")));
		controlador.addDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "programação ii", 1);
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("programação i")));
		controlador.addDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "programação i", 0);
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("programação i")));
		controlador.addDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "programação ii", 1);
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("programação ii")));
		controlador.addDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "introdução à computação", 0);
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("introdução à computação")));
		controlador.addDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "programação ii", 1);
		assertFalse(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("programação ii")));
		controlador.addDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "laboratório de programação i", 0);
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("laboratório de programação i")));
		controlador.addDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "programação ii", 1);
		assertTrue(controlador.getDisciplinasAlocadas(controlador.getUsuarioPorEmail("email@email.com")).contains(controlador.getDisciplinaPorNome("programação ii")));
	}
	
	@Test
	public void removeDoPlanoSemRemoverDaGrade() {
		Finder<Integer, Grade> gradeFinder = new Finder<Integer, Grade>(Integer.class, Grade.class);
		
		controlador.removeDisciplina(controlador.getUsuarioPorEmail("email@email.com"), "programação i");
		Grade grade = gradeFinder.all().get(0);
		
		assertNotNull(grade.getDisciplinaPorNome("programação i"));
		assertNotNull(grade.getDisciplinaPorNome("programação ii"));
	}
	
}
