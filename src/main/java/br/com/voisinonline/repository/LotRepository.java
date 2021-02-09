package br.com.voisinonline.repository;

import br.com.voisinonline.model.Lot;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LotRepository extends MongoRepository<Lot, String> {
}
