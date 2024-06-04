package com.ucsal.semoc.abstractions;

import java.io.Serializable;

public abstract class Item implements Serializable {
  protected abstract String getData();

  protected abstract String getTema();

  protected abstract String getHora();
}