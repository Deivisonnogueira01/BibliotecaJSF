package br.edu.ifms.biblioteca.model;

public class Usuario extends Pessoa {
	
	//-------------------------------------------------------------------------------
	private String nomeUsuario; // Atributos
	private String password; // Atributos
	//-------------------------------------------------------------------------------
	//							GETTERS SETTERS
	//-------------------------------------------------------------------------------
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	//-------------------------------------------------------------------------------
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	//-------------------------------------------------------------------------------
	public String getPassword() {
		return password;
	}
	//-------------------------------------------------------------------------------
	public void setPassword(String password) {
		this.password = password;
	}
	//-------------------------------------------------------------------------------
	//								VALIDAR LOGIN
	//-------------------------------------------------------------------------------
	public boolean login(String u, String p) {
		if((u.equalsIgnoreCase(nomeUsuario)) && (p.equalsIgnoreCase(password))){
			return true;
		} else {
			return false;
		}
	}
	//-------------------------------------------------------------------------------

}
