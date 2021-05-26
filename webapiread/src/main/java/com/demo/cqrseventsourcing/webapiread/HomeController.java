package com.demo.cqrseventsourcing.webapiread;

import com.demo.cqrseventsourcing.cqrslibrary.MyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController
{
    private final MyService myService;

    public HomeController(MyService myService)
    {
        this.myService = myService;
    }

    @GetMapping("/")
    public String create()
    {
        return myService.message();
    }
}
