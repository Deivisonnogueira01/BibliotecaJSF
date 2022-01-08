package br.edu.ifms.biblioteca.bean;

import static javax.faces.context.FacesContext.getCurrentInstance;
import java.io.Serializable;
//import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
//import br.edu.ifms.biblioteca.dao.LivroDAO;
import br.edu.ifms.biblioteca.dao.UserDAO;
//import br.edu.ifms.biblioteca.model.Livro;
import br.edu.ifms.biblioteca.model.Usuario;

@Named("usuarioBean")
@RequestScoped

public class UsuarioBean implements Serializable {
   
	private static final long serialVersionUID = 1L;

	@Inject
    private Usuario usuario;

    @Inject
    private UserDAO ldao;

    // private List<Livro> livros;

    public void cadastrar() { /////////////// CADASTRAR
        try {
            // System.out.println(livro.toString());

            // validar o campos
            String validar = validar(usuario);
            if (!validar.equals("ok")) {
                System.out.println(validar);
                showError("Erro na validação", validar);
                // return "erro";
            } else {
                // inserir no bd
                ldao.salvar(usuario);
                showInfo("Sucesso!", "Usuario cadastrado com sucesso!");
            }

        } catch (Exception e) {
            System.out.println("Um erro ocorreu no cadastro!");

        }
        // return "OK";
    }

    private String validar(Usuario usuario) { /////////////// VALIDAR
        if // ((usuario.getId().equals("")) || //id
        ((usuario.getNome().equals("")) || // nome
                (usuario.getEndereco().equals("")) || // endereco
                (usuario.getTelefone().equals("")) || // telefone
                (usuario.getNomeUsuario().equals("")) || // nomeUsuario
                (usuario.getPassword().equals(""))) { // passWord

            return "Preencha os campos corretamente!";
        }

        return "ok";
    }

    private void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public void showInfo(String titulo, String msg) {
        addMessage(FacesMessage.SEVERITY_INFO, titulo, msg);
    }

    public void showWarn(String titulo, String msg) {
        addMessage(FacesMessage.SEVERITY_WARN, titulo, msg);
    }

    public void showError(String titulo, String msg) {
        addMessage(FacesMessage.SEVERITY_ERROR, titulo, msg);
    }

}