package model;

import java.util.HashSet;
import java.util.Set;

public class CadastroUsuario {
	private Set<Usuario> usuarios;
	
	public CadastroUsuario() {
		usuarios = new HashSet<Usuario>();
	}
	
	public boolean cadastrarUsuario(String nome, String email, String senha) {
		Usuario usuario = new Usuario(nome, email, senha);
		
		return usuarios.add(usuario);
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
		Usuario usuarioEncontrado = null;
		
		for (Usuario usuario : usuarios) {
			if (usuario.getEmail().equals(email)) {
				usuarioEncontrado = usuario;
			}
		}
		
		return usuarioEncontrado;
	}
}
