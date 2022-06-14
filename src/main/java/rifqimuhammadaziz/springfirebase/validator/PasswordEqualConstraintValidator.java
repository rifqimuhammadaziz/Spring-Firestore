package rifqimuhammadaziz.springfirebase.validator;

import rifqimuhammadaziz.springfirebase.dto.AppUserRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordEqualConstraintValidator implements ConstraintValidator<PasswordEqualConstraint, Object> {

    @Override
    public boolean isValid(Object data, ConstraintValidatorContext constraintValidatorContext) {
        AppUserRequest userRequest = (AppUserRequest) data;
        return userRequest.getPassword().equals(userRequest.getRetypePassword());
    }
}
