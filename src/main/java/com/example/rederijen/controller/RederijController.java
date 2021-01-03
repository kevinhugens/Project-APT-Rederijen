package com.example.rederijen.controller;

import com.example.rederijen.models.Rederij;
import com.example.rederijen.repository.RederijRepository;
import com.example.rederijen.service.RederijService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class RederijController {

    Logger logger = Logger.getLogger(RederijController.class.getName());

    private RederijService rederijService;
    private RederijRepository rederijRepository;

    public RederijController(RederijService rederijService, RederijRepository rederijRepository) {
        this.rederijService = rederijService;
        this.rederijRepository = rederijRepository;
    }

    @GetMapping("/rederij/{id}")
    public Rederij getRederijByID(@PathVariable int id){
        //logging
        return rederijService.getRederijById(id);
    }

    @GetMapping("/containers")
    public List<Rederij> getAllContainers() {
        return rederijService.getAllRederijen();
    }

    @GetMapping("/rederij/{naam}")
    public Rederij getRederijByNaam(@PathVariable String naam) {
        //logging
        return rederijService.getRederijByNaam(naam);
    }

    @GetMapping("/rederij/postcode/{postcode}")
    public List<Rederij> getRederijenByPostcode(@PathVariable String postcode){
        //logging
        logger.setLevel(Level.INFO);
        logger.info(rederijRepository.findAll().toString());
        return rederijService.getRederijenByPostcode(postcode);
    }

    @GetMapping("/rederij/telefoon/{telefoon}")
    public Rederij getRederijByTelefoon(@PathVariable String telefoon){
        //logging
        return rederijService.getRederijByTelefoon(telefoon);
    }

    @DeleteMapping("rederij/delete/{id}")
    public void deleteRederijByID(@PathVariable int id){
        //logging
        rederijService.deleteRederij(id);
    }

    @PutMapping("rederij/update")
    public Rederij updateRederij(@RequestBody Rederij rederij) {
        Rederij retrievedRederij;

        retrievedRederij = rederijService.updateRederij(rederij);
        return retrievedRederij;
    }

    @PostMapping("/rederij")
    public Rederij insertRederij(@RequestBody Rederij rederij){
        return rederijService.insertRederij(rederij);
    }

    @PostConstruct()
    public void fillDB() {
        if (rederijRepository.count() == 0) {
            rederijRepository.save(new Rederij(1, "Thomas More", "thomasmore@gmail.com", "0474848488", "2440", "Geel"));
            rederijRepository.save(new Rederij(2, "Ruben", "ruben@gmail.com", "0474455789", "2440", "Geel"));
            rederijRepository.save(new Rederij(3,"Turnhout", "turnhout@gmail.com", "0454486958", "2300", "Turnhout"));
        }
    }
}
