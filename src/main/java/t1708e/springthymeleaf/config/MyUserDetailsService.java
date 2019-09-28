package t1708e.springthymeleaf.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import t1708e.springthymeleaf.entity.Account;
import t1708e.springthymeleaf.entity.Role;
import t1708e.springthymeleaf.repository.AccountRepository;

import java.util.stream.Collectors;

public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findById(username).orElse(null);
        if (account == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        System.out.println(account.getRoles().stream().map(x -> x.getName()).toArray());
        UserDetails user =
                User.builder()
                        .username(account.getEmail())
                        .password(account.getPassword())
                        .roles(account.getRoles().stream().map(x -> x.getName()).toArray().toString())
                        .build();
        return user;
    }
}
