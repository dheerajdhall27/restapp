package com.basic.api.restapp.controller;


import com.basic.api.restapp.domain.ObjectModel;
import com.basic.api.restapp.service.IService;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ObjectController {

  @Autowired
  private IService objectService;

  private AtomicLong nextId = new AtomicLong();

  @GetMapping("/api/objects")
  public ResponseEntity<List<Map<String, String>>> getAllData() {
    Iterable<ObjectModel> iterable = objectService.lookup();

    List<Map<String, String>> list = new ArrayList<>();
    iterable.forEach(data -> {
      Map<String, String> map = new HashMap<>();
      map.put("url", data.getUrl());
      list.add(map);
    });

    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @GetMapping("/api/objects/{id}")
  public ResponseEntity<ObjectModel> getObject(@PathVariable(value = "id") Long id) {
    ObjectModel objectModel = objectService.findById(id);

    return new ResponseEntity<>(objectModel, HttpStatus.FOUND);
  }

  @PostMapping("/api/objects")
  public ResponseEntity<ObjectModel> createData(@RequestBody JsonNode jsonNode)
      throws HttpMessageNotReadableException {
    Map<String, String> map = new HashMap<>();

    Iterator<String> fieldNamesIterator = jsonNode.fieldNames();

    while (fieldNamesIterator.hasNext()) {
      String fieldName = fieldNamesIterator.next();

      map.put(fieldName, jsonNode.get(fieldName).textValue());
    }

    ObjectModel objectModel = new ObjectModel(nextId.incrementAndGet());
    objectModel.setMap(map);

    objectModel = objectService.createObject(objectModel.getId(), objectModel.getMap());

    return new ResponseEntity<>(objectModel, HttpStatus.CREATED);
  }


  @PutMapping("/api/objects/{id}")
  public ResponseEntity<ObjectModel> updateData(@PathVariable(value = "id") Long id,
      @RequestBody JsonNode jsonNode) {
    ObjectModel objectModel = objectService.findById(id);

    if (objectModel == null) {
      // TODO : ADD CUSTOM EXCEPTION
      return null;
    }

    Iterator<String> fieldNamesIterator = jsonNode.fieldNames();
    Map<String, String> map = new HashMap<>();

    while (fieldNamesIterator.hasNext()) {
      String fieldName = fieldNamesIterator.next();
      map.put(fieldName, jsonNode.get(fieldName).textValue());
    }

    objectModel.setId(id);
    objectModel.setMap(map);

    objectService.save(objectModel);

    return new ResponseEntity<>(objectModel, HttpStatus.CREATED);
  }

  @DeleteMapping("/api/objects/{id}")
  public void deleteData(@PathVariable(value = "id") Long id) {
    objectService.delete(id);
  }
}
