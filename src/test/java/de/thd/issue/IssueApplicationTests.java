package de.thd.issue;

import de.thd.issue.rental.IRentalRepository;
import de.thd.issue.rental.RentalProjection;
import lombok.extern.slf4j.Slf4j;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import java.time.Duration;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = {IssueApplicationTests.Initializer.class})
@Slf4j
public class IssueApplicationTests {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer =
            (PostgreSQLContainer) new PostgreSQLContainer()
                    .withInitScript("schema.sql")
                    .withUsername("sampleuser")
                    .withPassword("samplepwd")
                    .withStartupTimeout(Duration.ofSeconds(600));

    @Autowired
    public IRentalRepository rentalRepository;

    @Test
    public void contextLoads() {
        assertThat(rentalRepository).isNotNull();
    }

    @Test
    public void issueShowCaseFailing() {
        assertThat(rentalRepository).isNotNull();
        RentalProjection rentalProjection = rentalRepository.findsMostRecentReturnDateAndTimeRankByLaboratory(LocalDate.now());
        assertThat(rentalProjection).isNotNull();
        assertThat(rentalProjection.getReturnDate()).isNotNull();
        assertThat(rentalProjection.getReturnDate()).isAfter(LocalDate.now());
    }

    @Test
    public void issueShowCaseSuceeding() {
        assertThat(rentalRepository).isNotNull();
        RentalProjection rentalProjection = rentalRepository.findsMostRecentReturnDateNativeAndTimeRankByLaboratory(LocalDate.now());
        assertThat(rentalProjection).isNotNull();
        assertThat(rentalProjection.getReturnDateNative()).isNotNull();
        assertThat(rentalProjection.getReturnDateNative()).isNotEmpty();
        log.error(rentalProjection.getReturnDateNative());
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}
