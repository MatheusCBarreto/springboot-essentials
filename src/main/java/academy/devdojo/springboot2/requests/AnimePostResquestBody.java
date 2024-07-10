package academy.devdojo.springboot2.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class AnimePostResquestBody {
    @NotEmpty(message = "The anime name cannot be empty.")
    @NotNull(message = "The anime name cannot be null.")
    private String name;
}
