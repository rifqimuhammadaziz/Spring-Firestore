package rifqimuhammadaziz.springfirebase.dto;

import lombok.Data;
import lombok.Getter;
import rifqimuhammadaziz.springfirebase.validator.PasswordEqualConstraint;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
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

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber.trim();
    }

    public void setFullName(String fullName) {
        this.fullName = fullName.trim();
    }

    public void setEmail(String email) {
        this.email = email.trim();
    }

    public void setPassword(String password) {
        this.password = password.trim();
    }

    public void setRetypePassword(String retypePassword) {
        this.retypePassword = retypePassword.trim();
    }
}
