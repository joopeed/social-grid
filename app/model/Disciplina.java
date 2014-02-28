package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import play.db.ebean.Model;

/**
 * Representa uma disciplina no sistema.
 */
@Entity
public class Disciplina extends Model implements Comparable<Disciplina> {
	
	private static final long serialVersionUID = -1290335955963203228L;

	@Id
	public Long id;
	private String nome;
	private int creditos;
	@ManyToMany
	@JoinTable(name="disciplinas_dependentes", joinColumns=@JoinColumn(name="disciplina_id", referencedColumnName="id"),
	inverseJoinColumns=@JoinColumn(name="dependente_id", referencedColumnName="id"))
	private Set<Disciplina> dependentes;
	@ManyToMany
	@JoinTable(name="disciplinas_requisitos", joinColumns=@JoinColumn(name="disciplina_id", referencedColumnName="id"),
			inverseJoinColumns=@JoinColumn(name="requisito_id", referencedColumnName="id"))
	private Set<Disciplina> requisitos;
	@ManyToMany(cascade = CascadeType.REMOVE)
	private List<Dica> dicas;

	
	/**
	 * Construtor
	 * @param novo_nome Nome da disciplina.
	 * @param quantidadeCreditos Quantidade de créditos da disciplina.
	 */
	public Disciplina(String novo_nome, int quantidadeCreditos) {
		nome = novo_nome;
		creditos = quantidadeCreditos;
		
		dependentes = new HashSet<Disciplina>();
		requisitos = new HashSet<Disciplina>();
		dicas = new ArrayList<Dica>();
	}
	
	/**
	 * Adiciona uma disciplina dependente.
	 * @param disciplina Disciplina dependente à ser adicionada.
	 */
	public void acrescentaDependente(Disciplina disciplina) {
		dependentes.add(disciplina);
		disciplina.acrescentaRequisitos(this);
	}
	
	/**
	 * Adiciona uma disciplina requisito.
	 * @param disciplina Disciplina requisito à ser adicionada.
	 */
	public void acrescentaRequisitos(Disciplina disciplina) {
		requisitos.add(disciplina);
	}
	
	public String getNome() {
		return nome;
	}
	
	public int getCreditos() {
		return creditos;
	}
	
	public Set<Disciplina> getDependentes() {
		return Collections.unmodifiableSet(dependentes);
	}

	public Set<Disciplina> getRequisitos() {
		return Collections.unmodifiableSet(requisitos);
	}
	
	@Override
	public int compareTo(Disciplina disciplina) {
		return nome.compareTo(disciplina.getNome());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());

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
		Disciplina other = (Disciplina) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	/**
	 * Adiciona uma dica na disciplina
	 * @param dica Dica a ser adicionada.
	 */
	public void addDica(Dica dica) {
		dicas.add(dica);		
	}

	public List<Dica> getDicas() {
		return dicas;
	}
}
