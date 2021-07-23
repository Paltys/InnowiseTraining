package controller;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.time.Instant;
import java.time.ZonedDateTime;

@Controller
@RequestMapping(value = "/")
public class SystemController {

    private final static String TIME_PATTERN = "d'days' HH'hrs' mm'mins' ss'sec'";

    private final Instant localDateTime = Instant.now();

    @ResponseBody
    @GetMapping
    public void getTest(ServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.write("Server start time " + localDateTime);
        //todo можно приставить как html тэги, например <div>text</div>
        writer.write("\nServer work time " + DurationFormatUtils.formatDuration(
                ManagementFactory.getRuntimeMXBean().getUptime(), TIME_PATTERN, true));
       writer.write("\nCurrent time " + ZonedDateTime.now());
       writer.flush();
    }
}
