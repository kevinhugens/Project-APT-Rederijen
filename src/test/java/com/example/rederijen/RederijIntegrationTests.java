package com.example.rederijen;

import com.example.rederijen.controller.RederijController;
import com.example.rederijen.models.Rederij;
import com.example.rederijen.repository.RederijRepository;
import com.example.rederijen.service.RederijService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = RederijController.class , excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
public class RederijIntegrationTests {

    Logger logger = Logger.getLogger(RederijIntegrationTests.class.getName());
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RederijRepository rederijRepository;

    @MockBean
    private RederijService rederijService;

    private Rederij rederij1 = new Rederij("Thomas More", "thomasmore@gmail.com", "0474848488", "2440", "Geel");
    private Rederij rederij2 = new Rederij("Ruben", "ruben@gmail.com", "0474455789", "2440", "Geel");
    private Rederij rederij3 = new Rederij("Turnhout", "turnhout@gmail.com", "0454486958", "2300", "Turnhout");

    @BeforeEach
    public void beforeAllTests() {
        rederijRepository.deleteAll();
        rederijRepository.save(rederij1);
        rederijRepository.save(rederij2);
        rederijRepository.save(rederij3);
        logger.info("AAAAAAA" + rederijRepository.findAll().toString() );
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
        logger.setLevel(Level.INFO);
        logger.info(rederijs.toString());

        mockMvc.perform(get("/rederijen/postcode/{postcode}", "2440"))
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].naam", is("Thomas More")))
                .andExpect(jsonPath("$[0].mail", is("thomasmore@gmail.com")))
                .andExpect(jsonPath("$[1].naam", is("Ruben")))
                .andExpect(jsonPath("$[1].mail", is("ruben@gmail.com")));
    }

    @Test
    public void testGetRederijByTelefoon() throws Exception {

        mockMvc.perform(get("/rederijen/telefoon/{telefoon}", "0474848488"))
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rederijdID", is(1)))
                .andExpect(jsonPath("$.telefoon", is("0474848488")))
                .andExpect(jsonPath("$.mail", is("thomasmore@gmail.com")));
    }

    @Test
    public void testGetRederijByNaam() throws Exception {

        mockMvc.perform(get("/rederijen/naam/{naam}", "Thomas More"))
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rederijdID", is(1)))
                .andExpect(jsonPath("$.telefoon", is("0474848488")))
                .andExpect(jsonPath("$.mail", is("thomasmore@gmail.com")));
    }

    @Test
    public void testPostRederij() throws Exception {

        Rederij rederij4 = new Rederij("TestRederij", "test@gmail.com", "0444444444", "2500", "TestDorp");

        mockMvc.perform(post("/rederij/insert/")
                .content(mapper.writeValueAsString(rederij4))
                .contentType("application/json"))
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.naam", is("TestRederij")))
                .andExpect(jsonPath("$.mail", is("test@gmail.com")));
    }

    @Test
    public void testDeleteRederij() throws Exception {
        mockMvc.perform(delete("/rederij/delete/{id}", 3)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
