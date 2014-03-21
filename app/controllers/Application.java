package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;

import model.Disciplina;
import model.Usuario;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

	public static Sistema SISTEMA = new Sistema();
	public static CadastroUsuario CADASTRO = new CadastroUsuario();
	
    public static Result aplicacao() {
    	if (!Autenticacao.existeUsuarioAutenticado()) {
    		return redirect("/");
    	}
    	  
    	Usuario usuario = CADASTRO.getUsuarioPorEmail(session("usuario"));
    	
        return ok(views.html.index.render(usuario.getPlano(), usuario.getPlano().getPeriodos(), usuario.getPlano().getDisciplinasOfertadas()));
    }
    
    public static Result cadastro() {
    	if (request().body().asFormUrlEncoded() != null) {
    		String[] nome = request().body().asFormUrlEncoded().get("nome");
    		String[] email = request().body().asFormUrlEncoded().get("email");
    		String[] senha = request().body().asFormUrlEncoded().get("senha");
    		
    		if (nome != null && email != null && senha != null) {
    			try {
    				CADASTRO.cadastrarUsuario(nome[0], email[0], senha[0]);
    				flash("success", "Cadastro efetuado com sucesso!");
    				return redirect("/");
    			} catch (CadastroUsuarioException e) {
    				flash("erro", "Usuário já cadastrado!");
    			}
    		}
    	}
    	
    	return ok(views.html.cadastro.render());
    }

    public static Result desalocarDisciplina(String nomeDisciplina) {
    	SISTEMA.desalocarDisciplina(CADASTRO.getUsuarioPorEmail(session("usuario")), nomeDisciplina);
    	return redirect("/aplicacao");
    }
    
    public static Result alocarDisciplina(String nomeDisciplina, int idxPeriodo) {
    	SISTEMA.alocarDisciplina(CADASTRO.getUsuarioPorEmail(session("usuario")), nomeDisciplina, idxPeriodo - 1);
    	return redirect("/aplicacao");
    }
    
    public static Result buscaUsuario() {
    	String query = request().queryString().get("query")[0];
    	return ok(views.html.search.render(query, CADASTRO.getUsuarioPorNome(query)));
    }
    
    public static Result planejaProximoPeriodoFacil() {
    	SISTEMA.planejaProximoPeriodoFacil(CADASTRO.getUsuarioPorEmail(session("usuario")));
    	return redirect("/aplicacao");
    }
    
    public static Result planejaProximoPeriodoDificil() {
    	SISTEMA.planejaProximoPeriodoDificil(CADASTRO.getUsuarioPorEmail(session("usuario")));
    	return redirect("/aplicacao");
    }
    
    public static Result avancaPeriodo() {
    	SISTEMA.avancaPeriodoAtual(CADASTRO.getUsuarioPorEmail(session("usuario")));
    	return redirect("/aplicacao");
    }
    
    public static Result reduzPeriodo() {
    	SISTEMA.reduzPeriodoAtual(CADASTRO.getUsuarioPorEmail(session("usuario")));
    	return redirect("/aplicacao");
    }
    
    public static Result getPerfil(String email) {
    	Usuario usuario = CADASTRO.getUsuarioPorEmail(email);
    	if(usuario != null)
    		return ok(views.html.perfil.render(usuario.getNome(), usuario.getPlano(), usuario.getPlano().getPeriodos(), usuario.getPlano().getDisciplinasOfertadas()));
    	else
    		return ok();
    }
    
    public static Result verDisciplina() {
    	if (!Autenticacao.existeUsuarioAutenticado()) {
    		return badRequest();
    	}
    	
    	Long codigo = null;
    	Disciplina disciplina = null;
		
    	if (request().body().asFormUrlEncoded() != null) {
    		String[] disciplinaData = request().body().asFormUrlEncoded().get("codigo");
    		
    		if (disciplinaData != null) {
    			codigo = Long.parseLong(disciplinaData[0]);
    		}
		}
    	
    	disciplina = SISTEMA.getDisciplinaPorCodigo(codigo);
    	
    	if (disciplina != null) {
    		return ok(views.html.disciplina.render(disciplina));
    	} else {
    		return badRequest();
    	}
    }
    
    public static Result adicionarDica() {
    	if (!Autenticacao.existeUsuarioAutenticado()) {
    		return badRequest();
    	}

    	ObjectNode result = Json.newObject();
    	
    	result.put("dica_adicionada", false);
    	
    	if (request().body().asFormUrlEncoded() != null) {
    		Usuario usuario = CADASTRO.getUsuarioPorEmail(session("usuario"));

    		String[] texto = request().body().asFormUrlEncoded().get("texto");
    		String[] codigo = request().body().asFormUrlEncoded().get("codigo");
    		
    		if (texto != null && codigo != null) {
   				SISTEMA.adicionarDica(Long.parseLong(codigo[0]), usuario, texto[0]);
   				result.put("dica_adicionada", true);
    		}
    	}

    	return ok(result);
    }
    
    public static Result carregarDicas(long codigo) {
    	if (!Autenticacao.existeUsuarioAutenticado()) {
    		return badRequest();
    	}

    	Disciplina disciplina = SISTEMA.getDisciplinaPorCodigo(codigo);
    	
    	return ok(views.html.dicas.render(disciplina.getDicas()));
    }
}
