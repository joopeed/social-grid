package model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Disciplina implements Comparable<Disciplina>  {
	private String nome;
	private int creditos;

	private Set<Disciplina> dependentes; 
	private Set<Disciplina> requisitos;
	
	public Disciplina(String novo_nome, int quantidadeCreditos) {
		nome = novo_nome;
		creditos = quantidadeCreditos;
		
		dependentes = new HashSet<Disciplina>();
		requisitos = new HashSet<Disciplina>();
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
}
