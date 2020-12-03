package com.example.rederijen.repository;

import com.example.rederijen.models.Rederij;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RederijRepository extends JpaRepository<Rederij, Integer> {


    Rederij findRederijByRederijdID(int id);

    List<Rederij> findRederijsByPostcode(String postcode);

    Rederij findRederijByTelefoon(String telefoon);
}
