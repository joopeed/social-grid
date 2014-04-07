package model;

public class RestritorLivre implements RestritorDeAlocacao {

	@Override
	public boolean podeSerAlocada(Disciplina disciplina, Periodo periodo) {
		return true;
	}

}
