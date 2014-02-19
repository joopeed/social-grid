package model;

/**
 * Representa uma dica que é associada a um objeto Disciplina.
 */
public class Dica {
	
	private String texto;
	
	/**
	 * Construtor
	 * @param novo_texto Texto da dica.
	 */
	public Dica(String novo_texto) {
		texto = novo_texto;
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
