package controllers;

import java.util.List;
import java.util.Set;

import model.Disciplina;
import model.Usuario;

public class Sistema {
	private Grade grade;
	
	public Sistema() {
		grade = new Grade();
	}
	
	private Grade getGrade() {
		return grade;
	}
	
	public Disciplina getDisciplinaPorNome(String nome) {
		return getGrade().getDisciplinaPorNome(nome);
	}
	
	public Disciplina getDisciplinaPorCodigo(Long codigo) {
		return getGrade().getDisciplinaPorCodigo(codigo);
	}

	public void alocarDisciplina(Usuario usuario, String nome, int idxPeriodo) {
		usuario.getPlano().addDisciplina(getGrade().getDisciplinaPorNome(nome), idxPeriodo);
		usuario.update();
	}

	public void desalocarDisciplina(Usuario usuario, String nome) {
		usuario.getPlano().removeDisciplina(getGrade().getDisciplinaPorNome(nome));
		usuario.update();
	}
	
	public List<Disciplina> getDisciplinasOfertadas(Usuario usuario) {
		return usuario.getPlano().getDisciplinasOfertadas(grade);
	}

	public Set<Disciplina> getDisciplinasAlocadas(Usuario usuario) {
		return usuario.getPlano().getDisciplinasAlocadas();
	}
}
