package com.ucsal.semoc.models;

import com.google.gson.annotations.SerializedName;
import com.ucsal.semoc.abstractions.Item;

import java.io.Serializable;

public class LecturesModel extends Item implements Serializable {
  @SerializedName("id")
  private int id;

  @SerializedName("nome")
  private String nome;

  @SerializedName("descricao")
  private String descricao;

  @SerializedName("data")
  private String data;

  @SerializedName("hora")
  private String hora;

  @SerializedName("local")
  private String Local;

  @SerializedName("palestrante_id")
  private int palestrante_id;

  @SerializedName("tema")
  private String tema;

  @SerializedName("nivel")
  private String nivel;

  @SerializedName("formato")
  private String formato;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public String getHora() {
    return hora;
  }

  public void setHora(String hora) {
    this.hora = hora;
  }

  public String getLocal() {
    return Local;
  }

  public void setLocal(String local) {
    Local = local;
  }

  public int getPalestrante_id() {
    return palestrante_id;
  }

  public void setPalestrante_id(int palestrante_id) {
    this.palestrante_id = palestrante_id;
  }

  public String getTema() {
    return tema;
  }

  public void setTema(String tema) {
    this.tema = tema;
  }

  public String getNivel() {
    return nivel;
  }

  public void setNivel(String nivel) {
    this.nivel = nivel;
  }

  public String getFormato() {
    return formato;
  }

  public void setFormato(String formato) {
    this.formato = formato;
  }
}
