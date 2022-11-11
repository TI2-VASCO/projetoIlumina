package service;

import dao.DoacaoDAO;
import model.Doacao;
import spark.Request;
import spark.Response;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper; 
import com.fasterxml.jackson.databind.ObjectWriter; 

public class DoacaoService {
	private DoacaoDAO doacaoDao;
	
	public DoacaoService(){
		try {
			doacaoDao = new DoacaoDAO();
			
		}catch(Exception e) {
			System.out.print(e.getMessage());
		}
	}
	
	public Object add(Request request, Response response) {
		String token[] = request.params(":token").split("_");
		int doadorId = Integer.parseInt(token[0]);
		System.out.print(doadorId);
		
		String data = request.queryParams("data");
		
		double valor = Double.parseDouble(request.queryParams("valor"));
	
		
		Doacao doacao = new Doacao(-1, data, valor, doadorId);
		response.header("Content-Type", "application/json");	
	    response.header("Content-Encoding", "UTF-8");
	    response.header("Access-Control-Allow-Origin", "*");
	    response.header("Access-Control-Allow-Headers", "*");
		
		if(doacaoDao.insert(doacao)) {
			return "{\"status\":\"true\"}";
		}
		
		return "{\"status\":\"false\"}";
	} 
	
	public Object getDoacaoByUser(Request request, Response response) {
		String token[] = request.params(":token").split("_");
		int id = Integer.parseInt(token[0]);
		
		List<Doacao> doacao = doacaoDao.getByUser(id);
		String json = "";
		
		if(doacao != null) {
			response.header("Content-Type", "application/json");
    	    response.header("Content-Encoding", "UTF-8");
    	    response.header("Access-Control-Allow-Origin", "*");
    	    response.header("Access-Control-Allow-Headers", "*");
		
    	 
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
			try {
				json += ow.writeValueAsString(doacao);
			} catch (JsonProcessingException e) {
		
				e.printStackTrace();
			}
		}
		
		
		return json;
	}
}
