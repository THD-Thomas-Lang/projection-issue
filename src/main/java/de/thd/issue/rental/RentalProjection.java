package de.thd.issue.rental;

import java.time.LocalDate;

public interface RentalProjection {
    LocalDate getReturnDate();

    String getReturnDateNative();

}
