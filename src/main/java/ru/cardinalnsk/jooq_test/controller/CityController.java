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
import ru.cardinalnsk.jooq_test.models.City;
import ru.cardinalnsk.jooq_test.repo.impl.CityRepository;

@RestController
@RequestMapping("/api/city")
@RequiredArgsConstructor
public class CityController {

    private final CityRepository cityRepository;


    @GetMapping("/{city_id}")
    public City getOne(@PathVariable("city_id") Long cityId) {
        return cityRepository.find(cityId);
    }

    @GetMapping
    public Iterable<City> getAll() {
        return cityRepository.findAll(DSL.noCondition());
    }

    @PostMapping
    public City create(@RequestBody City city) {
        return cityRepository.insert(city);
    }

    @DeleteMapping("/{city_id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable("city_id") Long cityId) {
        cityRepository.delete(cityId);
    }

    @PutMapping
    public City update(@RequestBody City city) {
        return cityRepository.update(city);
    }
}
