package org.test.log4jvuln.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/**")
public class LoggingController {

    static Logger log = LogManager.getLogger(LoggingController.class.getName());
    @GetMapping("/logme")
    public String logMe(@RequestParam String input) {
        log.info(input);
        return "Logged this -> " + input;
    }
}
