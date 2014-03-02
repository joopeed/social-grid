package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class Autenticacao extends Controller {
	public static Sistema SISTEMA = new Sistema();
	public static CadastroUsuario CADASTRO = new CadastroUsuario();
	
	public static Result existeUsuario() {
		System.out.println(request().body().asFormUrlEncoded());
		String email = "";

		if (request().body().asFormUrlEncoded() != null) {
    		String[] emailData = request().body().asFormUrlEncoded().get("email");
    		
    		if (emailData != null) {
    			email = emailData[0];
    		}
		}
    			
		ObjectNode result = Json.newObject();
		
		if (CADASTRO.getUsuarioPorEmail(email) != null) {
			result.put("existeUsuario", true);
		} else {
			result.put("existeUsuario", false);
		}
		
		return ok(result);
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
    	
    	if (Autenticacao.existeUsuarioAutenticado()) {
    		return redirect("/aplicacao");
    	}
    	
    	return ok(views.html.login.render());
    }
    
    public static Result logout() {
    	session().remove("usuario");
    	
    	return redirect("/");
    }
    
    protected static boolean existeUsuarioAutenticado() {
    	boolean existeSessaoValida = false;
    	
    	if (CADASTRO.getUsuarioPorEmail(session("usuario")) != null) {
    		existeSessaoValida = true;
    	} else if (session("usuario") != null) {
    		session().remove("usuario");
    	}
    	
    	return existeSessaoValida;
    }
}
