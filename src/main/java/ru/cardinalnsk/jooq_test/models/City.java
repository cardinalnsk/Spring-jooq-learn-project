package ru.cardinalnsk.jooq_test.models;


import javax.management.ConstructorParameters;
import lombok.Builder;

@Builder(toBuilder = true)
public record City(
    Long id,
    Long countryId,
    String name
) {

    @ConstructorParameters({"id", "countryId", "name",})
    public City {
    }
}
