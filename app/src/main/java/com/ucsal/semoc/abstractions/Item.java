package com.ucsal.semoc.abstractions;

import java.io.Serializable;

public abstract class Item implements Serializable {
  public abstract String getData();

  public abstract String getTema();

  public abstract String getHora();

  public abstract String getNome();

  public abstract String getDescricao();

  public abstract String getLocal();

  public abstract String getFormato();

  public abstract String getNivel();

  public abstract int getEntity();
}