package app;
import static spark.Spark.*;

import service.DoacaoService;
import service.ProjetoService;
import service.UsuarioService;

public class Aplicacao {
	private static UsuarioService usuarioService = new UsuarioService();
	private static DoacaoService doacaoService = new DoacaoService();
	private static ProjetoService projetoService = new ProjetoService();
	
	public static void main (String[] args) {
		port(6791);
		
		post("/cadastro", (request, response) -> {
			int userType = Integer.parseInt(request.queryParams("type-user"));
			if(userType == 0) {
				usuarioService.addDoador(request, response);
				
			}else if(userType == 1) {
				usuarioService.addInstituicao(request, response);
			}
			response.redirect("http://localhost:6790/frontEnd/login.html");
			return "Usuário Cadastrado";
		});
		
		get("/login", (request, response) -> {
			Object status = null;
			int userType = Integer.parseInt(request.queryParams("type-user"));
			if(userType == 0) {
				status = usuarioService.loginDoador(request, response) ;
				
			}else if(userType == 1){
				status = usuarioService.loginInstituicao(request, response);
			}
			
			if(status.toString() == "true") {
				response.redirect("http://localhost:6790/frontEnd/index.html");
			}else {
				response.redirect("http://localhost:6790/frontEnd/login.html");
			}
			
			return null;
		});
		
		get("/user/:token", (request, response) -> {
			String token = request.params(":token");
						
			String[] tokenPart = token.split("_");
			
			int tokenType = Integer.parseInt(tokenPart[1]);
			
			if(tokenType == 0) {
				return usuarioService.getDoador(request, response);
				
			}else if(tokenType == 1) {
				return usuarioService.getInstituto(request, response);
			}
			
			return null;
			
		});
		
		get("/user/doacoes/:token", (request, response) ->{
			return doacaoService.getDoacaoByUser(request, response);
		});
		
		post("/update/:token", (request, response) ->{
			String token = request.params(":token");
			
			String[] tokenPart = token.split("_");
			
			int tokenType = Integer.parseInt(tokenPart[1]);
			
			if(tokenType == 0) {
				usuarioService.updateDoador(request, response);
			}else if(tokenType == 1) {
				usuarioService.updateInstituicao(request, response);
			}
			
			response.redirect("http://localhost:6790/frontEnd/perfil.html");
			return null;
		});
		
		get("/delete/:token", (request, response) ->{
			String token = request.params(":token");
			String[] tokenPart = token.split("_");
			int tokenType = Integer.parseInt(tokenPart[1]);
			
			if(tokenType == 0) {
				usuarioService.deleteDoador(request, response);
			}else if(tokenType == 1) {
				usuarioService.deleteInstituicao(request, response);
			}
			
			response.redirect("http://localhost:6790/frontEnd/index.html");
			return null;
			
		});
		
		get("/doacao/cadastro/:token", (request, response) ->{
			return doacaoService.add(request, response);
		});
		
		get("/instituicao/getAll", (request, response) -> usuarioService.getAllInstituicao(request, response));
		
		get("/instituicao/:id", (request, response) -> usuarioService.getInstitutoById(request, response));
		
		get("/projetos/getAll", (request, response) -> projetoService.getAllProjetos(request, response));
		
		get("/projetos/instituicao/:id", (request, response) -> projetoService.getAllProjetosByInstituicao(request, response));
		
		get("projeto/add/:id", (request, response) -> projetoService.add(request, response));
	}
}
