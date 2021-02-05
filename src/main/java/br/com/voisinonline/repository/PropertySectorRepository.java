package br.com.voisinonline.repository;

import br.com.voisinonline.model.PropertySector;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertySectorRepository extends MongoRepository<PropertySector, String> {

    List<PropertySector> findAllByPropertyIdOrderByNameAsc(String id);

}