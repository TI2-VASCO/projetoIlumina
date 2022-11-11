package dao;

import model.Doacao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoacaoDAO extends DAO {
	public DoacaoDAO() {
		super();
		conectar();
	}
	
	public void finalize() {
		close();
	}
	
	public boolean insert(Doacao doacao) {
		boolean status = false;
		
		try {
			String sql = "INSERT INTO Doacao (DoacaoID, Data, Valor, DoadorID) VALUES (?, ?, ?, ?)";
			PreparedStatement st = conexao.prepareStatement(sql);
		
			st.setInt(1, nextID("Doacao")+1);
			st.setTimestamp(2, doacao.getTimestamp());
			st.setDouble(3, doacao.getValor());
			st.setInt(4, doacao.getDoadorID());
			st.executeUpdate();
			st.close();
			
			status = true;
		}catch(Exception e) {
			System.err.print(e.getMessage());
		}
		
		return status;
	}
	
	public Doacao get(int id) {
		Doacao doacao = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM Doacao WHERE DoacaoID =" + id;
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()) {
				doacao = new Doacao(rs.getInt("DoacaoID"), rs.getTimestamp("Data"), rs.getDouble("Valor"), rs.getInt("DoadorID"));
			}
			st.close();
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return doacao;
	}
	
	public List<Doacao> getByUser(int id) {
		List<Doacao> doacoesUser = new ArrayList<Doacao>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM Doacao WHERE DoadorID =" + id;
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				Doacao doacao = new Doacao(rs.getInt("DoacaoID"), rs.getTimestamp("Data"), rs.getDouble("Valor"), rs.getInt("DoadorID"));
				doacoesUser.add(doacao);
			}
			st.close();
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return doacoesUser;
	}
	
	public boolean update(Doacao doacao) {
		boolean status = false;
		
		try {
			String sql = "UPDATE Doacao SET Data = ?, Valor = ?, DoadorID = ? WHERE DoacaoID = ?";
			PreparedStatement st = conexao.prepareStatement(sql);
	
			st.setTimestamp(1, doacao.getTimestamp());
			st.setDouble(2, doacao.getValor());
			st.setInt(3, doacao.getDoadorID());
			st.setInt(4, doacao.getID());
			
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
			String sql = "DELETE FROM Doacao WHERE DoacaoID = "+id;
			st.executeQuery(sql);
			st.close();
			status = true;
			
		}catch(Exception e) {
			System.err.print(e.getMessage());
		}
		
		return status;
	}
	
}
