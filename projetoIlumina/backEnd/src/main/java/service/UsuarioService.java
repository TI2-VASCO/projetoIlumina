package service;

import dao.DoadorDAO;
import dao.InstituicaoDAO;
import model.Doador;
import model.Instituicao;
import spark.Request;
import spark.Response;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper; 
import com.fasterxml.jackson.databind.ObjectWriter; 


public class UsuarioService {
	private InstituicaoDAO instituicaoDAO;
	private DoadorDAO doadorDAO;
	
	public UsuarioService() {
		try {
			instituicaoDAO = new InstituicaoDAO();
			doadorDAO = new DoadorDAO();
			
		}catch(Exception e) {
			System.out.print(e.getMessage());
		}
	}
	
	public Object addDoador(Request request, Response response) throws Exception {
			String nome = request.queryParams("name");
			String email = request.queryParams("email");
			String senha = request.queryParams("senha");
			String cpf = request.queryParams("CPF");
			String telefone = request.queryParams("telefone");
			String cep = request.queryParams("CEP");
			String endereco = request.queryParams("endereco");
			String numeroEnd = request.queryParams("numero");
			String complemento = request.queryParams("complemento");
			
			String new_endereco = endereco + ", "+numeroEnd+", "+complemento;
			
			
			byte[] tmp = null;
			
			Doador doador = new Doador(-1, nome, email, senha, cpf, telefone, cep, new_endereco, tmp);
			
			doadorDAO.insert(doador);
			
			response.status(201);
			
			return null;
	}
	
	public Object addInstituicao(Request request, Response response) throws Exception {
		String nome = request.queryParams("nameInst");
		String email = request.queryParams("emailInst");
		String senha = request.queryParams("senhaInst");
		String cnpj = request.queryParams("CNPJInst");
		String telefone = request.queryParams("telefoneInst");
		String cep = request.queryParams("CEPInst");
		String endereco = request.queryParams("enderecoInst");
		String numeroEnd = request.queryParams("numeroInst");
		String complemento = request.queryParams("complementoINnst");
		
		System.out.print(nome+" "+email+" "+senha+" "+cnpj+" "+telefone+" "+cep+" "+endereco+" "+numeroEnd+" "+complemento);
		
		String new_endereco = endereco + ", "+numeroEnd+", "+complemento;
		
		byte[] tmp = null;
		
		Instituicao instituicao = new Instituicao(0, nome, email, senha, cnpj, telefone, cep, new_endereco, tmp, "");
		
		instituicaoDAO.insert(instituicao);
		
		response.status(201);
		
		return null;
	}
	
	public Object getDoador(Request request, Response response) {
		String token[] = request.params(":token").split("_");
		int id = Integer.parseInt(token[0]);

		Doador doador = doadorDAO.get(id);
		String json = "";
		if(doador != null) {
			response.header("Content-Type", "application/json");
    	    response.header("Content-Encoding", "UTF-8");
    	    response.header("Access-Control-Allow-Origin", "*");
    	    response.header("Access-Control-Allow-Headers", "*");
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

		try {
		     json = ow.writeValueAsString(doador);
		} catch (JsonProcessingException e) {
		
			e.printStackTrace();
		}
		
		}
		
		
		return json;
	}
	
	public Object getInstituto(Request request, Response response) {
		int id = Integer.parseInt(request.params(":token").split("_")[0]);
		
		Instituicao instituicao = instituicaoDAO.get(id);
		String json = "";
		if(instituicao != null) {
			response.header("Content-Type", "application/json");
    	    response.header("Content-Encoding", "UTF-8");
    	    response.header("Access-Control-Allow-Origin", "*");
    	    response.header("Access-Control-Allow-Headers", "*");
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

		try {
		     json = ow.writeValueAsString(instituicao);
		} catch (JsonProcessingException e) {
		
			e.printStackTrace();
		}
		
		}
		
		return json;
	}
	
