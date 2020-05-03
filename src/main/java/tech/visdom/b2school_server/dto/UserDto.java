package tech.visdom.b2school_server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import tech.visdom.b2school_server.model.User;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@Data
@ToString
public class UserDto {

    @NotNull
    private String userName;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private Integer points;

    public User toModel() {
        User user = new User();
        user.setUserName(this.userName);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setPoints(0);
        user.setRegistrationDate(LocalDate.now());
        user.setLastPasswordResetDate(LocalDate.now());
        return user;
    }
}
