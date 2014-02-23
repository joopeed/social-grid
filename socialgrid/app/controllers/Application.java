package controllers;

import model.Usuario;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

	public static Sistema SISTEMA = new Sistema();
	
    public static Result aplicacao() {
    	if (session("usuario") == null) {
    		return redirect("/");
    	}
    	  
    	Usuario usuario = SISTEMA.getUsuarioPorEmail(session("usuario"));
    	
        return ok(views.html.index.render(usuario.getPlano().getPeriodos(), usuario.getPlano().getDisciplinasOfertadas()));
    }
      
    public static Result login() {
    	if (request().body().asFormUrlEncoded() != null) {
    		String[] email = request().body().asFormUrlEncoded().get("email");
    		String[] senha = request().body().asFormUrlEncoded().get("senha");
    		
    		if (email != null && senha != null) {
    			if (SISTEMA.autenticarUsuario(email[0], senha[0]) != null) {
    				session("usuario", email[0]);
    			}
    		}
    	}
    	
    	if (session("usuario") != null) {
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
    				SISTEMA.cadastrarUsuario(nome[0], email[0], senha[0]);
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
