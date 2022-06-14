package rifqimuhammadaziz.springfirebase.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import rifqimuhammadaziz.springfirebase.validator.PasswordEqualConstraint;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@PasswordEqualConstraint(message = "Invalid Retype Password")
public class AppUserRequest {

    @NotEmpty(message = "Identity Number is required")
    @Size(min = 16, max = 16, message = "Identity number must be 16 digit of KTP")
    private String identityNumber;

    @NotEmpty(message = "Full Name is required")
    private String fullName;

    @Email(message = "Email is not valid")
    @NotEmpty(message = "Email is required")
    private String email;

    @NotEmpty(message = "Password is required")
    private String password;

    @NotEmpty(message = "Retype Password is required")
    private String retypePassword;
}
