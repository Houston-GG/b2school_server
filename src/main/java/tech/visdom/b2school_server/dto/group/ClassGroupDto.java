package tech.visdom.b2school_server.dto.group;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.visdom.b2school_server.dto.UserDto;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Data
public class ClassGroupDto {

    private Long id;

    private String name;

    private String city;

    private String educationalInstitution;

    private Integer classNumber;

    private String literal;

    private Long creator;

    private LocalDate createdAt;

    private List<UserDto> users;
}
