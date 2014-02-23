package controllers;

import java.io.IOException;
import java.util.Set;

import model.Disciplina;
import model.Grade;
import model.Plano;
import model.Usuario;
import play.db.ebean.Model.Finder;

public class Controlador {
	private CadastroUsuario cadastro;
	private Finder<Integer, Grade> gradeFinder;
	
	public Controlador() {
		gradeFinder = new Finder<Integer, Grade>(Integer.class, Grade.class);
		carregarGrade();
		cadastro = new CadastroUsuario();
	}
	
	private void carregarGrade() {
		if (gradeFinder.findRowCount() < 1) {
			try {
				Grade grade = new Grade();
				grade.preencheGrade();
				grade.save();
			} catch (IOException e) { }
		}
	}
	
	public Grade getGrade() {
		return gradeFinder.all().get(0);
	}
	
	public void cadastrarUsuario(String nome, String email, String senha) throws CadastroUsuarioException {
		Plano plano = new Plano(getGrade());
		plano.iniciaPrePlano();
		
		cadastro.cadastrarUsuario(nome, email, senha, plano);
	}
	
	public Usuario autenticarUsuario(String email, String senha) {
		return cadastro.autenticarUsuario(email, senha);
	}
	
	public Usuario getUsuarioPorEmail(String email) {
		return cadastro.getUsuarioPorEmail(email);
	}
	
	public Disciplina getDisciplinaPorNome(String nome) {
		return getGrade().getDisciplinaPorNome(nome);
	}
	
	public Set<Disciplina> getDisciplinasAlocadas(Usuario usuario) {
		return usuario.getPlano().getDisciplinasAlocadas();
	}

	public void addDisciplina(Usuario usuario, String nome, int idxPeriodo) {
		usuario.getPlano().addDisciplina(getGrade().getDisciplinaPorNome(nome), idxPeriodo);
	}

	public void removeDisciplina(Usuario usuario, String nome) {
		usuario.getPlano().removeDisciplina(getGrade().getDisciplinaPorNome(nome));
	}
	
}
