package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Instituicao extends Usuario{
	protected String CNPJ;
	protected String descricao;
	
	public Instituicao() {
		this(-1, "", "", "", "", "", "", "", null, "", 0);
	}
	
	public Instituicao(int id, String nome, String email, String senha, String cnpj, String telefone, String cep, String endereco, File imagem, String descricao, int tmp){
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.CNPJ = cnpj;
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
		this.descricao = descricao;
	} 
	
	public Instituicao(int id, String nome, String email, String senha, String cnpj, String telefone, String cep, String endereco, byte[] imagem, String descricao) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.CNPJ = cnpj;
		this.telefone = telefone;
		this.cep = cep;
		this.endereco = endereco;
		this.imagem = imagem;
		this.descricao = descricao;
	}
	
	//GETTERS	
	public String getCNPJ() {
		return this.CNPJ;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	//SETTERS	
	public void setCNPJ(String cnpj) {
		this.CNPJ = cnpj;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
