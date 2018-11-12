package KoffieRESTServer.response;

public class Reply {
    private Status status;
    private String message;

    public Status getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Reply(Status status, String message) {
        this.status = status;
        this.message = message;
    }
}
