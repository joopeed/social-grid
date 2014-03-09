package controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.Entity;
import javax.xml.parsers.ParserConfigurationException;

import model.Disciplina;

import org.xml.sax.SAXException;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

/**
 * Armazena todas as disciplinas do sistema.
 */
public class Grade {
	
	private Finder<Long, Disciplina> disciplinaFinder;

	
	/**
	 * Construtor
	 * @throws IOException Erro na leitura do arquivo.
	 */
	public Grade() {
		disciplinaFinder = new Finder<Long, Disciplina>(Long.class, Disciplina.class);
		preencheGrade();
	}

	/**
	 * Preenche a grade com disciplinas.
	 * @throws IOException Erro na leitura do arquivo.
	 * @throws ParserConfigurationException Erro na configuração do parser XML
	 * @throws SAXException Erro no arquivo XML
	 */
	private void preencheGrade() {
		if (disciplinaFinder.findRowCount() < 1) {
			try {
				Carregador carregador = new Carregador();
				
				List<Disciplina> disciplinas = carregador.preencheGrade();
				
				for (Disciplina disciplina : disciplinas) {
					disciplina.save();
				}
				
				carregador.adicionaDependentesERequisitos(disciplinas);
				
				for (Disciplina disciplina : disciplinas) {
					disciplina.update();
				}
			} catch (IOException e) { }
			  catch (ParserConfigurationException e) { }
			  catch (SAXException e) { }
		}
	}

	/**
	 * Busca e retorna por uma disciplina pelo nome.
	 * @param nome Nome da disciplina procurada.
	 * @return Disciplina procurada ou Null em caso de mesma não existir.
	 */
	public Disciplina getDisciplinaPorNome(String nome) {
		return disciplinaFinder.where().eq("nome", nome).findUnique();
	}
	
	/**
	 * Busca e retorna por uma disciplina pelo código.
	 * @param nome Código da disciplina procurada.
	 * @return Disciplina procurada ou Null em caso de mesma não existir.
	 */
	public Disciplina getDisciplinaPorCodigo(Long codigo) {
		return disciplinaFinder.byId(codigo);
	}

	
	/**
	 * Pega todas as disciplinas da grade.
	 * @return Conjunto de disciplinas.
	 */
	public List<Disciplina> getTodasDisciplinas() {
		return disciplinaFinder.all();
	}
}