package pl.pjatk.jazs22446nbp.golder.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.net.ConnectException;
import java.time.format.DateTimeParseException;

@RestControllerAdvice
public class GolderAdvice {
    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<String> handleInternalServerError(HttpServerErrorException.InternalServerError exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Exception occurred on request. Exception message: " + exception.getLocalizedMessage());
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<String> handleDateTimeParseException(DateTimeParseException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Exception occurred on request. Exception message: " + exception.getLocalizedMessage());
    }

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<String> handleGolderRest404(HttpClientErrorException.NotFound exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Exception occurred on request. Exception message: " + exception.getLocalizedMessage());
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity<String> handleGolderRest400(HttpClientErrorException.BadRequest exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Exception occurred on request. Exception message: " + exception.getLocalizedMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Exception occurred on request. Exception message: " + exception.getLocalizedMessage());
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<String> handleConnectException(ConnectException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Exception occurred on request. Exception message: " + exception.getLocalizedMessage());
    }
}
