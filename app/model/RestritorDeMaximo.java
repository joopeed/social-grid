package model;

public class RestritorDeMaximo implements RestritorDeAlocacao {
	private final int MAX_CREDITOS = 28;
	
	@Override
	public boolean podeSerAlocada(Disciplina disciplina, Periodo periodo) {
		return periodo.getTotalDeCreditos() + disciplina.getCreditos() <= MAX_CREDITOS;
	}

}
