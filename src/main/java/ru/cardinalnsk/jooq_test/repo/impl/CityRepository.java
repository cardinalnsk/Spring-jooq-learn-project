package ru.cardinalnsk.jooq_test.repo.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.cardinalnsk.jooq_test.models.City;
import ru.cardinalnsk.jooq_test.models.tables.Cities;
import ru.cardinalnsk.jooq_test.repo.CrudRepository;
import ru.cardinalnsk.jooq_test.repo.mapper.CityRecordMapper;

@Repository
@RequiredArgsConstructor
public class CityRepository implements CrudRepository<City> {

    private final DSLContext dsl;
    private final CityRecordMapper cityRecordMapper;

    @Override
    public City insert(City entity) {
        return dsl.insertInto(Cities.CITIES)
            .set(dsl.newRecord(Cities.CITIES, entity))
            .returning()
            .fetchOne()
            .into(City.class);
    }

    @Override
    public City find(Long id) {
        return dsl.selectFrom(Cities.CITIES)
            .where(Cities.CITIES.ID.eq(id))
            .fetchAny()
            .into(City.class);
    }

    @Override
    public City update(City entity) {
        return dsl.update(Cities.CITIES)
            .set(dsl.newRecord(Cities.CITIES, entity))
            .where(Cities.CITIES.ID.eq(entity.id()))
            .returning()
            .fetchOne()
            .into(City.class);
    }

    @Override
    public List<City> findAll(Condition condition) {
        return dsl.selectFrom(Cities.CITIES)
            .where(condition)
            .fetch()
            .map(cityRecordMapper);
    }

    @Override
    public Boolean delete(Long id) {
        return dsl.deleteFrom(Cities.CITIES)
            .where(Cities.CITIES.ID.eq(id))
            .execute() == SUCCESS_CODE;
    }
}
