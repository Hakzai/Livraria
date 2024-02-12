package controller.view;

import controller.dao.ExcluirDAO;
import model.classe.Livro;
import controller.dao.MainDAO;
import helper.statics.IdStorageHelper;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class FXMLMainController {

	private ObservableList<Livro> completeBookList;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorPaneMain;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnLimpar;

    @FXML
    private Button btnExcluir;

    @FXML
    private TableView<Livro> tableMainLivraria = new TableView<>();

    @FXML
    private TableColumn<Livro, Integer> tableColumnMainId;

    @FXML
    private TableColumn<Livro, String> tableColumnMainTitulo;

    @FXML
    private TableColumn<Livro, String> tableColumnMainAutor;

    @FXML
    private TableColumn<Livro, String> tableColumnMainEditora;

    @FXML
    private TableColumn<Livro, Integer> tableColumnMainNroPaginas;

    @FXML
    private TableColumn<Livro, String> tableColumnMainCategoria;

    @FXML
    private TableColumn<Livro, String> tableColumnMainStatus;

    @FXML
    private Label lbMainTitulo;

    @FXML
    private TextField txtTitulo;

    @FXML
    private TextField txtAutor;

    @FXML
    private TextField txtEditora;

    @FXML
    private TextField txtCategoria;

    @FXML
    private Label lbFiltrar;

    @FXML
    private Label lbTitulo;

    @FXML
    private Label lbAutor;

    @FXML
    private Label lbEditora;

    @FXML
    private Label lbCategoria;
    
    @FXML
    private Label lbContem;

    @FXML
    void handleBtnCadastrar(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/telasfx/FXMLCadastrar.fxml"));
        Pane root = loader.load();
        FXMLCadastrarController cadastrarController = (FXMLCadastrarController) loader.getController();
        
        // ESCONDE A TELA MAIN
        btnCadastrar.setDisable(true);
        Stage main = (Stage) AnchorPaneMain.getScene().getWindow();
        main.hide();
        
        Scene scene = new Scene(root);	
        Stage stage = new Stage();
        stage.setTitle("Tela Cadastro");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        
        // APOS FECHAR A JANELA MOSTRA A TELA MAIN E HABILITA O BOTAO NOVAMENTE
        btnCadastrar.setDisable(false); 
        main.show();
        readTable();
        
        limpaCampos();
        setDefaultCompleteList();
    }

    @FXML
    void handleBtnLimpar(ActionEvent event) {
        limpaCampos();
        setDefaultCompleteList();
    }
    
    @FXML
    void handleBtnEditar(ActionEvent event) throws Exception {
        
        // AQUI VERIFICAMOS SE HA LINHA SELECIONADA, PEGAMOS O ID NA TABELA E FAZEMOS A 'EXCLUSAO VISUAL'
        // PODE SER CORRIGIDO NO BANCO DE DADOS, POIS NAO APAGA DE VERDADE
        boolean formCanBeOpened = true;
        
        try{
            IdStorageHelper.setIdLivro(tableMainLivraria.getSelectionModel().getSelectedItem().getId());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Selecione algum livro", "Erro : " + e, 2);
            formCanBeOpened = false;
        }
        
        if(formCanBeOpened){
            
            // ABRIMOS A NOVA TELA E PASSAMOS
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/telasfx/FXMLEditar.fxml"));
            Pane root = loader.load();
            FXMLEditarController editarController = (FXMLEditarController) loader.getController();

            // ESCONDE A TELA MAIN
            btnEditar.setDisable(true);
            Stage main = (Stage) AnchorPaneMain.getScene().getWindow();
            main.hide();

            Scene scene = new Scene(root);	
            Stage stage = new Stage();
            stage.setTitle("Editar Livro");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            // APOS FECHAR A JANELA MOSTRA A TELA MAIN E HABILITA O BOTÃO NOVAMENTE
            btnEditar.setDisable(false); 
            main.show();
            readTable();
            
            limpaCampos();
            setDefaultCompleteList();
            
            IdStorageHelper.setIdLivro(0);
        }
        
    }

    @FXML
    void handleBtnExcluir(ActionEvent event) {
        
        // AQUI VERIFICAMOS SE HA LINHA SELECIONADA, PEGAMOS O ID NA TABELA E FAZEMOS A 'EXCLUSAO VISUAL'
        // PODE SER CORRIGIDO NO BANCO DE DADOS, POIS NAO APAGA DE VERDADE
        try{
            IdStorageHelper.setIdLivro(tableMainLivraria.getSelectionModel().getSelectedItem().getId());
            int resposta = JOptionPane.showConfirmDialog( null,"Esta operacao nao pode ser desfeita!", "Esta certo disso?",JOptionPane.YES_NO_OPTION);
            
            if(resposta == 0){
                ExcluirDAO excluir = new ExcluirDAO();
                excluir.delete(IdStorageHelper.getIdLivro());
                        
                readTable();
            }
            
            IdStorageHelper.setIdLivro(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Selecione algum livro", "Erro : " + e, 2);
        }
        
    }
    
    @FXML
    void handleFilterAction(Event event) 
    {
    	doFilterTable();
    }
    
    private void doFilterTable()
    {
		String titulo = txtTitulo.getText();
		String autor = txtAutor.getText();
		String editora = txtEditora.getText();
		String categoria = txtCategoria.getText();
    	
		if(titulo.isEmpty() && autor.isEmpty() && editora.isEmpty() && categoria.isEmpty())
		{
			setDefaultCompleteList();
			return;
		}

		ObservableList<Livro> filteredBookList = FXCollections.observableArrayList(completeBookList);
		
		if(!titulo.isEmpty())
		{
			doFilterRowByTitle(titulo, filteredBookList);
		}
		if(!autor.isEmpty())
		{
			doFilterRowByAutor(autor, filteredBookList);
		}
		if(!editora.isEmpty())
		{
			doFilterRowByEditora(editora, filteredBookList);
		}
		if(!categoria.isEmpty())
		{
			doFilterRowByCategoria(categoria, filteredBookList);
		}
		
		tableMainLivraria.setItems(filteredBookList);
    }
    
    private void doFilterRowByTitle(String titulo, ObservableList<Livro> filteredBookList)
    {
    	ObservableList<Livro> filteredByTitle = FXCollections.observableArrayList();
    	
    	for(Livro livro : filteredBookList)
    	{
    		if(null != livro.getTitulo() && livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) 
    		{
    			filteredByTitle.add(livro);
    		}
    	}
    	
    	filteredBookList.clear();
    	filteredBookList.addAll(filteredByTitle);
	}
    
    private void doFilterRowByAutor(String autor, ObservableList<Livro> filteredBookList)
    {
		ObservableList<Livro> filteredByAutor = FXCollections.observableArrayList();
    	
    	for(Livro livro : filteredBookList)
    	{
    		if(null != livro.getAutor() && livro.getAutor().toLowerCase().contains(autor.toLowerCase())) 
    		{
    			filteredByAutor.add(livro);
    		}
    	}
    	
    	filteredBookList.clear();
    	filteredBookList.addAll(filteredByAutor);
	}
    
    private void doFilterRowByEditora(String editora, ObservableList<Livro> filteredBookList)
    {
		ObservableList<Livro> filteredByEditora = FXCollections.observableArrayList();
    	
    	for(Livro livro : filteredBookList)
    	{
    		if(null != livro.getEditora() && livro.getEditora().toLowerCase().contains(editora.toLowerCase())) 
    		{
    			filteredByEditora.add(livro);
    		}
    	}
    	
    	filteredBookList.clear();
    	filteredBookList.addAll(filteredByEditora);
    }
    
    private void doFilterRowByCategoria(String categoria, ObservableList<Livro> filteredBookList)
    {
		ObservableList<Livro> filteredByCategoria = FXCollections.observableArrayList();
    	
    	for(Livro livro : filteredBookList)
    	{
    		if(null != livro.getCategoria() && livro.getCategoria().toLowerCase().contains(categoria.toLowerCase())) 
    		{
    			filteredByCategoria.add(livro);
    		}
    	}
    	
    	filteredBookList.clear();
    	filteredBookList.addAll(filteredByCategoria);
    }

	public void limpaCampos(){
        txtTitulo.setText("");
        txtAutor.setText("");
        txtEditora.setText("");
        txtCategoria.setText("");
    }
    
	private void setDefaultCompleteList()
	{
		tableMainLivraria.setItems(completeBookList);
	}
	
    @FXML
    void initialize() {
        assert AnchorPaneMain != null : "fx:id=\"AnchorPaneMain\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert btnCadastrar != null : "fx:id=\"btnCadastrar\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert btnEditar != null : "fx:id=\"btnEditar\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert btnLimpar != null : "fx:id=\"btnLimpar\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert btnExcluir != null : "fx:id=\"btnExcluir\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert tableMainLivraria != null : "fx:id=\"tableMainLivraria\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert tableColumnMainId != null : "fx:id=\"tableColumnMainId\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert tableColumnMainTitulo != null : "fx:id=\"tableColumnMainTitulo\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert tableColumnMainAutor != null : "fx:id=\"tableColumnMainAutor\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert tableColumnMainEditora != null : "fx:id=\"tableColumnMainEditora\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert tableColumnMainNroPaginas != null : "fx:id=\"tableColumnMainNroPaginas\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert tableColumnMainCategoria != null : "fx:id=\"tableColumnMainCategoria\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert tableColumnMainStatus != null : "fx:id=\"tableColumnMainStatus\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert lbMainTitulo != null : "fx:id=\"lbMainTitulo\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert txtTitulo != null : "fx:id=\"txtTitulo\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert txtAutor != null : "fx:id=\"txtAutor\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert txtEditora != null : "fx:id=\"txtEditora\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert txtCategoria != null : "fx:id=\"txtCategoria\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert lbFiltrar != null : "fx:id=\"lbFiltrar\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert lbTitulo != null : "fx:id=\"lbTitulo\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert lbAutor != null : "fx:id=\"lbAutor\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert lbEditora != null : "fx:id=\"lbEditora\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert lbCategoria != null : "fx:id=\"lbCategoria\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert lbContem != null : "fx:id=\"lbContem\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        
        readTable();
    }
    
    public void readTable(){
        // Array para abrigar as tuplas da tabela
        ObservableList<Livro> mainOList = FXCollections.observableArrayList();
        MainDAO mDAO = new MainDAO();
        
        // Busca no banco todas as informações
        for(Livro livro : mDAO.listar()){
            mainOList.add(new Livro(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getEditora(),
                livro.getNroPaginas(),
                livro.getCategoria(),
                livro.getSituacao()
            ));
        }
        
        // Determina qual coluna recebe qual valor do Modelo chamado
        tableColumnMainId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnMainTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        tableColumnMainAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        tableColumnMainEditora.setCellValueFactory(new PropertyValueFactory<>("editora"));
        tableColumnMainNroPaginas.setCellValueFactory(new PropertyValueFactory<>("nroPaginas"));
        tableColumnMainCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        tableColumnMainStatus.setCellValueFactory(new PropertyValueFactory<>("situacao"));
        
        // Insere os dados na tabela
        tableMainLivraria.setItems(mainOList);
        
        // Insere os dados na lista completa da classe
        completeBookList = mainOList;
    }


}