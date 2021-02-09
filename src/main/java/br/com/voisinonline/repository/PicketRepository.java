package br.com.voisinonline.repository;

import br.com.voisinonline.model.Picket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PicketRepository extends MongoRepository<Picket, String> {

    List<Picket> findAllBySectorIdOrderByNameAsc(String id);
}