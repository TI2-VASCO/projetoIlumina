package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Doador extends Usuario{
	protected String CPF;
	
	public Doador() {
		this(-1, "", "", "", "", "", "", "", null, 0);
	}
	
	public Doador(int id, String nome, String email, String senha, String cpf, String telefone, String cep, String endereco, File imagem, int tmp) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.CPF = cpf;
		this.telefone = telefone;
		this.cep = cep;
		this.endereco = endereco;
		
		byte[] b = null;
		try {
			b = Files.readAllBytes(imagem.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.imagem = b;
	}
	
	public Doador(int id, String nome, String email, String senha, String cpf, String telefone, String cep, String endereco, byte[] imagem) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.CPF = cpf;
		this.telefone = telefone;
		this.cep = cep;
		this.endereco = endereco;
		this.imagem = imagem;
	}
	
	//GETTERS
	public String getCPF() {
		return this.CPF;
	}
	
	//SETTERS
	public void setCPF(String cpf) {
		this.CPF = cpf;
	}
}
