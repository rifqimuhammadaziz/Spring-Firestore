package rifqimuhammadaziz.springfirebase.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Post {
    private String postId;
    private String title;
    private String category;
    private String body;
}
