package ca.sheridancollege.damor.Lab1NickDamor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Handles root URL
    @GetMapping("/")
    public String index() {
        return "index"; // Refers to index.html
    }
}