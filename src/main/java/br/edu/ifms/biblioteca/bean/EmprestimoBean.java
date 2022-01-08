package br.edu.ifms.biblioteca.bean;

import static javax.faces.context.FacesContext.getCurrentInstance;
import java.io.Serializable;
//import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

//import br.edu.ifms.biblioteca.dao.AcervoDAO;
import br.edu.ifms.biblioteca.dao.EmprestimoDAO;
//import br.edu.ifms.biblioteca.model.Acervo;
import br.edu.ifms.biblioteca.model.Emprestimo;

//import br.edu.ifms.biblioteca.model.Livro;

@Named("emprestimoBean")
@RequestScoped

public class EmprestimoBean implements Serializable {
    @Inject
    private Emprestimo emprestimo;

    @Inject
    private EmprestimoDAO ldao;

    public Emprestimo getId() {
        return this.emprestimo;
    }

    public void setId(Emprestimo e) {
        this.emprestimo = e;
    }

    // private List<Livro> livros;

    /*
     * public void salvar() { ////////// Salvar
     * 
     * 
     * 
     * 
     * 
     * }
     */

    public void cadastrar() { ///////// CADASTRAR
        try {
            // System.out.println(livro.toString());

            // validar o campos
            String validar = validar(emprestimo);
            if (!validar.equals("ok")) {
                System.out.println(validar);
                showError("Erro na validação", validar);
                // return "erro";
            } else {
                // inserir no bd
                ldao.salvar(emprestimo);
                showInfo("Sucesso!", "emprestimo cadastrado com sucesso!");
            }

        } catch (Exception e) {
            System.out.println("Um erro ocorreu no cadastro!");

        }
        // return "OK";
    }

    private String validar(Emprestimo emprestimo) { /// VALIDAR
        if // id //user
        ((emprestimo.getUser().equals("")) ||
                (emprestimo.getLivros().equals("")) ||
                (emprestimo.getDtEmprestimo().equals("")) ||
                (emprestimo.getDtDevolucao().equals(""))) {

            // list livros
            // dataEmprestimo
            // dataDevolução

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
