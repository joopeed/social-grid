package model;

import java.util.ArrayList;
import java.util.List;

public class PlanejaPeriodoDificil implements PlanejaPeriodo {
	private final int MAX_CREDITOS = 28;

	@Override
	public List<Disciplina> quaisAlocar(Plano plano) {
		List<Disciplina> paraAlocar = new ArrayList<Disciplina>();
		List<Disciplina> possiveis = filtraSemRequisitos(plano.getDisciplinasOfertadas(), plano);
		Disciplina proximaDificil;
		int creditos = 0;
		
		while(true) {
			proximaDificil = maisDificil(possiveis);
			creditos += proximaDificil.getCreditos();
			
			if (creditos > MAX_CREDITOS) break;
			
			paraAlocar.add(proximaDificil);
			possiveis.remove(proximaDificil);
		}
		
		return paraAlocar;
	}

	private Disciplina maisDificil(List<Disciplina> possiveis) {
		Disciplina maisDificil = possiveis.get(0);
		
		for (Disciplina disciplina: possiveis) {
			if (disciplina.getDificuldadeMedia() > maisDificil.getDificuldadeMedia()) {
				maisDificil = disciplina;
			}
		}
		
		return maisDificil;
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
