package exceptions;

import lombok.Data;

@Data
public class ViolationErrorCustom extends Exception{
    private String message;

    public ViolationErrorCustom(String message) {
        super(message);
        this.message = message;
    }
}
