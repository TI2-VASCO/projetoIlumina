package dao;

import model.Instituicao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InstituicaoDAO extends DAO {
	public InstituicaoDAO() {
		super();
		conectar();
	}
	
	public void finalize() {
		close();
	}
	
	public boolean insert(Instituicao instituicao) throws Exception {
		boolean status = false;
		
		try {
			String sql = "INSERT INTO Instituicao (InstituicaoID, Nome, Email, Senha, CNPJ, Telefone, CEP, Endereco, Imagem, Descricao) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
			
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setInt(1, nextID("instituicao") + 1);
			st.setString(2, instituicao.getNome());
			st.setString(3, instituicao.getEmail());
			st.setString(4, toMD5(instituicao.getSenha()));
			st.setString(5, instituicao.getCNPJ());
			st.setString(6, instituicao.getTelefone());
			st.setString(7, instituicao.getCEP());
			st.setString(8, instituicao.getEndereco());
			st.setBytes(9, instituicao.getImagem());
			st.setString(10, instituicao.getDescricao());
			st.executeUpdate();
			st.close();
			
			status = true;
			
		}catch(SQLException u){
			throw new RuntimeException(u);
		}
		
		return status;
	}
	
	public Instituicao get(int id) {
		Instituicao instituicao = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM Instituicao WHERE InstituicaoID = "+ id;
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()) {
				instituicao = new Instituicao(rs.getInt("InstituicaoID"), rs.getString("Nome"), rs.getString("Email"), rs.getString("Senha"), rs.getString("CNPJ"), rs.getString("Telefone"), rs.getString("CEP"),
						rs.getString("Endereco"), rs.getBytes("Imagem"), rs.getString("Descricao"));
			}
			
			st.close();
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		return instituicao;
	}
	
	public List<Instituicao> getAll(){
		List<Instituicao> instituicoes = new ArrayList<Instituicao>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM instituicao";
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				Instituicao instituto = new Instituicao(rs.getInt("InstituicaoID"), rs.getString("Nome"), rs.getString("Email"), rs.getString("Senha"), rs.getString("CNPJ"), rs.getString("Telefone"), rs.getString("CEP"),
						rs.getString("Endereco"), rs.getBytes("Imagem"), rs.getString("Descricao"));
				instituicoes.add(instituto);
			}
			
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return instituicoes;
	}
	
	public boolean update(Instituicao instituicao) throws Exception {
		boolean status = false;
		try {
			String sql = "UPDATE Instituicao SET Nome = ?, Email = ?, Senha = ?, CNPJ = ?, Telefone = ?, CEP = ?, Endereco = ?, Imagem = ?, Descricao = ? WHERE InstituicaoID = ?"; 
			
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setString(1, instituicao.getNome());
			st.setString(2, instituicao.getEmail());
			st.setString(3, toMD5(instituicao.getSenha()));
			st.setString(4, instituicao.getCNPJ());
			st.setString(5, instituicao.getTelefone());
			st.setString(6, instituicao.getCEP());
			st.setString(7, instituicao.getEndereco());
			st.setBytes(8, instituicao.getImagem());
			st.setString(9, instituicao.getDescricao());
			st.setInt(10, instituicao.getId());
			st.executeUpdate();
			st.close();
			
			status = true;
		}catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		
		return status;
	}
	
	public boolean delete(int id) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM Instituicao WHERE InstituicaoID = " + id;
			st.executeUpdate(sql);
			st.close();
			status = true;
		}catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		
		return status;
	}
	
	public Instituicao loginValidation(String email, String password) {
		Instituicao instituicao = null;
		try {
			
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM Instituicao WHERE Email = '" + email + "' AND Senha = '" + toMD5(password) + "'";
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()) {
				instituicao = new Instituicao(rs.getInt("InstituicaoID"), rs.getString("Nome"), rs.getString("Email"), rs.getString("Senha"), rs.getString("CNPJ"), rs.getString("Telefone"), rs.getString("CEP"),
						rs.getString("Endereco"), rs.getBytes("Imagem"), rs.getString("Descricao"));
			}
			
			st.close();
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return instituicao;
	}
	
}
