package com.demo.countryservice;

import beans.Country;
import controllers.CountryController;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import services.CountryService;

import java.util.List;

// ADD THIS LINE:
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {ControllerMockMvcTests.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ComponentScan(basePackages = "com.countryservice.demo") //scan for @components, @Service, @Repository , @Controller
@AutoConfigureMockMvc

public class ControllerMockMvcTests {
    @Autowired
    MockMvc mockMvc;

    @Mock
    CountryService countryService;

    @InjectMocks
    CountryController countryController;

    public List<Country> muCountries;
    Country country;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(countryController).build();
    }


    @Test
    @Order(2)
    public void test_getCountryByID() throws Exception {
        country = new Country(1, "India", "Delhi");

        int countryID=1;
        when(countryService.getCountryById(countryID)).thenReturn(country);//Mocking

        this.mockMvc.perform(get("/getcountries/{id}", countryID))
                .andExpect(status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idCountry").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("India"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.capital").value("Delhi"))
                .andDo(print());
    }

}
