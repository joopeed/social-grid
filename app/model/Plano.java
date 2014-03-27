package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import controllers.Grade;
import play.db.ebean.Model;

/**
 * Implementa o plano de formação com períodos a serem preenchidos com disciplinas.
 */
@Entity
public class Plano extends Model {
	
	private static final long serialVersionUID = 5092375276705062916L;
	
	@Id
	public Long id;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Periodo> periodos;
	private PlanejaPeriodo planejadorProximoPeriodo;
	private int qntPeriodos;
	private int idxPeriodoAtual;
	
	/**
	 * Construtor
	 * @param nova_grade grade com todas as disciplinas do período do curso.
	 */
	public Plano() {
		periodos = new ArrayList<Periodo>();
		idxPeriodoAtual = 0;
		
	}
	
	/**
	 * Inicia o plano com as disciplinas sugeridas.
	 */
	public void iniciaPlanoSugerido(Grade grade) {
		qntPeriodos = 10;
		for (int i = 0 ; i < qntPeriodos ; i++)
			periodos.add(new Periodo());
		
		addDisciplina(grade.getDisciplinaPorNome("Cálculo Diferencial e Integral I"), 0);
		addDisciplina(grade.getDisciplinaPorNome("Álgebra Vetorial e Geometria Analítica"), 0);
		addDisciplina(grade.getDisciplinaPorNome("Leitura e Produção de Textos"), 0);
		addDisciplina(grade.getDisciplinaPorNome("Programação I"), 0);
		addDisciplina(grade.getDisciplinaPorNome("Introdução à Computação"), 0);
		addDisciplina(grade.getDisciplinaPorNome("Laboratório de Programação I"), 0);
		
		addDisciplina(grade.getDisciplinaPorNome("Cálculo Diferencial e Integral II"), 1);
		addDisciplina(grade.getDisciplinaPorNome("Programação II"), 1);
		addDisciplina(grade.getDisciplinaPorNome("Laboratório de Programação II"), 1);
		addDisciplina(grade.getDisciplinaPorNome("Teoria dos Grafos"), 1);
		addDisciplina(grade.getDisciplinaPorNome("Matemática Discreta"), 1);
		addDisciplina(grade.getDisciplinaPorNome("Metodologia Científica"), 1);
		addDisciplina(grade.getDisciplinaPorNome("Fundamentos de Física Clássica"), 1);
		
		addDisciplina(grade.getDisciplinaPorNome("Álgebra Linear I"), 2);
		addDisciplina(grade.getDisciplinaPorNome("Probabilidade e Estatística"), 2);
		addDisciplina(grade.getDisciplinaPorNome("Teoria da Computação"), 2);
		addDisciplina(grade.getDisciplinaPorNome("Estrutura de Dados e Algoritmos"), 2);
		addDisciplina(grade.getDisciplinaPorNome("Fundamentos de Física Moderna"), 2);
		addDisciplina(grade.getDisciplinaPorNome("Gerência da Informação"), 2);
		addDisciplina(grade.getDisciplinaPorNome("Laboratório de Estrutura de Dados e Algoritmos"), 2);
		
		addDisciplina(grade.getDisciplinaPorNome("Métodos Estatísticos"), 3);
		addDisciplina(grade.getDisciplinaPorNome("Paradigmas de Linguagem de Programação"), 3);
		addDisciplina(grade.getDisciplinaPorNome("Lógica Matemática"), 3);
		addDisciplina(grade.getDisciplinaPorNome("Organização e Arquitetura de Computadores I"), 3);
		addDisciplina(grade.getDisciplinaPorNome("Engenharia de Software I"), 3);
		addDisciplina(grade.getDisciplinaPorNome("Sistemas de Informação I"), 3);
		addDisciplina(grade.getDisciplinaPorNome("Laboratório de Organização e Arquitetura de Computadores"), 3);
		
		addDisciplina(grade.getDisciplinaPorNome("Informática e Sociedade"), 4);
		addDisciplina(grade.getDisciplinaPorNome("Análise e Técnica de Algoritmos"), 4);
		addDisciplina(grade.getDisciplinaPorNome("Compiladores"), 4);
		addDisciplina(grade.getDisciplinaPorNome("Redes de Computadores"), 4);
		addDisciplina(grade.getDisciplinaPorNome("Banco de Dados I"), 4);
		addDisciplina(grade.getDisciplinaPorNome("Sistemas de Informação II"), 4);
		addDisciplina(grade.getDisciplinaPorNome("Laboratório de Engenharia de Software"), 4);
		
		addDisciplina(grade.getDisciplinaPorNome("Direito e Cidadania"), 5);
		addDisciplina(grade.getDisciplinaPorNome("Sistemas Operacionais"), 5);
		addDisciplina(grade.getDisciplinaPorNome("Interconexão de Redes de Computadores"), 5);
		addDisciplina(grade.getDisciplinaPorNome("Banco de Dados II"), 5);
		addDisciplina(grade.getDisciplinaPorNome("Inteligência Artificial I"), 5);
		addDisciplina(grade.getDisciplinaPorNome("Laboratório de Interconexão de Redes de Computadores"), 5);
		
		addDisciplina(grade.getDisciplinaPorNome("Métodos e Software Numéricos"), 6);
		addDisciplina(grade.getDisciplinaPorNome("Avaliação de Desempenho de Sistemas Discretos"), 6);
		addDisciplina(grade.getDisciplinaPorNome("Projeto em Computação I"), 6);
		
		addDisciplina(grade.getDisciplinaPorNome("Projeto em Computação II"), 7);

	}

