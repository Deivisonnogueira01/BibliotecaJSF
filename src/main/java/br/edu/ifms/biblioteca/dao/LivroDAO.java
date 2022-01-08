package br.edu.ifms.biblioteca.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import br.edu.ifms.biblioteca.bd.ConexaoMySQL;
import br.edu.ifms.biblioteca.model.Livro;



public class LivroDAO implements Serializable {
	
	private Connection conn = null;
	
	public LivroDAO() {
		// iniciar uma conexao com BD	
		ConexaoMySQL conexao = new ConexaoMySQL();
		conn = conexao.getConnection();
	}	
	//-------------------------------------------------------------------------
	// 									CRUD
	//-------------------------------------------------------------------------
	public void salvar(Livro livro){ // inserir um novo livro
		String sql = "INSERT INTO tb_livro (nome, autor, editora, codLivro, ano) VALUES (?, ?, ?, ?, ?)"; 
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, livro.getNome());
			ps.setString(2, livro.getAutor());
			ps.setString(3, livro.getEditora());
			ps.setString(4, livro.getCodigoLivro());
			ps.setInt(5, livro.getAno());			
			int resultado = ps.executeUpdate();
			if (resultado > 0)
				System.out.println("O livro foi inserido");
			else 
				System.out.println("Problema ao inserir o livro");			
		} catch (SQLException e) {			
			e.printStackTrace();
		} 
	}
	//-------------------------------------------------------------------------
	public List<Livro> selecionarTodos() { // buscar todos os livros no BD
		List<Livro> livros = new ArrayList<Livro>();
		
		String sql = "SELECT * FROM tb_livro";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			// object pascal = 0			
			// ex: 12 reg
			while(rs.next()) {
				Livro livro = new Livro();
				livro.setId(rs.getInt(1));
				livro.setNome(rs.getString(2));
				livro.setAutor(rs.getString(3));
				livro.setEditora(rs.getString(4));
				livro.setCodigoLivro(rs.getString(5));
				livro.setAno(rs.getInt(6));				
				livros.add(livro);
			}
			return livros;
			
		} catch (SQLException e) {			
			System.out.println("Erro ao consultar os livros no BD");
			e.printStackTrace();
		}
		
		return null;
		
	}	
	//-------------------------------------------------------------------------
	public void deletar(int id) { // id do livro que queremos deletar
		String sql = "DELETE FROM tb_livro WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int rowDelete = ps.executeUpdate();
			if(rowDelete > 0) {
				System.out.println("Livro deletado!!!");
			} else {
				System.out.println("Livro não deletado!!!");
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
	}	
	//-------------------------------------------------------------------------
	public void atualizar(Livro livro) { // atualizar um livro
		String sql = "UPDATE tb_livro SET nome = ?, autor = ?, editora = ?, codLivro = ?, ano = ? WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, livro.getNome());
			ps.setString(2, livro.getAutor());
			ps.setString(3, livro.getEditora());
			ps.setString(4, livro.getCodigoLivro());
			ps.setInt(5, livro.getAno());
			ps.setInt(6, livro.getId());
			
			int rowUpdate = ps.executeUpdate();
			
			if(rowUpdate > 0) {
				System.out.println("Livro atualizado!");
			} else {
				System.out.println("Livro não atualizado!");
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}	
	//-------------------------------------------------------------------------
	
	
	
}
