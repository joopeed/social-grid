package model;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class Planejador {

	private Grade grade;
	
	
	public Planejador() throws IOException {
		this.grade = Grade.getInstancia();
	}


	public Set<Disciplina> getTodasDisciplinas() {
		return grade.getTodasDisciplinas();
	}

}
