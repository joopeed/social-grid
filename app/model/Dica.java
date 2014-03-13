package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import play.db.ebean.Model;

/**
 * Representa uma dica que é associada a um objeto Disciplina.
 */
@Entity
public class Dica extends Model {
	
	private static final long serialVersionUID = 5306648628423069991L;
	
	@Id
	public Long id;
	@OneToOne
	private Usuario autor;
	@ManyToMany
	private List<Usuario> usuariosQueCurtiram;
	private String texto;
	
	/**
	 * Construtor
	 * @param novo_texto Texto da dica.
	 */
	public Dica(Usuario usuario, String novo_texto) {
		autor = usuario;
		texto = novo_texto;
		usuariosQueCurtiram = new ArrayList<Usuario>();
	}
	
	/**
	 * Adiciona um like na dica.
	 */
	public void adicionaLike(Usuario usuario){
		usuariosQueCurtiram.add(usuario);
	}
	
	/**
	 * Remove um like da dica.
	 */
	public void removeLike(Usuario usuario){
		usuariosQueCurtiram.remove(usuario);
	}
	
	public int getQuantidadeDeLikes(){
		return usuariosQueCurtiram.size();
	}
	
	public String getTexto() {
		return texto;
	}

	public String getNomeDoAutor() {
		return autor.getNome();
	}
	public String getEmailDoAutor() {
		return autor.getEmail();
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
