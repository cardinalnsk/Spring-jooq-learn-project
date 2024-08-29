package ru.cardinalnsk.jooq_test.repo.mapper;

import lombok.RequiredArgsConstructor;
import org.jooq.RecordMapper;
import org.springframework.stereotype.Component;
import ru.cardinalnsk.jooq_test.models.City;
import ru.cardinalnsk.jooq_test.models.tables.records.CitiesRecord;

@RequiredArgsConstructor
@Component
public class CityRecordMapper implements RecordMapper<CitiesRecord, City> {

    @Override
    public City map(CitiesRecord record) {
        return City.builder()
            .id(record.getId())
            .countryId(record.getCountryId())
            .name(record.getName())
            .build();
    }
}
