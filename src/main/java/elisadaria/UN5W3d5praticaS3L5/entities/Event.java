package elisadaria.UN5W3d5praticaS3L5.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Data
@Entity
@Table(name="events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private LocalDate date;
    private String location;
    private int NumberOfPeople;
    @ManyToOne
    @JoinColumn
    private User user;
}