	public Object getInstitutoById(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		
		Instituicao instituicao = instituicaoDAO.get(id);
		String json = "";
		if(instituicao != null) {
			response.header("Content-Type", "application/json");
    	    response.header("Content-Encoding", "UTF-8");
    	    response.header("Access-Control-Allow-Origin", "*");
    	    response.header("Access-Control-Allow-Headers", "*");
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

		try {
		     json = ow.writeValueAsString(instituicao);
		} catch (JsonProcessingException e) {
		
			e.printStackTrace();
		}
		
		}
		
		return json;
	}
	
	
	public Object updateDoador(Request request, Response response) throws Exception {
		int id = Integer.parseInt(request.params(":token").split("_")[0]);
		String nome = request.queryParams("name");
		String email = request.queryParams("email");
		String senha = request.queryParams("senha");
		String cpf = request.queryParams("CPF");
		String telefone = request.queryParams("telefone");
		String cep = request.queryParams("CEP");
		String endereco = request.queryParams("endereco");
		
		byte[] tmp = null;
		
		Doador doador = new Doador(id, nome, email, senha, cpf, telefone, cep, endereco, tmp);
		
		doadorDAO.update(doador);
		
		response.status(201);
		
		return null;
	}
	
	public Object updateInstituicao(Request request, Response response) throws Exception {
		Instituicao instituicao = null;
		
		int id = Integer.parseInt(request.params(":token").split("_")[0]);
		String nome = request.queryParams("name");
		String email = request.queryParams("email");
		String senha = request.queryParams("senha");
		String cnpj = request.queryParams("CNPJ");
		String telefone = request.queryParams("telefone");
		String cep = request.queryParams("CEP");
		String endereco = request.queryParams("endereco");
		String descricao = request.queryParams("descricao");
		
		byte[] tmp = null;
		
		instituicao = new Instituicao(id, nome, email, senha, cnpj, telefone, cep, endereco, tmp, descricao);
		
		instituicaoDAO.update(instituicao);
		
		response.status(201);
		
		return null;
	}
	
	public Object deleteDoador(Request request, Response response) {
		String token = request.params(":token");
		int id = Integer.parseInt(token.split("_")[0]);
		boolean status = doadorDAO.delete(id);
		
		if(status == false) {
			return false;
		}
	
		
		return true;
	}
	
	public Object deleteInstituicao(Request request, Response response) {
		String token = request.params(":token");
		int id = Integer.parseInt(token.split("_")[0]);
		boolean status = instituicaoDAO.delete(id);
		
		if(status == false) {
			return false;
		}
		
		return true;
	}
	
	public Object loginDoador(Request request, Response response) {
		Doador doador = null;
		
		String email = request.queryParams("email");
		String password = request.queryParams("password");
		
		
		doador = doadorDAO.loginValidation(email, password);

		if(doador == null) {
			return false;
		}
		
		String doadorTmp[] = doador.getNome().split(" ");
		System.out.print(doadorTmp[0]);
		
		String tokenID = doador.getId()+"_0_"+doadorTmp[0];
	
		
		response.cookie("token", tokenID);
		response.cookie("isLogged", "true");
		
		return true;
	}
	
	public Object loginInstituicao(Request request, Response response) {
		Instituicao instituicao = null;
		
		String email = request.queryParams("email");
		String password = request.queryParams("password");
		
		instituicao = instituicaoDAO.loginValidation(email, password);

		if(instituicao == null) {
			return false;
		}
		
		String instituicaoTmp[] = instituicao.getNome().split(" ");
		String tokenID = instituicao.getId()+"_1_"+instituicaoTmp[0];
	
		
		response.cookie("token", tokenID);
		response.cookie("isLogged", "true");
		
		return true;
	}
	
	public Object getAllInstituicao(Request request, Response response) {
		List<Instituicao> instituicoes;
		String json = "";
		
		instituicoes = instituicaoDAO.getAll();
		
		
		response.header("Content-Type", "application/json");
	    response.header("Content-Encoding", "UTF-8");
	    response.header("Access-Control-Allow-Origin", "*");
	    response.header("Access-Control-Allow-Headers", "*");
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
		     json = ow.writeValueAsString(instituicoes);
		} catch (JsonProcessingException e) {
		
			e.printStackTrace();
		}
			
		return json;
	}
}
	
