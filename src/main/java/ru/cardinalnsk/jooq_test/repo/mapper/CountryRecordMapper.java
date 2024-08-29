package ru.cardinalnsk.jooq_test.repo.mapper;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jooq.RecordMapper;
import org.springframework.stereotype.Component;
import ru.cardinalnsk.jooq_test.models.City;
import ru.cardinalnsk.jooq_test.models.Country;
import ru.cardinalnsk.jooq_test.models.tables.Cities;
import ru.cardinalnsk.jooq_test.models.tables.records.CountriesRecord;
import ru.cardinalnsk.jooq_test.models.type.GovernmentForm;
import ru.cardinalnsk.jooq_test.repo.impl.CityRepository;

@RequiredArgsConstructor
@Component
public class CountryRecordMapper implements RecordMapper<CountriesRecord, Country> {

    private final CityRepository cityRepository;

    @Override
    public Country map(CountriesRecord record) {
        List<City> all = cityRepository.findAll(Cities.CITIES.COUNTRY_ID.eq(record.getId()));
        return Country.builder()
            .cities(all)
            .id(record.getId())
            .name(record.getName())
            .population(record.getPopulation())
            .governmentForm(GovernmentForm.valueOf(record.getGovernmentForm()))
            .build();
    }
}
