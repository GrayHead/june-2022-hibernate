package models;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
//@EqualsAndHashCode
@Table(name = "user_table")
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(
            name = "secondName",
            unique = false,
            nullable = true,
            insertable = true,
            updatable = true,
            length = 250
    )
    private String surname;

    @OneToOne(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    @JoinColumn(name = "passport_id",
            referencedColumnName = "id")
    private Passport passport;

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, String surname, Passport passport) {
        this.name = name;
        this.surname = surname;
        this.passport = passport;
    }
}
