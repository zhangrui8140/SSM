package core.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Spliter Not Found")
public class SpliterNotFoundEx extends RuntimeException {
}
