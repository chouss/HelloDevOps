package com.demo.countryservice;

import beans.Country;
import controllers.CountryController;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import services.CountryService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ControllerMockitoTests {
    @Mock
    CountryService countryService;

    @InjectMocks
    CountryController countryController;

    public List<Country> myCountries = new ArrayList<Country>();
    Country country;

    @Test
    @Order(1)
    @ExtendWith(MockitoExtension.class)
    public void test_getAllCountries() {
        myCountries.add(new Country(1,"india","delhi"));
        myCountries.add(new Country(2,"USA","Washington"));

        when(countryService.getAllCountries()).thenReturn(myCountries);// Mocking Service
        ResponseEntity<List<Country>> results= countryController.getCountries();

        assertEquals(HttpStatus.FOUND,results.getStatusCode());
        assertEquals(2, results.getBody().size());
    }
}
