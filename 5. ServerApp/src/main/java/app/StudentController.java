package app;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class StudentController {

    private static AtomicLong counter = new AtomicLong();

    @RequestMapping("/student")
    public Student student(@RequestParam(value = "first_name", defaultValue = "Someone") String firstName,
                           @RequestParam(value = "last_name", defaultValue = " Awesome") String lastName) {
        return new Student(counter.incrementAndGet(), firstName, lastName);
    }
}
