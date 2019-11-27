package com.basic.api.restapp.domain;

import com.basic.api.restapp.util.Global;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class ObjectModel {

  @Id
  @GeneratedValue
  private Long uid;

  @ElementCollection
  private Map<String, String> map;

  @JsonIgnore
  private String url;


  public ObjectModel() {

  }

  public ObjectModel(Long uid) {
    this.uid = uid;
    setUrl();
    map = new HashMap<>();
  }

  public Long getUid() {
    return uid;
  }

  @JsonAnyGetter
  public Map<String, String> getMap() {
    return map;
  }

  public void setUid(Long uid) {
    this.uid = uid;
  }

  public void setMap(Map<String, String> map) {
    this.map = map;
  }

  public String getUrl() {
    return url;
  }

  private void setUrl() {
    this.url = Global.URL + uid;
  }
}
