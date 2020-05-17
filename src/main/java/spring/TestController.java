package spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author linckye
 */
@RestController
public class TestController {

    @PostMapping("xxxxxx")
    public String test(
            HttpSession session,
            @RequestParam(value = "x", defaultValue = "1") int x) {
        return String.valueOf(x);
    }

}
