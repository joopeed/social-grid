package controllers;

import java.io.IOException;
import java.util.Set;

import model.CadastroUsuario;
import model.CadastroUsuarioException;
import model.Disciplina;
import model.Grade;
import model.Plano;
import model.Usuario;

public class Controlador {
	private Grade grade;
	private CadastroUsuario cadastro;
	
	public Controlador() throws IOException {
		grade = new Grade();
		grade.save();
		
		cadastro = new CadastroUsuario();
	}
	
	public void cadastrarUsuario(String nome, String email, String senha) throws CadastroUsuarioException {
		cadastro.cadastrarUsuario(nome, email, senha, new Plano(grade));
	}
	
	public Usuario autenticarUsuario(String email, String senha) {
		return cadastro.autenticarUsuario(email, senha);
	}
	
	public Usuario getUsuarioPorEmail(String email) {
		return cadastro.getUsuarioPorEmail(email);
	}
	
	public Disciplina getDisciplinaPorNome(String nome) {
		return grade.getDisciplinaPorNome(nome);
	}
	
	public Set<Disciplina> getDisciplinasAlocadas(Usuario usuario) {
		return usuario.getPlano().getDisciplinasAlocadas();
	}

	public void addDisciplina(Usuario usuario, String nome, int idxPeriodo) {
		usuario.getPlano().addDisciplina(grade.getDisciplinaPorNome(nome), idxPeriodo);
	}

	public void removeDisciplina(Usuario usuario, String nome) {
		usuario.getPlano().removeDisciplina(grade.getDisciplinaPorNome(nome));
	}
	
}
