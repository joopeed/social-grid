package controllers;

import java.util.List;
import java.util.Set;

import model.Dica;
import model.Disciplina;
import model.PlanejadorDePeriodoDificil;
import model.PlanejadorDePeriodoFacil;
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
	
	public void planejaProximoPeriodoFacil(Usuario usuario) {
		usuario.getPlano().setPlanejadorProximoPeriodo(new PlanejadorDePeriodoFacil());
		usuario.getPlano().planejaProximoPeriodo();
		usuario.update();
	}

	public void planejaProximoPeriodoDificil(Usuario usuario) {
		usuario.getPlano().setPlanejadorProximoPeriodo(new PlanejadorDePeriodoDificil());
		usuario.getPlano().planejaProximoPeriodo();
		usuario.update();
	}
	
	public void avancaPeriodoAtual(Usuario usuario) {
		usuario.getPlano().avancaPeriodoAtual();
		usuario.update();
	}
	public void reduzPeriodoAtual(Usuario usuario) {
		usuario.getPlano().reduzPeriodoAtual();
		usuario.update();
	}
	
	
	public void iniciaGradeComum(Usuario usuario){
		Grade grade = new Grade();
		usuario.getPlano().iniciaPlanoComum(grade);
		usuario.update();
	}
	
	public void iniciaGradeSugerida(Usuario usuario){
		Grade grade = new Grade();
		usuario.getPlano().iniciaPlanoSugerido(grade);
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
	
	public void adicionarDica(Long codigo, Usuario usuario, String dica) {
		Disciplina disciplina = getDisciplinaPorCodigo(codigo);
		
		Dica novaDica = new Dica(usuario, dica);
		novaDica.save();
		
		disciplina.addDica(novaDica);
		disciplina.update();
	}

	public void getPerfil(Usuario usuarioPorEmail) {
		
	}
}
