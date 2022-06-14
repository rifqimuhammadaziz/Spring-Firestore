package rifqimuhammadaziz.springfirebase.entity;

import lombok.Data;

@Data
public class AppUser {
    private String identityNumber;
    private String fullName;
    private String email;
    private String password;
}
