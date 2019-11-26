package com.basic.api.restapp.domain;

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
  private Long id;

  @ElementCollection
  private Map<String, String> map;

  @JsonIgnore
  private String url;

  public ObjectModel() {

  }

  public ObjectModel(Long id) {
    this.id = id;
    setUrl();
    map = new HashMap<>();
  }

  public Long getId() {
    return id;
  }

  @JsonAnyGetter
  public Map<String, String> getMap() {
    return map;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setMap(Map<String, String> map) {
    this.map = map;
  }

  public String getUrl() {
    return url;
  }

  private void setUrl() {
    this.url = "https://myrestapp.cisco.com/api/objects/" + id;
  }
}
