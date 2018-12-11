package de.thd.issue.rental;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "rental", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rental implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @NotNull
    private long rentalCartId;

    @Column(nullable = false)
    @NotNull
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate returnDate;
}
