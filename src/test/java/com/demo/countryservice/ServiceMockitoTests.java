package com.demo.countryservice;

import beans.Country;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repositories.CountryRepository;
import services.CountryService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceMockitoTests {
    @Mock //simulated objects (classes or interfaces)
    CountryRepository countryRepository;

    @InjectMocks //instance of the class that I will create
    CountryService countryService;

    @Test
    @Order(1)
    public void test_getAllCountries() {
        Country country = new Country();

        List<Country> myCountries = new ArrayList<Country>();
        myCountries.add(new Country(1,"india","delhi"));
        myCountries.add(new Country(2,"USA","Washington"));

        when(countryRepository.findAll()).thenReturn(myCountries);
        assertEquals(2,countryService.getAllCountries().size());
    }
}
