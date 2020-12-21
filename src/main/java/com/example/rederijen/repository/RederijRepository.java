package com.example.rederijen.repository;

import com.example.rederijen.models.Rederij;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RederijRepository extends MongoRepository<Rederij, Integer> {


    Rederij findRederijByRederijdID(int id);

    List<Rederij> findRederijsByPostcode(String postcode);

    Rederij findRederijByTelefoon(String telefoon);
}
