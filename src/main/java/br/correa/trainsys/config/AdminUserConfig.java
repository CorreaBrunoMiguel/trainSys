package br.correa.trainsys.config;

import br.correa.trainsys.entities.Role;
import br.correa.trainsys.entities.User;
import br.correa.trainsys.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

@Configuration
@Transactional
public class AdminUserConfig implements CommandLineRunner {


    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminUserConfig(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var userAdmin = userRepository.findByEmail("admin@admin");

        if (userAdmin.isPresent() && userAdmin.get().getRole().equals(Role.ADMIN)){
            System.out.println("Admin já existe");System.out.println(String.format("Role: %s", userAdmin.get().getRole()));
        } else {
            var user = new User();
            user.setName("admin");
            user.setEmail("admin@admin");
            user.setDate_birth(LocalDate.now());
            user.setCpf("000.000.000.00");
            user.setPassword(passwordEncoder.encode("1234"));
            user.setRole(Role.ADMIN);
            userRepository.save(user);
        }
    }

}