	/**
	 * Inicia o plano comum.
	 */
	public void iniciaPlanoComum(Grade grade) {
		qntPeriodos = 10;
		for (int i = 0 ; i < qntPeriodos ; i++)
			periodos.add(new Periodo());
		
		addDisciplina(grade.getDisciplinaPorNome("Cálculo Diferencial e Integral I"), 0);
		addDisciplina(grade.getDisciplinaPorNome("Álgebra Vetorial e Geometria Analítica"), 0);
		addDisciplina(grade.getDisciplinaPorNome("Leitura e Produção de Textos"), 0);
		addDisciplina(grade.getDisciplinaPorNome("Programação I"), 0);
		addDisciplina(grade.getDisciplinaPorNome("Introdução à Computação"), 0);
		addDisciplina(grade.getDisciplinaPorNome("Laboratório de Programação I"), 0);
		
		addDisciplina(grade.getDisciplinaPorNome("Cálculo Diferencial e Integral II"), 1);
		addDisciplina(grade.getDisciplinaPorNome("Programação II"), 1);
		addDisciplina(grade.getDisciplinaPorNome("Laboratório de Programação II"), 1);
		addDisciplina(grade.getDisciplinaPorNome("Teoria dos Grafos"), 1);
		addDisciplina(grade.getDisciplinaPorNome("Matemática Discreta"), 1);
		addDisciplina(grade.getDisciplinaPorNome("Metodologia Científica"), 1);
		addDisciplina(grade.getDisciplinaPorNome("Fundamentos de Física Clássica"), 1);
		
		addDisciplina(grade.getDisciplinaPorNome("Álgebra Linear I"), 2);
		addDisciplina(grade.getDisciplinaPorNome("Probabilidade e Estatística"), 2);
		addDisciplina(grade.getDisciplinaPorNome("Teoria da Computação"), 2);
		addDisciplina(grade.getDisciplinaPorNome("Estrutura de Dados e Algoritmos"), 2);
		addDisciplina(grade.getDisciplinaPorNome("Fundamentos de Física Moderna"), 2);
		addDisciplina(grade.getDisciplinaPorNome("Gerência da Informação"), 2);
		addDisciplina(grade.getDisciplinaPorNome("Laboratório de Estrutura de Dados e Algoritmos"), 2);
		
		addDisciplina(grade.getDisciplinaPorNome("Métodos Estatísticos"), 3);
		addDisciplina(grade.getDisciplinaPorNome("Paradigmas de Linguagem de Programação"), 3);
		addDisciplina(grade.getDisciplinaPorNome("Lógica Matemática"), 3);
		addDisciplina(grade.getDisciplinaPorNome("Organização e Arquitetura de Computadores I"), 3);
		addDisciplina(grade.getDisciplinaPorNome("Engenharia de Software I"), 3);
		addDisciplina(grade.getDisciplinaPorNome("Sistemas de Informação I"), 3);
		addDisciplina(grade.getDisciplinaPorNome("Laboratório de Organização e Arquitetura de Computadores"), 3);
		
		addDisciplina(grade.getDisciplinaPorNome("Informática e Sociedade"), 4);
		addDisciplina(grade.getDisciplinaPorNome("Análise e Técnica de Algoritmos"), 4);
		addDisciplina(grade.getDisciplinaPorNome("Compiladores"), 4);
		addDisciplina(grade.getDisciplinaPorNome("Redes de Computadores"), 4);
		addDisciplina(grade.getDisciplinaPorNome("Banco de Dados I"), 4);
		addDisciplina(grade.getDisciplinaPorNome("Sistemas de Informação II"), 4);
		addDisciplina(grade.getDisciplinaPorNome("Laboratório de Engenharia de Software"), 4);
		
		addDisciplina(grade.getDisciplinaPorNome("Direito e Cidadania"), 5);
		addDisciplina(grade.getDisciplinaPorNome("Sistemas Operacionais"), 5);
		addDisciplina(grade.getDisciplinaPorNome("Interconexão de Redes de Computadores"), 5);
		addDisciplina(grade.getDisciplinaPorNome("Banco de Dados II"), 5);
		addDisciplina(grade.getDisciplinaPorNome("Inteligência Artificial I"), 5);
		addDisciplina(grade.getDisciplinaPorNome("Laboratório de Interconexão de Redes de Computadores"), 5);
		
		addDisciplina(grade.getDisciplinaPorNome("Métodos e Software Numéricos"), 6);
		addDisciplina(grade.getDisciplinaPorNome("Avaliação de Desempenho de Sistemas Discretos"), 6);
		addDisciplina(grade.getDisciplinaPorNome("Projeto em Computação I"), 6);
		
		addDisciplina(grade.getDisciplinaPorNome("Projeto em Computação II"), 7);

	}
	
	
	/**
	 * Pega todas as disciplinas já alocadas no sistema.
	 * @return Conjunto com as disciplinas alucadas.
	 */
	public Set<Disciplina> getDisciplinasAlocadas() {
		Set<Disciplina> disciplinas = new HashSet<Disciplina>();
		for (Periodo periodo: periodos) {
			for (Disciplina disciplina: periodo.getDisciplinas()) {
				disciplinas.add(disciplina);
			}
		}
		
		return disciplinas;
	}
	
