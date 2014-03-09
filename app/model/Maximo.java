package model;

public class Maximo implements RegraDeAlocacao {
	private final int MAX_CREDITOS = 28;
	
	@Override
	public boolean podeSerAlocada(Disciplina disciplina, Periodo periodo) {
		return periodo.getTotalDeCreditos() + disciplina.getCreditos() <= MAX_CREDITOS;
	}

}
