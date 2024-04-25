package com.ucsal.semoc.models;

public class ScheduleEventModel {
  private String Date;
  private String Theme;
  private String Activity;
  private String Link;

  public void setDate(String date) {
    this.Date = date;
  }
  public void setTheme(String theme) {
    this.Theme = theme;
  }
  public void setActivity(String activity) {
    this.Activity = activity;
  }
  public void setLink(String link) {
    this.Link = link;
  }
  public String getDate() {
    return this.Date;
  }
  public String getTheme() {
    return this.Theme;
  }
  public String getActivity() {
    return this.Activity;
  }
  public String getLink() {
    return this.Link;
  }
}
