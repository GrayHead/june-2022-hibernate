package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Sunglass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String brand;

    public Sunglass(String brand) {
        this.brand = brand;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_sg",
            joinColumns = @JoinColumn(name = "sg_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;
}
