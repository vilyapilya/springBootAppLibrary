package pack.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userController {

    @RequestMapping(value="/add/user", method = RequestMethod.POST)
    public String createUser(String userFirstName, String userLastName) {

    }
}
