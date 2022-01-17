package br.edu.ifms.biblioteca.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
//import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifms.biblioteca.bd.ConexaoMySQL;
import br.edu.ifms.biblioteca.model.Emprestimo;
import br.edu.ifms.biblioteca.model.Livro;
import br.edu.ifms.biblioteca.model.Usuario;
//mport br.edu.ifms.biblioteca.model.Livro;
// import br.edu.ifms.biblioteca.model.Usuario;

public class EmprestimoDAO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Connection conn = null;

    public EmprestimoDAO() {
        // iniciar uma conexao com BD

        ConexaoMySQL conexao = new ConexaoMySQL();
        conn = conexao.getConnection();
    }

    // CRUD

     private int id;
     private Usuario user;
     private List<Livro> livros;
     private LocalDate dtEmprestimo;
     private LocalDate dtDevolucao;
     
     

    public void salvar(Emprestimo emprestimo) { // inserir um novo emprestimo
        String sql = "INSERT INTO tb_emprestimo (id,  id_usuario, id_livro, data_emprestimo, data_devolucao) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, emprestimo.getId());
            ps.setInt(2, emprestimo.getUser().getId());
            ps.setInt(3, ((Emprestimo) emprestimo.getLivros()).getId());
            emprestimo.getDtDevolucao();
			LocalDate.now();
            emprestimo.getDtEmprestimo();
			LocalDate.now();
          // ps.setDate(4, emprestimo.getDtDevolucao().adjustInto(dtDevolucao));
          //  ps.setDate(4, emprestimo.getDtDevolucao(x);
           
            
          //  ps.setDate(4, new java.sql.Date(emprestimo.getDtDevolucao().getDayOfMonth()));
          //  ps.setDate((5, emprestimo.getDtEmprestimo().LocalDate.now()));

            int resultado = ps.executeUpdate();
            if (resultado > 0)
                System.out.println("O Emprestimo foi Realizado");
            else
                System.out.println("Problema ao cadastrar emprestimo");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // -------------------------------------------------------------------------
    public List<Emprestimo> selecionarTodos() { // buscar todos os livros no BD
        List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();

        String sql = "SELECT * FROM tb_emprestimo";
        try {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Emprestimo emprestimo = new Emprestimo();
                emprestimo.getId(); // id
                emprestimo.getUser().getId(); // Usuario user
                ((Emprestimo) emprestimo.getLivros()).getId();
                emprestimo.getDtDevolucao();
				LocalDate.now();
                emprestimo.getDtEmprestimo();
				LocalDate.now();

                emprestimos.add(emprestimo);
            }
            // return emprestimo;

        } catch (SQLException e) {
            System.out.println("Erro ao consultar os emprestimos no BD");
            e.printStackTrace();
        }

        return null;

    }

    // -------------------------------------------------------------------------
    public void deletar(int id) { // id do emprestimo que queremos deletar
        String sql = "DELETE FROM tb_emprestimo WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int rowDelete = ps.executeUpdate();
            if (rowDelete > 0) {
                System.out.println("Emprestimo deletado!!!");
            } else {
                System.out.println("Emprestimo não deletado!!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
   
    // -------------------------------------------------------------------------
    public void atualizar(Emprestimo emprestimo) { // atualizar um emprestimo
        String sql = "UPDATE tb_emprestimo SET id = ?, id_usuario = ?, id_livro = ?, data_emprestimo = ?, data_devolucao = ?"; // WHERE
        // id =
        // ?";
       
        try {

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, emprestimo.getId());
            ps.setInt(2, emprestimo.getUser().getId());
            emprestimo.getDtDevolucao();
			LocalDate.now();
            emprestimo.getDtEmprestimo();
			LocalDate.now();
          //  ps.setDate(4, new java.sql.Date(emprestimo.getDtDevolucao().getDayOfMonth()));
         //   ps.setDate(5,emprestimo.getDtEmprestimo().format(new Date()));
            
			//ps.setDate(5,new Date());
            
            int rowUpdate = ps.executeUpdate();

            if (rowUpdate > 0) {
                System.out.println("Emprestimo atualizado!");
            } else {
                System.out.println("Emprestimo não atualizado!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 

}
	
	
	
	
	

	
	
	
	

