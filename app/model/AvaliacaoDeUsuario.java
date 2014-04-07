package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;

/**
 * Representa a dificuldade que um usuário atribui a uma disciplina.
 */
@Entity
public class AvaliacaoDeUsuario extends Model {

	private static final long serialVersionUID = -7849251856375143243L;
	
	@Id
	public Long id;
	@ManyToOne
	private Usuario usuario;
	private int dificuldade;
	
	public AvaliacaoDeUsuario(Usuario novoUsuario, int novaDificuldade) {
		usuario = novoUsuario;
		dificuldade = novaDificuldade;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getDificuldade() {
		return dificuldade;
	}
	
	public void setDificuldade(int dificuldade) {
		this.dificuldade = dificuldade ;
	}
	
}
