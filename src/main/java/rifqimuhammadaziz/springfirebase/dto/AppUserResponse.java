package rifqimuhammadaziz.springfirebase.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AppUserResponse {
    private String identityNumber;
    private String fullName;
    private String email;
}
