package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Lê o arquivo de disciplinas e criar cada disciplina no sistema.
 */
public class Carregador {
	
	/**
	 * Cria um conjunto de objetos disciplinas a partir de um arquivo.
	 * @return Conjunto de disciplinas com dependências e requisitos.
	 * @throws IOException Erro na leitura do arquivo.
	 * @throws ParserConfigurationException Erro na configuração do parser XML
	 * @throws SAXException Erro no arquivo XML
	 */
	public List<Disciplina> preencheGrade() throws IOException, ParserConfigurationException, SAXException {
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		
		String caminho = new File("res/disciplinas.xml").getCanonicalPath();
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document disciplinasDocument = db.parse(new File(caminho));
		
		Node disciplinasRoot = disciplinasDocument.getDocumentElement();
		NodeList disciplinasNodes = disciplinasRoot.getChildNodes();
		
		Disciplina disciplina;
		
		for (int i = 0; i < disciplinasNodes.getLength(); i++) {
			if (disciplinasNodes.item(i).getNodeName().equals("disciplina")) {
				long codigo = 0;
				String nome = null;
				int creditos = 0;
				
				NodeList disciplinaNodeList = disciplinasNodes.item(i).getChildNodes();
				for (int j = 0; j < disciplinaNodeList.getLength(); j++) {
					if (disciplinaNodeList.item(j).getNodeName().equals("nome")) {
						nome = (disciplinaNodeList.item(j).getTextContent());
					}
					
					if (disciplinaNodeList.item(j).getNodeName().equals("creditos")) {
						creditos = Integer.valueOf(disciplinaNodeList.item(j).getTextContent());
					}
					
					if (disciplinaNodeList.item(j).getNodeName().equals("codigo")) {
						codigo = Long.valueOf(disciplinaNodeList.item(j).getTextContent());
					}
				}
				
				disciplina = new Disciplina(nome, creditos);
				disciplina.id = codigo;
				
				disciplinas.add(disciplina);
			}
		}
		
		return disciplinas;
	}
	
	/**
	 * Adiciona disciplinas dependentes e requisitos em cada disciplina.
	 * @param disciplinas Conjunto de disciplinas sem dependências e requisitos.
	 * @return Conjunto de disciplinas com dependências e requisitos.
	 * @throws IOException Erro na leitura do arquivo.
	 * @throws ParserConfigurationException Erro na configuração do parser XML
	 * @throws SAXException Erro no arquivo XML
	 */
	protected List<Disciplina> adicionaDependentesERequisitos(List<Disciplina> disciplinas) throws IOException, ParserConfigurationException, SAXException {
		String caminho = new File("res/disciplinas.xml").getCanonicalPath();
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document disciplinasDocument = db.parse(new File(caminho));
		
		Node disciplinasRoot = disciplinasDocument.getDocumentElement();
		NodeList disciplinasNodes = disciplinasRoot.getChildNodes();
		
		Disciplina disciplina;
		
		for (int i = 0; i < disciplinasNodes.getLength(); i++) {
			if (disciplinasNodes.item(i).getNodeName().equals("disciplina")) {
				long codigo = 0;
				List<Integer> prerequisitos = new ArrayList<Integer>();
				
				NodeList disciplinaNodeList = disciplinasNodes.item(i).getChildNodes();
				for (int j = 0; j < disciplinaNodeList.getLength(); j++) {
					if (disciplinaNodeList.item(j).getNodeName().equals("codigo")) {
						codigo = Long.valueOf(disciplinaNodeList.item(j).getTextContent());
					}
					
					if (disciplinaNodeList.item(j).getNodeName().equals("codigosPrerequisitos")) {
						NodeList prerequisitosNodeList = disciplinaNodeList.item(j).getChildNodes(); 
						for (int k = 0; k < prerequisitosNodeList.getLength(); k++) {
							if (!prerequisitosNodeList.item(k).getTextContent().trim().equals("")) {
								prerequisitos.add(Integer.valueOf(prerequisitosNodeList.item(k).getTextContent()));
							}
						}
					}
				}
				
				disciplina = buscaDisciplina(disciplinas, codigo);
				
				for (Integer prerequisito : prerequisitos) {
					Disciplina disciplinaPrerequisito = buscaDisciplina(disciplinas, prerequisito);
					disciplinaPrerequisito.acrescentaDependente(disciplina);
					disciplina.acrescentaRequisitos(disciplinaPrerequisito);
				}
			}
		}
		
		return disciplinas;
	}
		
	/**
	 * Procura, em um conjunto, uma disciplina pelo código e retorna o objeto Disciplina correspondente.
	 * @param disciplinas Conjunto de objetos Disciplina.
	 * @param nome Nome da disciplina procurada.
	 * @return Objeto Disciplina procurado.
	 */
	private Disciplina buscaDisciplina(List<Disciplina> disciplinas, long codigo) {
		for(Disciplina disc: disciplinas) {
			if (disc.id == codigo)
				return disc;
		}
		return null;
	}
}