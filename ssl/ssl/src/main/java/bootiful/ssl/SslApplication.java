package bootiful.ssl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Map;

@Controller
@ResponseBody
@SpringBootApplication
public class SslApplication {

    public static void main(String[] args) {
        SpringApplication.run(SslApplication.class, args);
    }

    @GetMapping("/hello")
    Map<String, String> hello(Principal principal) {
        return Map.of("message", "Hello, " + principal.getName() + "!");
    }
}

