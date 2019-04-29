package app;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Student {

    private final long id;
    private final String first_name;
    private final String last_name;
}
