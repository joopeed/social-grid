package controllers;

import model.Disciplina;
import model.Usuario;
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
    	
        return ok(views.html.index.render(usuario.getPlano().getPeriodos(), SISTEMA.getDisciplinasOfertadas(usuario)));
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
    
    public static Result verDisciplina() throws InterruptedException {
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
}