	/**
	 * Pega as disciplinas da grade que não estão alocadas.
	 * @return Disciplinas não alocadas.
	 */
	public List<Disciplina> getDisciplinasOfertadas(Grade grade) {
		List<Disciplina> disciplinasOfertadas = grade.getTodasDisciplinas();
		
		for (Disciplina alocada: getDisciplinasAlocadas()) {
			if (disciplinasOfertadas.contains(alocada)) {
				disciplinasOfertadas.remove(alocada);
			}
		}
		
		return disciplinasOfertadas;
	} 

	/**
	 * Aloca uma disciplina para um período.
	 * @param disciplina Disciplina à ser alocada.
	 * @param idxPeriodo Índice do período que recebe-rá a disciplina.
	 */
	public void addDisciplina(Disciplina disciplina, int idxPeriodo) {
		setPeriodoAtual(idxPeriodoAtual);
		
		if (!periodos.get(idxPeriodo).contemDisciplina(disciplina)) {
			if (getDisciplinasAlocadas().contains(disciplina)) {
				removeDisciplina(disciplina);
			}
			periodos.get(idxPeriodo).adicionaDisciplina(disciplina);
		}
	}
		
	/**
	 * Retorna se uma disciplina tem os requisitos nos periodos anteriores
	 * @param disciplina A disciplina
	 * @param idxPeriodo Periodo Atual
	 * @return True se tem requisitos, False se não
	 */
	public boolean temRequisitos(Disciplina disciplina, int  idxPeriodo) {
		boolean temRequisitos = true;
		for (Disciplina requisito: disciplina.getRequisitos()) {
			temRequisitos &= temDisciplinaAlocada(requisito, idxPeriodo);
		}
		return temRequisitos;
	}
	
    /**
     * Diz se um periodo tem qualquer disciplina incorreta
     * @param idxPeriodo O periodo a verificar
     * @return True se sim, False, se não
     */
	public boolean temDisciplinaIncorreta(int idxPeriodo) {
		boolean temTodosRequisitos = true;
		for(Disciplina disciplina: periodos.get(idxPeriodo).getDisciplinas()){
			temTodosRequisitos &= temRequisitos(disciplina, idxPeriodo);
		}
		return !temTodosRequisitos;
	}
	
	/**
	 * Diz se uma determinada disciplina esta incorreta pela falta de um requisito
	 * @param disciplina A disciplina a verificar
	 * @param idxPeriodo O periodo relacionado
	 * @return True se a disciplina nao tem todos os requisitos, False, se tem
	 */
	public boolean estaIncorreta(Disciplina disciplina, int idxPeriodo) {
		return !temRequisitos(disciplina, idxPeriodo);
		
	}
	
	/**
	 * Retorna uma lista de requisitos em falta para uma disciplina 
	 * @param disciplina A disciplina a verificar
	 * @param idxPeriodo O periodo em que a disciplina está
	 * @return Lista de disciplinas em falta
	 */
	
	public List<Disciplina> getRequisitosEmFaltaDe(Disciplina disciplina, int idxPeriodo) {
		ArrayList<Disciplina> requisitosEmFalta = new ArrayList<Disciplina>();
		for (Disciplina requisito: disciplina.getRequisitos()) {
			if(!temDisciplinaAlocada(requisito, idxPeriodo))
				requisitosEmFalta.add(requisito);
		}
		return requisitosEmFalta;
	}
	
