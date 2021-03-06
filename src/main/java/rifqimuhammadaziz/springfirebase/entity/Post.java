package rifqimuhammadaziz.springfirebase.entity;

import com.google.cloud.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Post {
    private String postId;
    private String title;
    private Category category;
    private String body;
    private Timestamp createdDate;

    // Create timestamp every add data
    public Timestamp getCreatedDate() {
        return Timestamp.now();
    }
}
