package br.edu.ifms.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifms.biblioteca.bd.ConexaoMySQL;
import br.edu.ifms.biblioteca.model.Usuario;

// CRUD
public class UserDAO {

	private Connection conn = null;

	public UserDAO() {
		ConexaoMySQL conexao = new ConexaoMySQL();
		conn = conexao.getConnection();
	}

	public void salvar(Usuario user) {
		String sql = "INSERT INTO tb_usuarios (nome, endereco, telefone, nomeUsuario, senha) VALUES (?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getNome());
			ps.setString(2, user.getEndereco());
			ps.setString(3, user.getTelefone());
			ps.setString(4, user.getNomeUsuario());
			ps.setString(5, user.getPassword());

			int resultado = ps.executeUpdate();

			if (resultado > 0) {
				System.out.println("Usuário inserido com sucesso!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Usuario> selecionarTodos() {
		List<Usuario> usuarios = new ArrayList<Usuario>();

		String sql = "SELECT * FROM tb_usuarios";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			// ex: 12 reg
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setNome(rs.getString("nome"));
				usuario.setEndereco(rs.getString("endereco"));
				usuario.setTelefone(rs.getString("telefone"));
				usuario.setNomeUsuario(rs.getString("nomeUsuario"));
				usuario.setPassword(rs.getString("senha"));
				usuarios.add(usuario);

			}
			return usuarios;

		} catch (SQLException e) {

			System.out.println("Erro ao consultar os livros no BD");
			e.printStackTrace();
		}
		return null;

	}

	public void deletar(int id) {
		String sql = "DELETE FROM tb_usuarios WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int rowDelete = ps.executeUpdate();
			if (rowDelete > 0) {
				System.out.println("Usuário deletado!!!");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void atualizar(Usuario user) {
		String sql = "UPDATE tb_usuarios SET nome = ?, endereco = ?, telefone = ?, nomeUsuario = ?, senha = ? WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, user.getNome());
			ps.setString(2, user.getEndereco());
			ps.setString(3, user.getTelefone());
			ps.setString(4, user.getNomeUsuario());
			ps.setString(5, user.getPassword());
			ps.setInt(6, user.getId());

			int rowUpdate = ps.executeUpdate();

			if (rowUpdate > 0) {
				System.out.println("Usuário atualizado!");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
