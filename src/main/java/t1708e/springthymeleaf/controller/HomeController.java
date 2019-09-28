package t1708e.springthymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import t1708e.springthymeleaf.entity.Account;
import t1708e.springthymeleaf.entity.Role;
import t1708e.springthymeleaf.repository.AccountRepository;
import t1708e.springthymeleaf.repository.RoleRepository;

import java.util.HashMap;
import java.util.HashSet;

@Controller
@RequestMapping(value = "/")
public class HomeController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {


        Role role = new Role();
        role.setName("ADMIN");

        for (Account account :
                accountRepository.findAll()) {
            role.addAccount(account);
        }

        roleRepository.save(role);

        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String login() {
        return "login";
    }
}
