package br.com.voisinonline.repository;

import br.com.voisinonline.model.Property;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends MongoRepository<Property, String> {
}