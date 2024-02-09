/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.classe;

/**
 *
 * @author Codeiro
 */
public class Livro {
    
    private int id;
    private String titulo;
    private String autor;
    private String editora;
    private int nroPaginas;
    private String categoria;
    private String situacao;
    
    public Livro(){
        
    }
    
    // PARA CRIAR LIVRO
    public Livro(String titulo, String autor, String editora, int nroPaginas, String categoria, String situacao){
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.nroPaginas = nroPaginas;
        this.categoria = categoria;
        this.situacao = situacao;
    }
    
    // PARA EDITAR LIVRO
    public Livro(int id, String titulo, String autor, String editora, int nroPaginas, String categoria, String situacao){
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.nroPaginas = nroPaginas;
        this.categoria = categoria;
        this.situacao = situacao;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the autor
     */
    public String getAutor() {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * @return the editora
     */
    public String getEditora() {
        return editora;
    }

    /**
     * @param editora the editora to set
     */
    public void setEditora(String editora) {
        this.editora = editora;
    }

    /**
     * @return the nroPaginas
     */
    public int getNroPaginas() {
        return nroPaginas;
    }

    /**
     * @param nroPaginas the nroPaginas to set
     */
    public void setNroPaginas(int nroPaginas) {
        this.nroPaginas = nroPaginas;
    }

    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the situacao
     */
    public String getSituacao() {
        return situacao;
    }

    /**
     * @param situacao the situacao to set
     */
    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    
}
