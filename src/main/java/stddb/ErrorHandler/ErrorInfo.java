package stddb.ErrorHandler;

import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

public class ErrorInfo {
    private Timestamp timestamp;
    private HttpStatus status;
    private String error;
    private String message;
    private String path;

    //constructors
    public ErrorInfo() {
    }

    public ErrorInfo(HttpStatus status, String error, String message, String path) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    //getter setters

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
