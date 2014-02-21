package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Usuario extends Model {
	
	private static final long serialVersionUID = -4882979229999226419L;
	
	@Id
	private String email;
	@Required
	private String nome;
	@Required
	private String senha;
	@OneToOne(cascade=CascadeType.ALL)
	private Plano plano;

	public Usuario(String nome, String email, String senha, Plano plano) {
		setNome(nome);
		setEmail(email);
		setSenha(senha);
		setPlano(plano);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.toLowerCase();
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Plano getPlano() {
		return plano;
	}
	
	public void setPlano(Plano plano) {
		this.plano = plano;
	}

	public boolean autenticar(String senha) {
		boolean autenticado = false;

		if (getSenha().equals(senha)) {
			autenticado = true;
		}

		return autenticado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;

		return true;
	}
}
