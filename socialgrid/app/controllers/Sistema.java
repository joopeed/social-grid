package controllers;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import model.Disciplina;
import model.Grade;
import model.Plano;
import model.Usuario;
import play.db.ebean.Model.Finder;

public class Sistema {
	private CadastroUsuario cadastro;
	private Finder<Integer, Grade> gradeFinder;
	
	public Sistema() {
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
			  catch (ParserConfigurationException e) { }
			  catch (SAXException e) { }
		}
	}
	
	private Grade getGrade() {
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
	
	public void alocarDisciplina(Usuario usuario, String nome, int idxPeriodo) {
		usuario.getPlano().addDisciplina(getGrade().getDisciplinaPorNome(nome), idxPeriodo);
		usuario.update();
	}

	public void desalocarDisciplina(Usuario usuario, String nome) {
		usuario.getPlano().removeDisciplina(getGrade().getDisciplinaPorNome(nome));
		usuario.update();
	}
	
	public List<Disciplina> getDisciplinasOfertadas(Usuario usuario) {
		return usuario.getPlano().getDisciplinasOfertadas();
	}

	public Set<Disciplina> getDisciplinasAlocadas(Usuario usuario) {
		return usuario.getPlano().getDisciplinasAlocadas();
	}
}
