package model;

import play.db.ebean.Model.Finder;

public class CadastroUsuario {
	private Finder<String, Usuario> finder;
	
	public CadastroUsuario() {
		finder = new Finder<String, Usuario>(String.class, Usuario.class);
	}
	
	public void cadastrarUsuario(String nome, String email, String senha, Plano plano) throws CadastroUsuarioException {
		Usuario usuario = new Usuario(nome, email, senha, plano);
		
		if (finder.byId(email) != null) {
			throw new CadastroUsuarioException();
		}
		
		usuario.save();
	}
	
	public Usuario autenticarUsuario(String email, String senha) {
		Usuario usuarioEncontrado = getUsuarioPorEmail(email);
		Usuario usuarioAutenticado = null;
		
		if (usuarioEncontrado != null) {
			if (usuarioEncontrado.autenticar(senha)) {
				usuarioAutenticado = usuarioEncontrado;
			}
		}
		
		return usuarioAutenticado;
	}
	
	public Usuario getUsuarioPorEmail(String email) {
		return finder.byId(email);
	}
}
