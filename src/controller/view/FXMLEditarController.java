package controller.view;

import controller.dao.EditarDAO;
import helper.statics.IdStorageHelper;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.classe.Livro;

public class FXMLEditarController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private AnchorPane anchorPaneEditarLivro;

    @FXML
    private Label lbTitulo;

    @FXML
    private Label lbEditarTitulo;

    @FXML
    private TextField txtTitulo;

    @FXML
    private Label lbAutor;

    @FXML
    private TextField txtAutor;

    @FXML
    private Label lbEditora;

    @FXML
    private TextField txtEditora;

    @FXML
    private Label lbNroPaginas;

    @FXML
    private TextField txtNroPaginas;

    @FXML
    private Label lbCategoria;

    @FXML
    private TextField txtCategoria;

    @FXML
    private Label lbSituacao;

    @FXML
    private TextField txtSituacao;

    @FXML
    private Button btnConcluir;

    @FXML
    private Button btnLimpar;

    @FXML
    void handleBtnLimpar(MouseEvent event) {
        limpaCampos();
    }

    @FXML
    void handleBtnConcluir(MouseEvent event) {
        // VERIFICA SE HÁ ALGUM CAMPO VAZIO
        if(txtTitulo.getText().isEmpty() || txtAutor.getText().isEmpty() || txtEditora.getText().isEmpty() 
                || txtNroPaginas.getText().isEmpty() || txtCategoria.getText().isEmpty() || txtSituacao.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Todos os campos precisam estar preenchidos", "Erro!", 0);
            return;
        }
       else{
            Livro livro = new Livro();
            EditarDAO editar = new EditarDAO();
            
            livro = getTxtValues();
            editar.update(livro);

            JOptionPane.showMessageDialog(null, "Livro Salvo com Sucesso!");
        }
        
        limpaCampos();
        
        Stage stage = (Stage) btnConcluir.getScene().getWindow();
        stage.close();
    }
    
    public void limpaCampos(){
        txtTitulo.setText("");
        txtAutor.setText("");
        txtEditora.setText("");
        txtNroPaginas.setText("");
        txtCategoria.setText("");
        txtSituacao.setText("");
    }
    
    public Livro getTxtValues(){
        
        // PASSA DIRETO NO CONSTRUTOR
        Livro livro = new Livro(
            IdStorageHelper.getIdLivro(),
            txtTitulo.getText(),
            txtAutor.getText(),
            txtEditora.getText(),
            Integer.parseInt(txtNroPaginas.getText()),
            txtCategoria.getText(),
            txtSituacao.getText()
        );
        
        return livro;
    }
    
    public void setTxtValues(){
        
        EditarDAO editar = new EditarDAO();
        Livro livro = editar.findById(IdStorageHelper.getIdLivro());
        
        txtTitulo.setText(livro.getTitulo());
        txtAutor.setText(livro.getAutor());
        txtEditora.setText(livro.getEditora());
        txtNroPaginas.setText(Integer.toString(livro.getNroPaginas()));
        txtCategoria.setText(livro.getCategoria());
        txtSituacao.setText(livro.getSituacao());
    }

    @FXML
    void initialize() {
        assert anchorPaneEditarLivro != null : "fx:id=\"anchorPaneEditarLivro\" was not injected: check your FXML file 'FXMLEditar.fxml'.";
        assert lbTitulo != null : "fx:id=\"lbTitulo\" was not injected: check your FXML file 'FXMLEditar.fxml'.";
        assert lbEditarTitulo != null : "fx:id=\"lbEditarTitulo\" was not injected: check your FXML file 'FXMLEditar.fxml'.";
        assert txtTitulo != null : "fx:id=\"txtTitulo\" was not injected: check your FXML file 'FXMLEditar.fxml'.";
        assert lbAutor != null : "fx:id=\"lbAutor\" was not injected: check your FXML file 'FXMLEditar.fxml'.";
        assert txtAutor != null : "fx:id=\"txtAutor\" was not injected: check your FXML file 'FXMLEditar.fxml'.";
        assert lbEditora != null : "fx:id=\"lbEditora\" was not injected: check your FXML file 'FXMLEditar.fxml'.";
        assert txtEditora != null : "fx:id=\"txtEditora\" was not injected: check your FXML file 'FXMLEditar.fxml'.";
        assert lbNroPaginas != null : "fx:id=\"lbNroPaginas\" was not injected: check your FXML file 'FXMLEditar.fxml'.";
        assert txtNroPaginas != null : "fx:id=\"txtNroPaginas\" was not injected: check your FXML file 'FXMLEditar.fxml'.";
        assert lbCategoria != null : "fx:id=\"lbCategoria\" was not injected: check your FXML file 'FXMLEditar.fxml'.";
        assert txtCategoria != null : "fx:id=\"txtCategoria\" was not injected: check your FXML file 'FXMLEditar.fxml'.";
        assert lbSituacao != null : "fx:id=\"lbSituacao\" was not injected: check your FXML file 'FXMLEditar.fxml'.";
        assert txtSituacao != null : "fx:id=\"txtSituacao\" was not injected: check your FXML file 'FXMLEditar.fxml'.";
        assert btnConcluir != null : "fx:id=\"btnConcluir\" was not injected: check your FXML file 'FXMLEditar.fxml'.";
        assert btnLimpar != null : "fx:id=\"btnLimpar\" was not injected: check your FXML file 'FXMLEditar.fxml'.";
        
        setTxtValues();
    }
}
