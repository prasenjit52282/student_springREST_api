package stddb.ErrorHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody ErrorInfo handle_no_such_element_exception(HttpServletRequest req, Exception ex){
        return new ErrorInfo(HttpStatus.BAD_REQUEST,"Internal Server Error",ex.getMessage(),req.getRequestURI());
    }
}
