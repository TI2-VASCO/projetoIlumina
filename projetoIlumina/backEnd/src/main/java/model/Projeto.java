package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Projeto {
	protected int ID;
	protected String descricao;
	protected byte[] imagem;
	protected int categoriaID;
	
	public Projeto() {
		this(-1, "", null, -1, 0);
	}
	
	public Projeto(int id, String descricao, File imagem, int instituicaoId, int tmp) {
		this.ID = id;
		this.descricao = descricao;
		if(imagem != null) {
			byte[] b = null;
			try {
				b = Files.readAllBytes(imagem.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.imagem = b;
			}
		this.categoriaID = instituicaoId;
	}
	
	public Projeto(int id, String descricao, byte[] imagem, int instituicaoId) {
		this.ID = id;
		this.descricao = descricao;
		this.imagem = imagem;
		this.categoriaID = instituicaoId;
	}
	
	//GETTERS
	public int getID() {
		return this.ID;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public byte[] getImagem() {
		return this.imagem;
	}
	
	public int getInstituicaoId() {
		return this.categoriaID;
	}
	
	//SETTERS
	public void setID(int id) {
		this.ID = id;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
	
	public void setInstituicaoID(int id) {
		this.categoriaID = id;
	}
	
	
}
