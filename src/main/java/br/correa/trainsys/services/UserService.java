package br.correa.trainsys.services;

import br.correa.trainsys.entities.User;
import br.correa.trainsys.repositories.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    public UserRepository repo;
    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    // Criar Usuários
    public User createUser(User user) throws BadRequestException {
        if (repo.findByEmail(user.getEmail()).isPresent()){
            throw new BadRequestException("Usuário ja cadastrado!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(user);
    }


}
