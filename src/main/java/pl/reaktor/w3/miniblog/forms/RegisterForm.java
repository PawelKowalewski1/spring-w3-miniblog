package pl.reaktor.w3.miniblog.forms;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter @Getter
@ToString(exclude = {"password", "password2"})
public class RegisterForm {

    @NotEmpty
    private String email;

    private String password;

    private String password2;
    @NotBlank
    private String firstName;
    @Size(min =5, max = 10, message ="Nazwisko musi mieć minimum 5 a maksimum 10 znakuf")
    private String lastName;
}
