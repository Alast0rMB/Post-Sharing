package example.website.Exception;

//Resource not found Exception
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L; //Used for checking the correct version
    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}