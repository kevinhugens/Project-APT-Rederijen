package com.example.rederijen.controller;

import com.example.rederijen.models.Rederij;
import com.example.rederijen.repository.RederijRepository;
import com.example.rederijen.service.RederijService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class RederijController {


    private RederijService rederijService;
    private RederijRepository rederijRepository;
    Logger logger = Logger.getLogger(RederijController.class.getName());

    public RederijController(RederijService rederijService, RederijRepository rederijRepository) {
        this.rederijService = rederijService;
        this.rederijRepository = rederijRepository;
    }

    @GetMapping("/rederijen/{id}")
    public Rederij getRederijByID(
            @PathVariable String id){
        return rederijService.getRederijById(id);
    }

    @GetMapping("/rederijen")
    public List<Rederij> getAllRederijen() {
        return rederijService.getAllRederijen();
    }

    @GetMapping("/rederijen/naam/{naam}")
    public Rederij getRederijByNaam(
            @PathVariable String naam) {
        return rederijService.getRederijByNaam(naam);
    }

    @GetMapping("/rederijen/postcode/{postcode}")
    public List<Rederij> getRederijenByPostcode(
            @PathVariable String postcode){
        logger.setLevel(Level.INFO);
        logger.info(rederijRepository.findAll().toString());

        return rederijService.getRederijenByPostcode(postcode);
    }

    @GetMapping("/rederijen/telefoon/{telefoon}")
    public Rederij getRederijByTelefoon(
            @PathVariable String telefoon){
        return rederijService.getRederijByTelefoon(telefoon);
    }

    @DeleteMapping("rederijen/delete/{id}")
    public void deleteRederijByID(
            @PathVariable String id){
        rederijService.deleteRederij(id);
    }

    @PutMapping("rederijen/update")
    public Rederij updateRederij(
            @RequestBody Rederij rederij) {
        Rederij retrievedRederij;

        retrievedRederij = rederijService.updateRederij(rederij);

        return retrievedRederij;
    }

    @PostMapping("/rederijen/insert")
    public Rederij insertRederij(
            @RequestBody Rederij rederij){
        return rederijService.insertRederij(rederij);
    }

    @PostConstruct()
    public void fillDB() {
        if (rederijRepository.count() == 0) {
            rederijRepository.save(new Rederij("Thomas More", "thomasmore@gmail.com", "0474848488", "2440", "Geel"));
            rederijRepository.save(new Rederij("Ruben", "ruben@gmail.com", "0474455789", "2440", "Geel"));
            rederijRepository.save(new Rederij("Turnhout", "turnhout@gmail.com", "0454486958", "2300", "Turnhout"));
        }
    }
}
