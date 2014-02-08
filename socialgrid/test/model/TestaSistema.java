package model;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import controllers.Controlador;

public class TestaSistema {
	
	@Test
	public void iniciaPlanejamentoComRequisitos() throws IOException {
		Controlador controlador = new Controlador();
		controlador.iniciarPlano();
		
		Set<Disciplina> disciplinas = new HashSet<Disciplina>();

		Grade grade = Grade.getInstancia();
		disciplinas.add(grade.getDisciplinaPorNome("cálculo diferencial e integral i"));
		disciplinas.add(grade.getDisciplinaPorNome("álgebra vetorial e geometria analítica"));
		disciplinas.add(grade.getDisciplinaPorNome("leitura e produção de textos"));
		disciplinas.add(grade.getDisciplinaPorNome("programação i"));
		disciplinas.add(grade.getDisciplinaPorNome("introdução à computação"));
		disciplinas.add(grade.getDisciplinaPorNome("laboratório de programação i"));
		
		Planejador planejador = new Planejador();
		Set<Disciplina> todasDisciplinas = planejador.getTodasDisciplinas();
		
		assertTrue(disciplinas.equals(todasDisciplinas));
	}
	
	
}
