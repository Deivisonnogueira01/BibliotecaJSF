package br.edu.ifms.biblioteca.model;

import java.time.LocalDate;
import java.util.List;

public class Emprestimo {
	
	private int id;
	private Usuario user;
	private List<Livro> livros; // será colocado objetos do tipo Livro
	private LocalDate dtEmprestimo; // 
	private LocalDate dtDevolucao; //
	
	
	public List<Livro> getLivros() {
		return livros;
	}
	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Usuario getUser() {
		return user;
	}
	public void setUser(Usuario user) {
		this.user = user;
	}
	
	public LocalDate getDtEmprestimo() {
		return dtEmprestimo;
	}
	public void setDtEmprestimo(LocalDate dtEmprestimo) {
		this.dtEmprestimo = dtEmprestimo;
	}
	public LocalDate getDtDevolucao() {
		return dtDevolucao;
	}
	public void setDtDevolucao(LocalDate dtDevolucao) {
		this.dtDevolucao = dtDevolucao;
	}
	
	

}
