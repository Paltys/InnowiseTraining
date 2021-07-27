package dto;
import lombok.Data;

@Data
public class ValidationErrorResponse {

    private List<Violation> violations = new ArrayList<>();
}
