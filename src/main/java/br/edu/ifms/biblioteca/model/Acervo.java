package br.edu.ifms.biblioteca.model;

public class Acervo {
	
	private int id;
	private Livro livro;	// chave estrangeira cod_livro
	private int quantidade; // 4 td vez q for emprestado -1 até = 1
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public String verificarDispo() {
		if (quantidade > 1 ) {
			return "ok";
		} else {
			return "O livro não pode ser emprestado! Quantidade inferior a 1!";
		}
	}

	
	
	
	
	
}
