package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Disciplina implements Comparable<Disciplina>  {
	private String nome;
	private int creditos;

	private Set<Disciplina> dependentes; 
	private Set<Disciplina> requisitos;
	private List<Dica> dicas;
	
	public Disciplina(String novo_nome, int quantidadeCreditos) {
		nome = novo_nome;
		creditos = quantidadeCreditos;
		
		dependentes = new HashSet<Disciplina>();
		requisitos = new HashSet<Disciplina>();
		dicas = new ArrayList<Dica>();
	}
	
	public void acrescentaDependente(Disciplina disciplina) {
		dependentes.add(disciplina);
		disciplina.acrescentaRequisitos(this);
	}
	
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
	
	public void addDica(Dica dica) {
		dicas.add(dica);		
	}

	public List<Dica> getDicas() {
		return dicas;
	}
}
