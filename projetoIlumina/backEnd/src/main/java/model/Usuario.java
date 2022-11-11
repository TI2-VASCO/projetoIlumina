package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Usuario {
	protected int id;
	protected String nome;
	protected String email;
	protected String senha;
	protected String telefone;
	protected String cep;
	protected String endereco;
	protected byte[] imagem;
	
	public Usuario() {
		this(-1, "", "", "", "", "", "", null, 0);
	}
	
	public Usuario(int id, String nome, String email, String senha, String telefone, String cep, String endereco, File imagem, int tmp) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.telefone = telefone;
		this.cep = cep;
		this.endereco = endereco;
		
		if(imagem != null) {
		byte[] b = null;
		try {
			b = Files.readAllBytes(imagem.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.imagem = b;
		}
	}
	
	public Usuario(int id, String nome, String email, String senha, String telefone, String cep, String endereco, byte[] imagem) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.telefone = telefone;
		this.cep = cep;
		this.endereco = endereco;
		this.imagem = imagem;
	}
	
	//GETTERS
	public int getId() {
		return this.id;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getSenha() {
		return this.senha;
	}
	
	public String getTelefone() {
		return this.telefone;
	}
	
	public String getCEP() {
		return this.cep;
	}
	
	public String getEndereco() {
		return this.endereco;
	}
	
	public byte[] getImagem() {
		return this.imagem;
	}
	
	//SETTERS
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public void setCEP(String cep) {
		this.cep = cep;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public void setImagem(File imagem) {
		byte[] b = null;
		try {
			b = Files.readAllBytes(imagem.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.imagem = b;
	}
	
}
