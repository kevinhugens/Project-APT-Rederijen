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

        // logging
        return rederij;
    }

    public Rederij insertRederij(Rederij rederij){
        //logging
        return rederijRepository.save(rederij);
    }

    public void deleteRederij(int rederijId){
        //logging
        rederijRepository.deleteById(rederijId);
    }
}
