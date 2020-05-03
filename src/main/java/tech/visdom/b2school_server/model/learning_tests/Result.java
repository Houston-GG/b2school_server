package tech.visdom.b2school_server.model.learning_tests;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.visdom.b2school_server.dto.learning_tests.ResultDto;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "result")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name = "EXERCISE", nullable = false)
    private String exercise;

    @Column(name = "ANSWER", nullable = false)
    private String answer;

    @Column(name = "IS_CORRECT", nullable = false)
    private Boolean is_correct;

    @ManyToOne
    @JoinColumn(name="USER_LEVEL_ID", nullable=false)
    private UserLevel userLevel;

    public Result(String exercise, String answer, Boolean is_correct, UserLevel userLevel) {
        this.exercise = exercise;
        this.answer = answer;
        this.is_correct = is_correct;
        this.userLevel = userLevel;
    }

    public ResultDto toDto() {
        ResultDto resultDto = new ResultDto();
        resultDto.setExercise(this.exercise);
        resultDto.setAnswer(this.answer);
        resultDto.setIs_correct(is_correct);
        return resultDto;
    }
}
