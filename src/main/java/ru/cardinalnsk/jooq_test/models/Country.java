package ru.cardinalnsk.jooq_test.models;

import java.beans.ConstructorProperties;
import java.util.List;
import lombok.Builder;
import ru.cardinalnsk.jooq_test.models.type.GovernmentForm;

@Builder(toBuilder = true)
public record Country(
    Long id,
    String name,
    GovernmentForm governmentForm,
    Integer population,
    List<City> cities
) {

    @ConstructorProperties({"id", "name", "governmentForm", "population", "cities"})
    public Country {
    }
}
