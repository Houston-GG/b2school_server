package tech.visdom.b2school_server.dto.group;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.visdom.b2school_server.model.ClassGroup;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class SampleClassGroupDto {

    private String name;

    private String city;

    private String educationalInstitution;

    private Integer classNumber;

    private String literal;

    public ClassGroup toClassGroupModel() {
        ClassGroup classGroup = new ClassGroup();
        classGroup.setName(this.name);
        classGroup.setCity(this.city);
        classGroup.setEducationalInstitution(this.educationalInstitution);
        classGroup.setClassNumber(this.classNumber);
        classGroup.setLiteral(this.literal);
        classGroup.setCreatedAt(LocalDate.now());
        return classGroup;
    }
}
