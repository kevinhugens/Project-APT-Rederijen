package com.example.rederijen.controller;

import com.example.rederijen.models.Rederij;
import com.example.rederijen.service.RederijService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RederijController {


    @Autowired
    private RederijService rederijService;

    @GetMapping("/rederij/{id}")
    public Rederij getRederijByID(@PathVariable int id){
        //logging
        return rederijService.getRederijById(id);
    }

    @GetMapping("/rederij/postcode/{postcode}")
    public List<Rederij> getRederijenByPostcode(@PathVariable String postcode){
        //logging
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

    @PostMapping("rederij/insert/")
    public Rederij insertRederij(@RequestBody Rederij rederij){
        return rederijService.insertRederij(rederij);
    }
}
