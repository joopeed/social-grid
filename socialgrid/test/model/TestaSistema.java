package model;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import controllers.Controlador;

public class TestaSistema {
	@Test
	public void iniciaPlanejamentoComRequisitos() {
		Controlador controlador = new Controlador();
		controlador.iniciarPlano();
		
		Set<Disciplina> disciplinas = new HashSet<Disciplina>();

		Grade grade = new Grade();
		disciplinas.add(grade.buscaDisciplinaPorNome("Cálculo Diferencial e Integral I"));
		disciplinas.add(grade.buscaDisciplinaPorNome("Álgebra Vetorial e Geometria Analítica"));
		disciplinas.add(grade.buscaDisciplinaPorNome("Leitura e Produção de Textos"));
		disciplinas.add(grade.buscaDisciplinaPorNome("Programação I"));
		disciplinas.add(grade.buscaDisciplinaPorNome("Introdução à Computação"));
		disciplinas.add(grade.buscaDisciplinaPorNome("Laboratório de Programação I"));
		
		Planejador planejador = new Planejador();
		Collection<Disciplina> todasDisciplinas = planejador.getTodasDisciplinas();
		
		assertTrue(disciplinas.equals(todasDisciplinas));
	}
}
