package br.com.voisinonline.repository;

import br.com.voisinonline.model.LotAllocate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LotAllocateRepository extends MongoRepository<LotAllocate, String> {
}