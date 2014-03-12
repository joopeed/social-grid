package model;

import java.util.ArrayList;
import java.util.List;

public class PlanejaPeriodoFacil implements PlanejaPeriodo {
	private final int MIM_CREDITOS = 14;

	@Override
	public List<Disciplina> quaisAlocar(Plano plano) {
		List<Disciplina> paraAlocar = new ArrayList<Disciplina>();
		List<Disciplina> possiveis = filtraSemRequisitos(plano.getDisciplinasOfertadas(), plano);
		Disciplina proximaFacil;
		int creditos = 0;
		
		while(creditos < MIM_CREDITOS) {
			proximaFacil = maisFacil(possiveis);
			creditos += proximaFacil.getCreditos();
			paraAlocar.add(proximaFacil);
			possiveis.remove(proximaFacil);
		}
		
		return paraAlocar;
	}

	private Disciplina maisFacil(List<Disciplina> possiveis) {
		Disciplina maisFacil = possiveis.get(0);
		
		for (Disciplina disciplina: possiveis) {
			if (disciplina.getDificuldadeMedia() < maisFacil.getDificuldadeMedia()) {
				maisFacil = disciplina;
			}
		}
		
		return maisFacil;
	}
	
	private List<Disciplina> filtraSemRequisitos(List<Disciplina> ofertadas, Plano plano) {
		List<Disciplina> comRequisitos = new ArrayList<Disciplina>();
		
		for (Disciplina disciplina: ofertadas) {
			if (plano.temRequisitos(disciplina, plano.getPeriodoAtual() + 1)) {
				comRequisitos.add(disciplina);
			}
		}
		
		return comRequisitos;
	}
	
}
