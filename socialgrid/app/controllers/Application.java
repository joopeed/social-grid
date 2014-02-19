package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(views.html.index.render("Your new application is ready."));
    }
    
    public static Result cadastroLogin() {
    	return ok(views.html.cadastroLogin.render("Tela de login"));
    }

}
