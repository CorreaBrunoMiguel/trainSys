package br.correa.trainsys.services;

import br.correa.trainsys.controllers.dto.LoginRequest;
import br.correa.trainsys.entities.User;
import br.correa.trainsys.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    private UserRepository repo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public boolean loginCorrect(LoginRequest loginRequest){
        var user = repo.findByEmail(loginRequest.email());

        if (user.isEmpty()){
            throw new BadCredentialsException(
                    "Usuário não cadastrado."
            );
        }
        var password = user.get().getPassword();

        boolean match = passwordEncoder.matches(
                loginRequest.password(),
                password
        );

        if (!match){
            throw new BadCredentialsException(
                    "Password está incorreto."
            );
        }

        return true;
    }

}
