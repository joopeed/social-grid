package controllers;

import java.util.List;

import model.Plano;
import model.Usuario;
import play.db.ebean.Model.Finder;

public class CadastroUsuario {
	private Finder<String, Usuario> finder;
	
	public CadastroUsuario() {
		finder = new Finder<String, Usuario>(String.class, Usuario.class);
	}
	
	public void cadastrarUsuario(String nome, String email, String senha) throws CadastroUsuarioException {
		Grade grade = new Grade();
		Plano plano = new Plano();
		
		plano.iniciaPrePlano(grade);
		
		Usuario usuario = new Usuario(nome, email, senha, plano);
		
		if (email != null && finder.byId(email) != null) {
			throw new CadastroUsuarioException();
		}
		
		usuario.save();
	}
	
	public Usuario autenticarUsuario(String email, String senha) {
		Usuario usuarioEncontrado = null;
		Usuario usuarioAutenticado = null;

		if (email != null) {
			usuarioEncontrado = getUsuarioPorEmail(email);
		}
		
		if (usuarioEncontrado != null) {
			if (usuarioEncontrado.autenticar(senha)) {
				usuarioAutenticado = usuarioEncontrado;
			}
		}
		
		return usuarioAutenticado;
	}
	
	public Usuario getUsuarioPorEmail(String email) {
		Usuario usuario = null;
		
		if (email != null) {
			usuario = finder.byId(email);
		}
		
		return usuario;
	}
	
	public List<Usuario> getUsuarioPorNome(String query) {
		List<Usuario> result = null;
		
		if (query != null) {
			result = finder.where()
					.ilike("nome","%"+  query + "%")
                    .findList();
		}
		
		return result;
	}
}
