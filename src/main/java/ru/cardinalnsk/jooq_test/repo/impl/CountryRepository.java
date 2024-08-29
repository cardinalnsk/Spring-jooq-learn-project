package ru.cardinalnsk.jooq_test.repo.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.exception.DataAccessException;
import org.springframework.stereotype.Repository;
import ru.cardinalnsk.jooq_test.models.Country;
import ru.cardinalnsk.jooq_test.models.tables.Countries;
import ru.cardinalnsk.jooq_test.models.tables.records.CountriesRecord;
import ru.cardinalnsk.jooq_test.repo.CrudRepository;
import ru.cardinalnsk.jooq_test.repo.mapper.CountryRecordMapper;

@Repository
@RequiredArgsConstructor
public class CountryRepository implements CrudRepository<Country> {

    private final DSLContext dsl;
    private final CountryRecordMapper countryRecordMapper;

    @Override
    public Country insert(Country entity) {
        return dsl.insertInto(Countries.COUNTRIES)
            .set(dsl.newRecord(Countries.COUNTRIES, entity))
            .returning()
            .fetchOptional()
            .orElseThrow(() -> new DataAccessException("Error inserting entity: " + entity))
            .into(Country.class);
    }

    @Override
    public Country find(Long id) {
        return dsl.selectFrom(Countries.COUNTRIES)
            .where(Countries.COUNTRIES.ID.eq(id))
            .fetchAny()
            .map(r -> countryRecordMapper.map((CountriesRecord) r));
    }

    @Override
    public Country update(Country entity) {
        return dsl.update(Countries.COUNTRIES)
            .set(dsl.newRecord(Countries.COUNTRIES, entity))
            .where(Countries.COUNTRIES.ID.eq(entity.id()))
            .returning()
            .fetchOptional()
            .orElseThrow(() -> new DataAccessException("Error updating entity: " + entity))
            .into(Country.class);
    }

    @Override
    public List<Country> findAll(Condition condition) {
        return dsl.selectFrom(Countries.COUNTRIES)
            .where(condition)
            .fetch()
            .map(countryRecordMapper);
    }

    @Override
    public Boolean delete(Long id) {
        return dsl.deleteFrom(Countries.COUNTRIES)
            .where(Countries.COUNTRIES.ID.eq(id))
            .execute() == SUCCESS_CODE;
    }
}
