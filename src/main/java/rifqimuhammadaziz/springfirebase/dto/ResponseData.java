package rifqimuhammadaziz.springfirebase.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseData<T> {
    private boolean status;
    private List<String> messages = new ArrayList<>();
    private T data;
}
