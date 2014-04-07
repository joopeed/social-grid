package model;

public interface RestritorDeAlocacao {

	abstract boolean podeSerAlocada(Disciplina disciplina, Periodo periodo);
	
}
