package de.thd.issue.rental;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface IRentalRepository extends CrudRepository<Rental, Long> {

    @Query(value = "select r.returndate as returnDate from rental r where r.returndate >= ?1 order by r.returndate asc limit 1", nativeQuery = true)
    RentalProjection findsMostRecentReturnDateAndTimeRankByLaboratory(LocalDate localDate);

    @Query(value = "select r.returndate as returnDateNative from rental r where r.returndate >= ?1 order by r.returndate asc limit 1", nativeQuery = true)
    RentalProjection findsMostRecentReturnDateNativeAndTimeRankByLaboratory(LocalDate localDate);

}
