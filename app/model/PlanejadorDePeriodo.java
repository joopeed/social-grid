package model;

import java.util.List;

public interface PlanejadorDePeriodo {

	abstract public List<Disciplina> quaisAlocar(Plano plano);
	
}
