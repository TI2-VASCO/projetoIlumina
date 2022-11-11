package service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import dao.ProjetoDAO;
import model.Projeto;
import spark.Request;
import spark.Response;

public class ProjetoService {
	private ProjetoDAO projetoDAO;
	
	public ProjetoService(){
		try {
			projetoDAO = new ProjetoDAO();
			
		}catch(Exception e) {
			System.out.print(e.getMessage());
		}
	}
	
	public Object add(Request request, Response response) {
		int instituicaoId = Integer.parseInt(request.params(":id"));
	
		String descricao = request.queryParams("descricao");
		System.out.print("descricao: "+descricao);
		byte[] tmp = null;
		
		Projeto projeto = new Projeto(-1, descricao, tmp, instituicaoId);
		
		projetoDAO.insert(projeto);
		response.redirect("http://localhost:6790/frontEnd/perfil.html");
		
		response.status(201);
		
		return null;
}
	
	public Object delete(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		boolean status = projetoDAO.delete(id);
		
		if(status == false) {
			return false;
		}
		
		return true;
		
	}
	
	public Object getAllProjetos(Request request, Response response) {
		List<Projeto> projetos;
		String json = "";
		
		projetos = projetoDAO.getAll();
		
		
		response.header("Content-Type", "application/json");
	    response.header("Content-Encoding", "UTF-8");
	    response.header("Access-Control-Allow-Origin", "*");
	    response.header("Access-Control-Allow-Headers", "*");
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
		     json = ow.writeValueAsString(projetos);
		} catch (JsonProcessingException e) {
		
			e.printStackTrace();
		}
			
		return json;
	}
	
	public Object getAllProjetosByInstituicao(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		List<Projeto> projetos;
		String json = "";
		
		projetos = projetoDAO.getAllByInstituto(id);
		
		
		response.header("Content-Type", "application/json");
	    response.header("Content-Encoding", "UTF-8");
	    response.header("Access-Control-Allow-Origin", "*");
	    response.header("Access-Control-Allow-Headers", "*");
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
		     json = ow.writeValueAsString(projetos);
		} catch (JsonProcessingException e) {
		
			e.printStackTrace();
		}
			
		return json;
	}
	
}
