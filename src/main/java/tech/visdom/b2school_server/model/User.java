package tech.visdom.b2school_server.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.visdom.b2school_server.dto.UserDto;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "USER_NAME"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "POINTS", nullable = false)
    private Integer points;

    @Column(name = "REGISTRATION_DATE", nullable = false)
    private LocalDate registrationDate;

    @Column(name = "LAST_PASSWORD_RESET_DATE", nullable = false)
    private LocalDate lastPasswordResetDate;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")
    )
    private List<Role> roles;

    public UserDto toDto() {

        UserDto userDto = new UserDto();
        userDto.setUserName(this.userName);
        userDto.setFirstName(this.firstName);
        userDto.setLastName(this.lastName);
        userDto.setPoints(this.points);
        return userDto;
    }

}
