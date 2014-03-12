package model;

public class MaximoEMinimo implements RegraDeAlocacao {
	private final int MAX_CREDITOS = 28;
	//private final int MIM_CREDITOS = 14;

	@Override
	public boolean podeSerAlocada(Disciplina disciplina, Periodo periodo) {
		return periodo.getTotalDeCreditos() + disciplina.getCreditos() <= MAX_CREDITOS;
	}

}
