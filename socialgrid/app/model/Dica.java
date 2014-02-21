package model;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

/**
 * Representa uma dica que é associada a um objeto Disciplina.
 */
@Entity
public class Dica extends Model {
	
	private static final long serialVersionUID = 5306648628423069991L;
	
	@Id
	public Long id;
	private String texto;
	private int likes;
	
	/**
	 * Construtor
	 * @param novo_texto Texto da dica.
	 */
	public Dica(String novo_texto) {
		texto = novo_texto;
		likes = 0;
	}
	
	/**
	 * Adiciona um like na dica.
	 */
	public void adicionaLike(){
		likes++;
	}
	
	/**
	 * Remove um like da dica.
	 */
	public void removeLike(){
		likes--;
	}
	
	public int getLikes(){
		return likes;
	}
	
	public String getTexto(){
		return texto;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dica other = (Dica) obj;
		if (texto == null) {
			if (other.texto != null)
				return false;
		} else if (!texto.equals(other.texto))
			return false;
		return true;
	}
	
}
