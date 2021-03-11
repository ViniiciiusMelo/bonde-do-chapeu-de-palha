package com.example.besmart.models;

import java.util.Date;

public class ModelClass_Ideia {

    private String titulo;
    private String descricao;
    private String status;
    private long dataInscricao;

    public ModelClass_Ideia(){}

    public ModelClass_Ideia(String titulo, String descricao, String status, long dataInscricao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.dataInscricao = dataInscricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(long dataInscricao) {
        this.dataInscricao = dataInscricao;
    }
}
