package ru.cardinalnsk.jooq_test.controller;

import lombok.RequiredArgsConstructor;
import org.jooq.impl.DSL;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.cardinalnsk.jooq_test.models.Country;
import ru.cardinalnsk.jooq_test.repo.impl.CountryRepository;

@RestController
@RequestMapping("/api/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryRepository countryRepository;


    @GetMapping("/{country_id}")
    public Country getOne(@PathVariable("country_id") Long countryId) {
        return countryRepository.find(countryId);
    }

    @GetMapping
    public Iterable<Country> getAll() {
        return countryRepository.findAll(DSL.noCondition());
    }

    @PostMapping
    public Country create(@RequestBody Country country) {
        return countryRepository.insert(country);
    }

    @DeleteMapping("/{country_id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable("country_id") Long countryId) {
        countryRepository.delete(countryId);
    }

    @PutMapping
    public Country update(@RequestBody Country country) {
        return countryRepository.update(country);
    }


}
