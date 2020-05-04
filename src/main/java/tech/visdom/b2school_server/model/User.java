package tech.visdom.b2school_server.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.visdom.b2school_server.dto.ExtendedUserDto;
import tech.visdom.b2school_server.dto.UserDto;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

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
    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "user_group",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "GROUP_ID", referencedColumnName = "ID")
    )
    private Set<ClassGroup> classGroups;

    public UserDto toDto() {

        UserDto userDto = new UserDto();
        userDto.setId(this.id);
        userDto.setUserName(this.userName);
        userDto.setFirstName(this.firstName);
        userDto.setLastName(this.lastName);
        userDto.setPoints(this.points);
        return userDto;
    }

    public ExtendedUserDto toExtendedUserDto() {

        ExtendedUserDto extendedUserDto = new ExtendedUserDto();
        extendedUserDto.setId(this.id);
        extendedUserDto.setUserName(this.userName);
        extendedUserDto.setFirstName(this.firstName);
        extendedUserDto.setLastName(this.lastName);
        extendedUserDto.setPoints(this.points);
        extendedUserDto.setClassGroupsDto(this.classGroups.stream().map(ClassGroup::toDto).collect(Collectors.toList()));
        return extendedUserDto;
    }
}
