package tech.visdom.b2school_server.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.visdom.b2school_server.dto.group.ClassGroupDto;

import java.util.List;

@NoArgsConstructor
@Data
public class ExtendedUserDto {

    private Long id;

    private String userName;

    private String firstName;

    private String lastName;

    private Integer points;

    private List<ClassGroupDto> classGroupsDto;
}
