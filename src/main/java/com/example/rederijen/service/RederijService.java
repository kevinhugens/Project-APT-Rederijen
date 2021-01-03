package com.example.rederijen.service;

import com.example.rederijen.models.Rederij;
import com.example.rederijen.repository.RederijRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class RederijService {

    Logger logger = Logger.getLogger(RederijService.class.getName());

    private RederijRepository rederijRepository;

    public RederijService(RederijRepository rederijRepository) {
        this.rederijRepository = rederijRepository;
    }

    public Rederij getRederijById(int id){
        Rederij rederij;
        rederij = rederijRepository.findRederijByRederijdID(id);

        //logging
        return rederij;
    }

    public List<Rederij> getAllRederijen() {
        List<Rederij> rederijs;
        rederijs = rederijRepository.findAll();

        return rederijs;
    }

    public Rederij getRederijByNaam(String naam) {
        Rederij rederij;
        rederij = rederijRepository.findRederijByNaam(naam);

        return rederij;
    }

    public List<Rederij> getRederijenByPostcode(String postcode){
        List<Rederij> rederijs = new ArrayList<>();
        logger.setLevel(Level.INFO);
        logger.info(postcode);
        rederijs = rederijRepository.findRederijsByPostcode(postcode);
        logger.info(rederijs.toString());
        //logging
        return rederijs;
    }

    public Rederij getRederijByTelefoon(String telefoon) {
        Rederij rederij;
        rederij = rederijRepository.findRederijByTelefoon(telefoon);

        if (rederij != null) {
            logger.info("Rederij met id " + rederij.getRederijdID() + " en telefoonnummer "+ rederij.getTelefoon() +" gevonden.");
        } else {
            logger.warning("Rederij met telefoonnummer " + rederij.getTelefoon() + " bestaat niet, of is niet gevonden");
        }

        return rederij;
    }

    public Rederij insertRederij(Rederij rederij){
        if (rederijRepository.findRederijByNaam(rederij.getNaam()) == null)
        {
            logger.info("Rederij met naam " + rederij.getNaam() + " is toegevoegd");
            return rederijRepository.save(rederij);
        } else {
            logger.warning("Rederij met deze naam bestaat al");
            return null;
        }

    }

    public Rederij updateRederij(Rederij rederij) {
        Rederij newRederij = rederijRepository.findRederijByRederijdID(rederij.getRederijdID());

        newRederij.setGemeente(rederij.getGemeente());
        newRederij.setMail(rederij.getMail());
        newRederij.setNaam(rederij.getNaam());
        newRederij.setPostcode(rederij.getPostcode());
        newRederij.setTelefoon(rederij.getTelefoon());

        rederijRepository.save(newRederij);
        return newRederij;
    }

    public void deleteRederij(int rederijId){
        //logging
        rederijRepository.deleteById(rederijId);
    }
}
