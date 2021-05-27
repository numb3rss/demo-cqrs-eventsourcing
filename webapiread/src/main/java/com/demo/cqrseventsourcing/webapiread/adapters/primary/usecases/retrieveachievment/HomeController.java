package com.demo.cqrseventsourcing.webapiread.adapters.primary.usecases.retrieveachievment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController
{
    @GetMapping("/")
    public String create()
    {
        return "Read Side";
    }
}
