package model;

public class SemRestricoes implements RestritorDeAlocacao {

	@Override
	public boolean podeSerAlocada(Disciplina disciplina, Periodo periodo) {
		return true;
	}

}
