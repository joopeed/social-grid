package model;

public class SemRestricao implements RegraDeAlocacao {

	@Override
	public boolean podeSerAlocada(Disciplina disciplina, Periodo periodo) {
		return true;
	}

}