	/**
	 * Veririfa se uma disciplina esta alocada em um periodo
	 * @param disciplina A disciplina a verificar
	 * @param idxPeriodo O periodo a verificar
	 * @return True, se a disicplina esta no periodo, False, se não.
	 */

	private boolean temDisciplinaAlocada(Disciplina disciplina, int idxPeriodo) {
		for (int i = 0; i < idxPeriodo; i++) {
			Periodo periodo = periodos.get(i);
			if (periodo.contemDisciplina(disciplina)) {
				return true;
			}
		}
		return false;
	}


	/**
	 * Remove uma disciplina alocada.
	 * @param disciplina Disciplina à ser removida.
	 */
	public void removeDisciplina(Disciplina disciplina) {
		for (Disciplina dependente: disciplina.getDependentes()) {
			removeDisciplina(dependente);
		}
		desalocaDisciplina(disciplina);
		setPeriodoAtual(idxPeriodoAtual);
	}
	
	private void desalocaDisciplina(Disciplina disciplina) {
		for (Periodo periodo: periodos) {
			if (periodo.contemDisciplina(disciplina)) {
				periodo.removeDisciplina(disciplina);
				return;
			}
		}
	}

	/**
	 * Adiciona as disciplinas no período posterial ao atual
	 * com base na estratégia de planejamento.
	 */
	public void planejaProximoPeriodo() {
		limpaPeriodosPosterioresAoAtual();
		List<Disciplina> paraAlocar = planejadorProximoPeriodo.quaisAlocar(this);
		
		for (Disciplina disciplina: paraAlocar) {
			addDisciplina(disciplina, idxPeriodoAtual + 1);
		}
	}
	
	private void limpaPeriodosPosterioresAoAtual() {
		for (int i = idxPeriodoAtual + 1 ; i < 10 ; i++) {
			getPeriodos().get(i).removeTodasAsDisciplinas();
		}
	}
	
	/**
	 * Calcula a diferença entre os planos.
	 * @param plano2 Plano à ser comparado.
	 * @return Direrença entre os planos.
	 */
	public int getDiferencaDePlanos(Plano plano2) {
		int diferenca = 0;
		Set<Disciplina> maisDisciplinas;
		Set<Disciplina> menosDisciplinas;
		
		if (getDisciplinasAlocadas().size() > plano2.getDisciplinasAlocadas().size()) {
			maisDisciplinas = getDisciplinasAlocadas();
			menosDisciplinas = plano2.getDisciplinasAlocadas();
		} else {
			maisDisciplinas = plano2.getDisciplinasAlocadas();
			menosDisciplinas = getDisciplinasAlocadas();
		}
		
		for (Disciplina disciplina: maisDisciplinas) {
			if (!menosDisciplinas.contains(disciplina)) {
				diferenca++;
			}
		}

		return diferenca;
	}
	
	public List<Periodo> getPeriodos() {
		return periodos;
	}
	
	public void setPlanejadorProximoPeriodo(PlanejaPeriodo novoPlanejador) {
		planejadorProximoPeriodo = novoPlanejador;
	}
	
	
	
	/**
	 * 
	 */
	public boolean isPeriodoAtual(int idx){
		return idx == idxPeriodoAtual;
	}

	/**
	 * Define as estratégias de alocação nos períodos baseado no período atual.
	 * @param idxAtual Índice do período que será o atual.
	 */
	public void setPeriodoAtual(int idxAtual) {
		int ultimo = indiceUltimoPeriodo();
		
		idxPeriodoAtual = idxAtual;
		
		periodos.get(ultimo).setRegraDeAlocacao(new SemRestricao());
		for (int i = 0 ; i < idxAtual ; i++) {
			periodos.get(i).setRegraDeAlocacao(new Maximo());
		}
		
		for (int i = idxAtual ; i < ultimo ; i++) {
			periodos.get(i).setRegraDeAlocacao(new MaximoEMinimo());
		}
		
	
	}

	private int indiceUltimoPeriodo() {
		for (int ultimo = periodos.size() - 1 ; ultimo >= 0 ; ultimo--) {
			if (periodos.get(ultimo).getTotalDeCreditos() != 0) {
				return ultimo;
			}
		}
		
		return 0;
	}

	public int getPeriodoAtual() {
		return idxPeriodoAtual;
	}

	public void avancaPeriodoAtual() {
		if(getPeriodoAtual() + 1 <= qntPeriodos)
			setPeriodoAtual(getPeriodoAtual() + 1);
	}
	
	public void reduzPeriodoAtual() {
		if(getPeriodoAtual() - 1 >= 0)
			setPeriodoAtual(getPeriodoAtual() - 1);
	}
}
