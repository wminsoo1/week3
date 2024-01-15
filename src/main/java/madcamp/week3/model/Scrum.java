package madcamp.week3.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Scrum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scrumId;

    private String scrumTitle;

    private String scrumDescription;

    private LocalDate scrumDate;

    @ManyToOne
    @JoinColumn(name = "projectId")
    private Project project;

    public Scrum() {

    }

}
