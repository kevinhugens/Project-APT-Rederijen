package com.example.rederijen;

import com.example.rederijen.models.Rederij;
import com.example.rederijen.repository.RederijRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RederijIntegrationTests {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RederijRepository rederijRepository;

    private Rederij rederij1 = new Rederij(1, "Thomas More", "thomasmore@gmail.com", "0474848488", "2440", "Geel");
    private Rederij rederij2 = new Rederij(2, "Ruben", "ruben@gmail.com", "0474455789", "2440", "Geel");
    private Rederij rederij3 = new Rederij(3,"Turnhout", "turnhout@gmail.com", "0454486958", "2300", "Turnhout");

    @BeforeEach
    public void beforeAllTests() {
        rederijRepository.deleteAll();
        rederijRepository.save(rederij1);
        rederijRepository.save(rederij2);
        rederijRepository.save(rederij3);
    }

    @AfterEach
    public void afterAllTests() {
        rederijRepository.deleteAll();
    }

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testGetRederijenByPostcode() throws Exception {
        List<Rederij> rederijs = new ArrayList<>();
        rederijs.add(rederij1);
        rederijs.add(rederij2);
        rederijs.add(rederij3);

        mockMvc.perform(get("/rederij/postcode/{postcode}", "2440"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].rederijdID", is(1)))
                .andExpect(jsonPath("$[0].naam", is("Thomas More")))
                .andExpect(jsonPath("$[0].mail", is("thomasmore@gmail.com")))
                .andExpect(jsonPath("$[1].rederijdID", is(2)))
                .andExpect(jsonPath("$[1].naam", is("Ruben")))
                .andExpect(jsonPath("$[1].mail", is("ruben@gmail.com")));
    }

    @Test
    public void testGetRederijByTelefoon() throws Exception {

        mockMvc.perform(get("/rederij/telefoon/{telefoon}", "0474848488"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rederijdID", is(1)))
                .andExpect(jsonPath("$.telefoon", is("0474848488")))
                .andExpect(jsonPath("$.mail", is("thomasmore@gmail.com")));
    }

    @Test
    public void testPostRederij() throws Exception {
        Rederij rederij4 = new Rederij(4, "TestRederij", "test@gmail.com", "0444444444", "2500", "TestDorp");
        mockMvc.perform(post("rederij/insert/")
                .content(mapper.writeValueAsString(rederij4))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rederID", is(4)))
                .andExpect(jsonPath("$.naam", is("TestRederij")))
                .andExpect(jsonPath("$.mail", is("test@gmail.com")));
    }

    @Test
    public void testDeleteRederij() throws Exception {
        mockMvc.perform(delete("rederij/delete/{id}", 3)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
