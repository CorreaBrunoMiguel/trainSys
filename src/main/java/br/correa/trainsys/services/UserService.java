package br.correa.trainsys.services;

import br.correa.trainsys.entities.User;
import br.correa.trainsys.repositories.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    public final UserRepository repo;
    public final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository repo, BCryptPasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    // Criar Usuários
    public User createUser(User user) throws BadRequestException {
        if (repo.findByEmail(user.getEmail()).isPresent()){
            throw new BadRequestException("Usuário ja cadastrado!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(user);
    }

    // Listar Usuários
    public List<User> userList(){
        return repo.findAll();
    }

    // Ler Usuário pelo Id
    public Optional<User> userById(Long id) throws BadRequestException {
        var user = repo.findById(id);
        if (user.isEmpty()) {
            throw new BadRequestException(
                    String.format(
                            "Usuário com id: %d não encontrado",
                            id
                    )
            );
        } else {
            return user;
        }
    }




}
