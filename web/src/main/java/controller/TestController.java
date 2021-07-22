package controller;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Controller
@RequestMapping(value = "/test")
public class TestController {//todo удалить неиспользуемые объекты

    @ResponseBody
    @GetMapping
    public void getTest(ServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.write("Hello");
        writer.flush();
//todo удалить отступы
    }
}
