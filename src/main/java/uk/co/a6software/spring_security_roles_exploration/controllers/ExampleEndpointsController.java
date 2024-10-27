package uk.co.a6software.spring_security_roles_exploration.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleEndpointsController {

    @GetMapping("/")
    public String everyoneEndpoint()
    {
        return "everyone can see this";
    }

    @GetMapping("/only-authenticated")
    public String userEndpoint()
    {
        return "only authenticated users can see this";
    }

    @GetMapping("/only-authorised")
    public String authorisedAccessEndpoint()
    {
        return "only users with an allowed role can see this";
    }

    @GetMapping("/admin")
    public String adminEndpoint()
    {
        return "admin only";
    }
}
