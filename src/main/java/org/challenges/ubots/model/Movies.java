package org.challenges.ubots.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.challenges.ubots.dto.MoviesDto;

@Entity
@Table(name = "movies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGSERIAL", updatable = false)
    private long id;

    @Column(name = "title", nullable = false)
    @Size(max = 50, message = "O título não deve ultrapassar 50 caracteres")
    private String title;

    @Column(name = "synopsis", nullable = false)
    @Size(max = 200, message = "a sinopse do filme não deve ultrapassar 200 caracteres")
    private String synopsis;

    @Column(name = "date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(name = "rating")
    @OneToMany
    @JoinTable(name = "movies_rating",
            joinColumns = @JoinColumn(name = "movies_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "rating_id", referencedColumnName = "id"))
    private List<Rating> rating;

    public boolean isRated(){
        return !rating.isEmpty();
    }


    public MoviesDto convertToDto() {
        return MoviesDto.builder()
                .id(this.id)
                .title(this.title)
                .synopsis(this.synopsis)
                .date(this.date)
                .build();
    }
}
