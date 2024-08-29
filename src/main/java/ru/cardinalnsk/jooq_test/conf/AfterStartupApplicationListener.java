package ru.cardinalnsk.jooq_test.conf;

import lombok.SneakyThrows;
import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.Configuration;
import org.jooq.meta.jaxb.Database;
import org.jooq.meta.jaxb.Generator;
import org.jooq.meta.jaxb.Jdbc;
import org.jooq.meta.jaxb.Target;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class AfterStartupApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${jooq.generator.database.name}")
    private String databaseName;

    @Value("${jooq.generator.database.with-includes}")
    private String databaseWithIncludes;

    @Value("${jooq.generator.database.with-input-schema}")
    private String databaseWithInputSchema;

    @Value("${jooq.generator.target.package-name}")
    private String targetPackageName;

    @Value("${jooq.generator.target.directory}")
    private String targetDirectory;

    @SneakyThrows
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        new GenerationTool().run(configureGenerator());
    }

    private Configuration configureGenerator() {
        return new Configuration()
            .withJdbc(
                new Jdbc()
                    .withDriver(driver)
                    .withUrl(url)
                    .withUsername(username)
                    .withPassword(password))
            .withGenerator(new Generator()
                .withDatabase(new Database()
                    .withName(databaseName)
                    .withIncludes(databaseWithIncludes)
                    .withExcludes("flyway_schema_history")
                    .withInputSchema(databaseWithInputSchema))
                .withTarget(new Target()
                    .withPackageName(targetPackageName)
                    .withDirectory(targetDirectory))
            );
    }
}
