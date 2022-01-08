package br.edu.ifms.biblioteca.bean;

import static javax.faces.context.FacesContext.getCurrentInstance;
import java.io.Serializable;
//import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifms.biblioteca.dao.AcervoDAO;

import br.edu.ifms.biblioteca.model.Acervo;
//import br.edu.ifms.biblioteca.model.Livro;
//import br.edu.ifms.biblioteca.model.Livro;
//import br.edu.ifms.biblioteca.model.Livro;

@Named("acervoBean")
@RequestScoped

public class AcervoBean implements Serializable {
    @Inject
    private Acervo acervo;

    @Inject
    private AcervoDAO ldao;

    public Acervo getaAcervo() {
        return this.acervo;
    }

    public void setaArcevo(Acervo a) {
        this.acervo = a;
    }

    // private List<Livro> livros;

    public void salvar() {

    }

    public void cadastrar() {
        try {
            // System.out.println(livro.toString());

            // validar o campos
            String validar = validar(acervo);
            if (!validar.equals("ok")) {
                System.out.println(validar);
                showError("Erro na validação", validar);
                // return "erro";
            } else {
                // inserir no bd
                ldao.salvar(acervo);
                showInfo("Sucesso!", "Acervo cadastrado com sucesso!");
            }

        } catch (Exception e) {
            System.out.println("Um erro ocorreu no cadastro!");

        }
        // return "OK";
    }

    private String validar(Acervo acervo) {
        if ((acervo.getLivro().equals("")) ||
                (acervo.getQuantidade() == 0)) {

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