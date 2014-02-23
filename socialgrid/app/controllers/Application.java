package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

	public static Sistema CONTROLADOR = new Sistema();
	
    public static Result aplicacao() {
        return ok(views.html.index.render());
    }
      
    public static Result login() {
    	if (request().body().asFormUrlEncoded() != null) {
    		
    	}
    	
    	return ok(views.html.login.render());
    }
    
    public static Result cadastro() {
    	if (request().body().asFormUrlEncoded() != null) {
    		String[] nome = request().body().asFormUrlEncoded().get("nome");
    		String[] email = request().body().asFormUrlEncoded().get("email");
    		String[] senha = request().body().asFormUrlEncoded().get("senha");
    		
    		if (nome != null || email != null || senha != null) {
    			try {
    				CONTROLADOR.cadastrarUsuario(nome[0], email[0], senha[0]);
    				flash("cadastro", "Cadastro efetuado com sucesso!");
    				return redirect("/");
    			} catch (CadastroUsuarioException e) {
    				flash("cadastro", "Usuário já cadastrado!");
    			}
    		}
    	}
    	
    	return ok(views.html.cadastro.render());
    }
}
