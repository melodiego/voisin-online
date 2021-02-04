package br.com.voisinonline.repository;

import br.com.voisinonline.model.PropertySector;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertySectorRepository extends MongoRepository<PropertySector, String> {

}