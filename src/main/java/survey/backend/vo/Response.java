package survey.backend.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Response {

    private String token;
    private List<String> roles;

}
