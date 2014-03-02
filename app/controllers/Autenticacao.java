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
}
