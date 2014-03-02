package controllers;

import model.Usuario;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

	public static Sistema SISTEMA = new Sistema();
	public static CadastroUsuario CADASTRO = new CadastroUsuario();
	
    public static Result aplicacao() {
    	if (!existeSessaoValida()) {
    		return redirect("/");
    	}
    	  
    	Usuario usuario = CADASTRO.getUsuarioPorEmail(session("usuario"));
    	
        return ok(views.html.index.render(usuario.getPlano().getPeriodos(), usuario.getPlano().getDisciplinasOfertadas()));
    }
      
    public static Result login() {
    	if (request().body().asFormUrlEncoded() != null) {
    		String[] email = request().body().asFormUrlEncoded().get("email");
    		String[] senha = request().body().asFormUrlEncoded().get("senha");
    		
    		if (email != null && senha != null) {
    			if (CADASTRO.autenticarUsuario(email[0], senha[0]) != null) {
    				session("usuario", email[0]);
    			} else {
    				flash("erro", "Nome de usuário ou senha inválidos!");
    			}
    		}
    	}
    	
    	if (existeSessaoValida()) {
    		return redirect("/aplicacao");
    	}
    	
    	return ok(views.html.login.render());
    }
    
    public static Result logout() {
    	session().remove("usuario");
    	
    	return redirect("/");
    }
    
    public static Result cadastro() {
    	if (request().body().asFormUrlEncoded() != null) {
    		String[] nome = request().body().asFormUrlEncoded().get("nome");
    		String[] email = request().body().asFormUrlEncoded().get("email");
    		String[] senha = request().body().asFormUrlEncoded().get("senha");
    		
    		if (nome != null && email != null && senha != null) {
    			try {
    				CADASTRO.cadastrarUsuario(nome[0], email[0], senha[0]);
    				flash("cadastro", "Cadastro efetuado com sucesso!");
    				return redirect("/");
    			} catch (CadastroUsuarioException e) {
    				flash("cadastro", "Usuário já cadastrado!");
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
    
    public static boolean existeSessaoValida() {
    	boolean existeSessaoValida = false;
    	
    	if (CADASTRO.getUsuarioPorEmail(session("usuario")) != null) {
    		existeSessaoValida = true;
    	} else if (session("usuario") != null) {
    		session().remove("usuario");
    	}
    	
    	return existeSessaoValida;
    }
}
