package com.ucsal.semoc.models;

import com.google.gson.annotations.SerializedName;

public class EntityModel {
  @SerializedName("id")
  private int id;

  @SerializedName("nome")
  private String nome;

  @SerializedName("bio")
  private String bio;

  @SerializedName("tipo")
  private String tipo;

  @SerializedName("foto_url")
  private String foto_url;

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

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public String getFoto_url() {
    return foto_url;
  }

  public void setFoto_url(String foto_url) {
    this.foto_url = foto_url;
  }
}
