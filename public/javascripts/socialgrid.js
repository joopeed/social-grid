/**
 * Funções usadas no site
 */
function validateEmail(email) { 
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
} 

function desalocar(disciplina) {
	var transfere = window.confirm("Ao desalocar esta disciplina você estará desalocando" +
								   "\nas disciplinas dela dependentes");
    if (transfere) {
    	location.href = '/aplicacao/desalocarDisciplina/' + disciplina;
	} else {
    	location.href = '/'
	}
}