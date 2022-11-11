package dao;

import model.Doador; 

import java.sql.*;

public class DoadorDAO extends DAO {
	public DoadorDAO() {
		super();
		conectar();
	}
	
	public void finalize() {
		close();
	}
	
	public boolean insert(Doador doador) throws Exception {
		boolean status = false;
		
		try {
			String sql = "INSERT INTO Doador (DoadorID, Nome, Email, Senha, CPF, Telefone, CEP, Endereco, Imagem) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
			
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setInt(1, nextID("Doador") + 1);
			st.setString(2, doador.getNome());
			st.setString(3, doador.getEmail());
			st.setString(4, toMD5(doador.getSenha()));
			st.setString(5, doador.getCPF());
			st.setString(6, doador.getTelefone());
			st.setString(7, doador.getCEP());
			st.setString(8, doador.getEndereco());		
			st.setBytes(9, doador.getImagem());
			
			
			st.executeUpdate();
			st.close();
			
			status = true;
			
		}catch(SQLException u){
			throw new RuntimeException(u);
		};
		
		return status;
	}
	
	public Doador get(int id) {
		Doador doador = null;
		try {
			
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM Doador WHERE DoadorID =" + id;
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()) {
				doador = new Doador(rs.getInt("DoadorID"), rs.getString("Nome"), rs.getString("Email"), rs.getString("Senha"), rs.getString("CPF"), rs.getString("Telefone"), rs.getString("CEP"),
						rs.getString("Endereco"), rs.getBytes("Imagem"));
			}
			st.close();
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return doador;
	}
	
	public boolean update(Doador doador) throws Exception {
		boolean status = false;
		try {
			String sql = "UPDATE Doador SET Nome = ?, Email = ?, Senha = ?, CPF = ?, Telefone = ?, CEP = ?, Endereco = ?, Imagem = ? WHERE DoadorID = ?";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setString(1, doador.getNome());
			st.setString(2, doador.getEmail());
			st.setString(3, toMD5(doador.getSenha()));
			st.setString(4, doador.getCPF());
			st.setString(5, doador.getTelefone());
			st.setString(6, doador.getCEP());
			st.setString(7, doador.getEndereco());
			st.setBytes(8, doador.getImagem());
			st.setInt(9, doador.getId());
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
			String sql = "DELETE FROM Doador WHERE DoadorID =" + id;
			st.executeUpdate(sql);
			st.close();
			status = true;
		}catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		
		return status;
	}
	
	public Doador loginValidation(String email, String password) {
		Doador doador = null;
		try {
			
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM Doador WHERE Email = '" + email + "' AND Senha = '" + toMD5(password) + "'";
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()) {
				doador = new Doador(rs.getInt("DoadorID"), rs.getString("Nome"), rs.getString("Email"), rs.getString("Senha"), rs.getString("CPF"), rs.getString("Telefone"), rs.getString("CEP"),
						rs.getString("Endereco"), rs.getBytes("Imagem"));
			}
			st.close();
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return doador;
	}
	
}
