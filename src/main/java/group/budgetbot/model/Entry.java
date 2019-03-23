package group.budgetbot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity
@Table(name = "entries")
public class Entry extends AuditModel {
    @Id
    @GeneratedValue(generator = "entry_generator")
    @SequenceGenerator(
            name = "entry_generator",
            sequenceName = "entry_sequence",
            initialValue = 1000
    )
    private Long entryId;

    private String label;
    private Float value;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    // what does this mean?
    @JsonIgnore
    private User user;

    // Getters and Setters (Omitted for brevity) - with lombok

}