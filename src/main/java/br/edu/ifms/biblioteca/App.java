package br.edu.ifms.biblioteca;

import java.util.List;

import br.edu.ifms.biblioteca.bd.ConexaoMySQL;
import br.edu.ifms.biblioteca.dao.LivroDAO;
import br.edu.ifms.biblioteca.model.Livro;

public class App {

	public static void main(String[] args) {
		
		ConexaoMySQL conexao = new ConexaoMySQL();
		conexao.getConnection(); // esse método abre a conexao com Mysql (BD)


		LivroDAO ldao = new LivroDAO();
		List<Livro> livros = ldao.selecionarTodos();

		System.out.println(livros.size());


		
		
		
	}

}
