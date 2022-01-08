package br.edu.ifms.biblioteca.model;

import java.io.Serializable;

public class Livro implements Serializable {
	
	private int id; // auto
	private String nome;
	private String autor; // rever..
	private String editora;
	private String codigoLivro; // diferenciar livros iguais #XX99999-9
	private int ano;
	

	public Livro(){
		gerarCodLivro();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getEditora() {
		return editora;
	}
	public void setEditora(String editora) {
		this.editora = editora;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getCodigoLivro() {
		return codigoLivro;
	}
	public void setCodigoLivro(String codigoLivro) {
		this.codigoLivro = codigoLivro;
	}
	
	@Override
	public String toString() {
		String msg = getNome() + "\n " +
					 getAutor() + "\n " +
					 getCodigoLivro() + "\n " +
					 getEditora() + "\n " +
					 getAno();
		return msg;
	}
	//-------------------------------------------------------------------------------
	//							CRIAR UM ALG P GERAR O CÓDIGO DO LIVRO
	//-------------------------------------------------------------------------------
	private String gerarCodLivro() {
		
		return getCodigoLivro();
	}
	

}
