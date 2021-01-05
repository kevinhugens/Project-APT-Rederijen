package com.example.rederijen.models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Data
public class Rederij {
    @Id
    private String id;

    private String naam;
    private String mail;
    private String telefoon;
    private String postcode;
    private String gemeente;


    public Rederij(String naam, String mail, String telefoon, String postcode, String gemeente) {
        this.naam = naam;
        this.mail = mail;
        this.telefoon = telefoon;
        this.postcode = postcode;
        this.gemeente = gemeente;
    }

    public Rederij() {
    }
}
