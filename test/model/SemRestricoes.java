package model;

public class SemRestricoes implements RegraDeAlocacao {

	@Override
	public boolean podeSerAlocada(Disciplina disciplina, Periodo periodo) {
		return true;
	}

}
