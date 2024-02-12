package controller.view;

import controller.dao.CadastrarDAO;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.classe.Livro;

public class FXMLCadastrarController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lbTitulo;

    @FXML
    private Label lbCadastrarTitulo;

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
    private ComboBox<String> cbCategoria;

    @FXML
    private Label lbSituacao;

    @FXML
    private ComboBox<String> cbSituacao;

    @FXML
    private Button btSalvar;

    @FXML
    private Button btLimpar;

    @FXML
    void handleBtLimpar(MouseEvent event) {
        limpaCampos();
    }

    @FXML
    void handleBtSalvar(MouseEvent event) {
        
        // VERIFICA SE HA ALGUM CAMPO VAZIO
        if(txtTitulo.getText().isEmpty() || txtAutor.getText().isEmpty() || txtEditora.getText().isEmpty() 
                || txtNroPaginas.getText().isEmpty() || null == cbCategoria.getValue() || null == cbSituacao.getValue()){
            JOptionPane.showMessageDialog(null, "Todos os campos precisam estar preenchidos", "Erro!", 0);
            return;
        }
        else{
            Livro livro = new Livro();
            CadastrarDAO cadastrar = new CadastrarDAO();
            
            livro = getTxtValues();
            cadastrar.save(livro);

            JOptionPane.showMessageDialog(null, "Livro Salvo com Sucesso!");
        }
        
        limpaCampos();
        
        Stage stage = (Stage) btSalvar.getScene().getWindow();
        stage.close();
        
    }
    
    public void limpaCampos(){
        txtTitulo.setText("");
        txtAutor.setText("");
        txtEditora.setText("");
        txtNroPaginas.setText("");
        cbCategoria.setValue(null);
        cbSituacao.setValue(null);
    }
    
    public Livro getTxtValues(){
        
        // PASSANDO DIRETO NO CONSTRUTOR
        Livro livro = new Livro(
            txtTitulo.getText(),
            txtAutor.getText(),
            txtEditora.getText(),
            Integer.parseInt(txtNroPaginas.getText()),
            cbCategoria.getValue(),
            cbSituacao.getValue()
        );
        
        return livro;
    }

    @FXML
    void initialize() {
        assert lbTitulo != null : "fx:id=\"lbTitulo\" was not injected: check your FXML file 'FXMLCadastrar.fxml'.";
        assert lbCadastrarTitulo != null : "fx:id=\"lbCadastrarTitulo\" was not injected: check your FXML file 'FXMLCadastrar.fxml'.";
        assert txtTitulo != null : "fx:id=\"txtTitulo\" was not injected: check your FXML file 'FXMLCadastrar.fxml'.";
        assert lbAutor != null : "fx:id=\"lbAutor\" was not injected: check your FXML file 'FXMLCadastrar.fxml'.";
        assert txtAutor != null : "fx:id=\"txtAutor\" was not injected: check your FXML file 'FXMLCadastrar.fxml'.";
        assert lbEditora != null : "fx:id=\"lbEditora\" was not injected: check your FXML file 'FXMLCadastrar.fxml'.";
        assert txtEditora != null : "fx:id=\"txtEditora\" was not injected: check your FXML file 'FXMLCadastrar.fxml'.";
        assert lbNroPaginas != null : "fx:id=\"lbNroPaginas\" was not injected: check your FXML file 'FXMLCadastrar.fxml'.";
        assert txtNroPaginas != null : "fx:id=\"txtNroPaginas\" was not injected: check your FXML file 'FXMLCadastrar.fxml'.";
        assert lbCategoria != null : "fx:id=\"lbCategoria\" was not injected: check your FXML file 'FXMLCadastrar.fxml'.";
        assert lbSituacao != null : "fx:id=\"lbSituacao\" was not injected: check your FXML file 'FXMLCadastrar.fxml'.";
        assert cbSituacao != null : "fx:id=\"cbSituacao\" was not injected: check your FXML file 'FXMLCadastrar.fxml'.";
        assert btSalvar != null : "fx:id=\"btSalvar\" was not injected: check your FXML file 'FXMLCadastrar.fxml'.";
        assert btLimpar != null : "fx:id=\"btLimpar\" was not injected: check your FXML file 'FXMLCadastrar.fxml'.";
        assert cbCategoria != null : "fx:id=\"cbCategoria\" was not injected: check your FXML file 'FXMLCadastrar.fxml'.";
        
        loadCategorias();
        loadSituacoes();
    }
    
    private void loadCategorias()
    {
    	CadastrarDAO cadastrar = new CadastrarDAO();
    	cbCategoria.setItems(cadastrar.getAllCategories());
    }
    
    private void loadSituacoes()
    {
    	String[] status = {"Novo", "Não Lido", "Lendo", "Lido"};
    	ObservableList<String> statusList= FXCollections.observableArrayList(Arrays.asList(status));
    	cbSituacao.setItems(statusList);
    }
}
