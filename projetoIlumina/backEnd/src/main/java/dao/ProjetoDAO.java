package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Projeto;

public class ProjetoDAO extends DAO {
	public ProjetoDAO() {
		super();
		conectar();
	}
	
	public void finalize() {
		close();
	}
	
	public boolean insert(Projeto projeto) {
		boolean status = false;
		
		try {
			String sql = "INSERT INTO Projeto (ProjetoID, Descricao, Imagem, InstituicaoID) "
					+ "VALUES (?, ?, ?, ?)";
			
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setInt(1, nextID("Projeto") + 1);
			st.setString(2, projeto.getDescricao());
			st.setBytes(3, projeto.getImagem());
			st.setInt(4, projeto.getInstituicaoId());
			st.executeUpdate();
			st.close();
			
			status = true;
		}catch(SQLException u){
			throw new RuntimeException(u);
		};
		
		
		return status;
	}
	
	public Projeto get(int id) {
		Projeto projeto = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM Projeto WHERE ProjetoID = "+ id;
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()) {
				projeto = new Projeto(rs.getInt("ProjetoID"), rs.getString("Descricao"), rs.getBytes("Imagem"), rs.getInt("InstituicaoID"));
			}
			
			st.close();
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		return projeto;
	}
	
	public List<Projeto> getAll() {
		List<Projeto> projetos = new ArrayList<Projeto>();
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM projeto";
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				Projeto projeto = new Projeto(rs.getInt("ProjetoID"), rs.getString("Descricao"), rs.getBytes("Imagem"), rs.getInt("InstituicaoID"));
				projetos.add(projeto);
			}
			
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		
		return projetos;
	}
	
	public List<Projeto> getAllByInstituto(int id) {
		List<Projeto> projetos = new ArrayList<Projeto>();
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM projeto WHERE instituicaoid = "+id ;
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				Projeto projeto = new Projeto(rs.getInt("ProjetoID"), rs.getString("Descricao"), rs.getBytes("Imagem"), rs.getInt("InstituicaoID"));
				projetos.add(projeto);
			}
			
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return projetos;
	}
	
	public boolean update(Projeto projeto) {
		boolean status = false;
		
		try {
			String sql = "UPDATE Projeto SET Descricao = ?, Imagem = ?, InstituicaoID = ?";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setString(1, projeto.getDescricao());
			st.setBytes(2, projeto.getImagem());
			st.setInt(3, projeto.getInstituicaoId());
			st.setInt(4, projeto.getID());
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
		
		try{
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM Projeto WHERE ProjetoID = " + id;
			st.executeUpdate(sql);
			st.close();
			status = true;
			
		}catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		
		return status;
	}
}
