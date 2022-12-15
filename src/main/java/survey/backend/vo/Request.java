package survey.backend.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Request {

    private String userName;
    private String userPwd;
    private List<String> roles;

}
