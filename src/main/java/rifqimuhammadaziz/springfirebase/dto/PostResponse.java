package rifqimuhammadaziz.springfirebase.dto;

import com.google.cloud.Timestamp;
import lombok.Getter;
import lombok.Setter;
import rifqimuhammadaziz.springfirebase.entity.Category;

import java.util.HashMap;

@Getter
@Setter
public class PostResponse {
    private String postId;
    private String title;
    private Category category;
    private String body;
    private Timestamp createdDate;
}
