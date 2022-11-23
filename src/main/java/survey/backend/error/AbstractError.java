package survey.backend.error;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class AbstractError extends RuntimeException{

    private Set <AbstractSubError> details = new HashSet<>();

}
