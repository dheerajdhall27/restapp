package com.basic.api.restapp.service;

import com.basic.api.restapp.domain.ObjectModel;
import java.util.Map;
import org.springframework.stereotype.Service;


@Service("IService")
public interface IService {

  ObjectModel createObject(Long id, Map<String, String> map);

  Iterable<ObjectModel> lookup();

  ObjectModel findById(Long id);

  Long total();

  void save(ObjectModel objectModel);

  Boolean delete(Long id);
}
