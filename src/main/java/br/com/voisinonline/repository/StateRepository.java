package br.com.voisinonline.repository;

import br.com.voisinonline.model.State;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends MongoRepository<State, Long> {

}