package com.example.rederijen.models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Data
@Document(collection = "Rederij")
public class Rederij {


    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
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
