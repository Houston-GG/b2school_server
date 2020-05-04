package tech.visdom.b2school_server.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.visdom.b2school_server.dto.group.ClassGroupDto;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
@Entity
@Table(name = "class_group")
public class ClassGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "CITY", nullable = false)
    private String city;

    @Column(name = "EDUCATIONAL_INSTITUTION", nullable = false)
    private String educationalInstitution;

    @Column(name = "CLASS_NUMBER", nullable = false)
    private Integer classNumber;

    @Column(name = "LITERAL", nullable = false)
    private String literal;

    @Column(name = "CREATOR", nullable = false)
    private Long creator;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDate createdAt;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "user_group",
            joinColumns = @JoinColumn(name = "GROUP_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    )
    private List<User> users;

    public ClassGroupDto toDto() {
        ClassGroupDto classGroupDto = new ClassGroupDto();
        classGroupDto.setId(this.id);
        classGroupDto.setName(this.name);
        classGroupDto.setCity(this.city);
        classGroupDto.setEducationalInstitution(this.educationalInstitution);
        classGroupDto.setClassNumber(this.classNumber);
        classGroupDto.setLiteral(this.literal);
        classGroupDto.setCreator(this.creator);
        classGroupDto.setCreatedAt(this.createdAt);
        classGroupDto.setUsers(this.users.stream().map(User::toDto).collect(Collectors.toList()));
        return classGroupDto;
    }
}
