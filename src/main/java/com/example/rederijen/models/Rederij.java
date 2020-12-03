package com.example.rederijen.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity(name = "Rederij")
//@Table(name = "rederij")
public class Rederij {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rederijdID;

    private String naam;
    private String mail;
    private String telefoon;
    private String postcode;
    private String gemeente;


    public Rederij(int rederijdID, String naam, String mail, String telefoon, String postcode, String gemeente) {
        this.rederijdID = rederijdID;
        this.naam = naam;
        this.mail = mail;
        this.telefoon = telefoon;
        this.postcode = postcode;
        this.gemeente = gemeente;
    }

    public Rederij() {
    }
}
