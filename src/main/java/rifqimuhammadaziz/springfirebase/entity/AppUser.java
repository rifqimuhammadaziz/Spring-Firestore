package rifqimuhammadaziz.springfirebase.entity;

import com.google.cloud.Timestamp;
import lombok.Data;

@Data
public class AppUser {
    private String identityNumber;
    private String fullName;
    private String email;
    private String password;
    private Timestamp createdDate;

    // Create timestamp every add data
    public Timestamp getCreatedDate() {
        return Timestamp.now();
    }
}
