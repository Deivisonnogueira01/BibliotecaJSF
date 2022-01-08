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
import br.edu.ifms.biblioteca.model.Acervo;
import br.edu.ifms.biblioteca.model.Emprestimo;
import br.edu.ifms.biblioteca.model.Livro;

public class AcervoDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Connection conn = null;

	public AcervoDAO() {
		
		// iniciar uma conexao com BD
		ConexaoMySQL conexao = new ConexaoMySQL();
		conn = conexao.getConnection();
	}

	// -------------------------------------------------------------------------
	// CRUD // int id livro int quantidade
	// -------------------------------------------------------------------------
	public void salvar(Acervo acervo) { // inserir um novo Acervo
		String sql = "INSERT INTO tb_acervo (id_livro, quantidade,) VALUES (?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, acervo.getLivro().getId()); // primeir livro depois o ID
			ps.setInt(2, acervo.getQuantidade());

			int resultado = ps.executeUpdate();
			if (resultado > 0)
				System.out.println("O Acervo  foi inserido");
			else
				System.out.println("Problema ao Configurar  o acervo");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// -------------------------------------------------------------------------
	public List<Acervo> selecionarTodos() { // buscar todos os acervos no BD
		List<Acervo> acervos = new ArrayList<Acervo>();

		String sql = "SELECT * FROM tb_Acervo";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Acervo acervo = new Acervo();
				int idLivro = rs.getInt(1);
				Livro l = localizarLivro(idLivro);
				acervo.setLivro(l);
				acervo.setQuantidade(rs.getInt("quantidade"));
				acervo.setId(rs.getInt("id"));

				acervos.add(acervo);
			}
			return acervos;

		} catch (SQLException e) {
			System.out.println("Erro ao consultar os acervos no BD");
			e.printStackTrace();
		}

		return null;

		// private Livro localizarLivro(int idlivro){

		// }

	}

	private Livro localizarLivro(int idLivro) {
		String sql = "SELECT * FROM   tb_usuarios WHERE id_livro = ? ";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idLivro);
			ResultSet rs = ps.executeQuery(sql);

			while (rs.next()) {
				Livro l = new Livro();

				l.setNome(rs.getString("nome"));
				l.setAutor(rs.getString("autor"));
				l.setEditora(rs.getString("editora"));
				l.setCodigoLivro(rs.getString("codLivro"));
				l.setAno(rs.getInt("ano"));

				return l;
			}
		} catch (SQLException e) {

			System.out.println("Erro ao consultar os acervos no BD");
			e.printStackTrace();
		}

		return null;
	}

	// -------------------------------------------------------------------------
	public void deletar(int id) {

		// IMPLEMENTAR UM METODO P
		// verificar se o acervo pode ser deletado

		// id do Acervo que queremos deletar
		String sql = "DELETE FROM tb_acervo WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int rowDelete = ps.executeUpdate();
			if (rowDelete > 0) {
				System.out.println("Acervo deletado!!!");
			} else {
				System.out.println("Acervo não deletado!!!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// -------------------------------------------------------------------------
	public void atualizar(Acervo acervo) { // atualizar um Acervo
		String sql = "UPDATE tb_ SET id_livro = ?, quantidade = ? WHERE id = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, acervo.getLivro().getId());
			ps.setInt(2, acervo.getQuantidade());
			ps.setInt(3, acervo.getId());
			int rowUpdate = ps.executeUpdate();

			if (rowUpdate > 0) {
				System.out.println("Acervo atualizado!");
			} else {
				System.out.println("Acervo  atualizado!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void salvar(Emprestimo emprestimo) {
	}

}
