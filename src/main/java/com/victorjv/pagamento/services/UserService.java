package com.victorjv.pagamento.services;


import com.victorjv.pagamento.entities.User;
import com.victorjv.pagamento.entities.dtos.UserDTO;
import com.victorjv.pagamento.entities.enums.Type;
import com.victorjv.pagamento.repositories.UserRepository;
import com.victorjv.pagamento.services.exceptions.TransactionError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void debit(Double amount, User user){
        user.debit(amount);
        repository.save(user);
    }

    public void credit(Double amount, User user){
        user.credit(amount);
        repository.save(user);
    }

    public static UserDTO toDTO(User user){
        return new UserDTO(user.getId(), user.getName(), user.getDocument(),
                user.getEmail(), user.getBalance(), Type.getType(user.getType()));
    }

    public User toUser(UserDTO userDTO){
        return repository.findById(userDTO.id()).orElse(null);
    }

    // ========================= CRUD ===========================

    public User insert(User user){

        return repository.save(user);
    }

    public List<UserDTO> findAll(){

        return repository.findAll().stream().map(UserService::toDTO).toList();
    }

    public UserDTO findById(Long id){

        return UserService.toDTO(repository.findById(id).orElseThrow(() -> new NullPointerException("Nenhum usuario encontrado com o id " + id)));
    }

    public UserDTO finByDocument(String document){


        Optional<User> user = repository.findByDocument(document);

        if (user.stream().toList().isEmpty()) {
            throw new NullPointerException ("Nenhuma carteira encontrada com o documento " + document);
        }

        return UserService.toDTO(user.stream().toList().getFirst());
    }

    public void deleteById(Long id){
        findById(id);
        repository.deleteById(id);
    }

    public User update(User obj){

        User user = repository.getReferenceById(obj.getId());
        updateDate(user, obj);

        return repository.save(user);
    }

    private void updateDate(User user, User obj){

        user.setName(obj.getName());
        user.setDocument(obj.getDocument());
        user.setEmail(obj.getEmail());
        user.setPassword(obj.getPassword());
        user.setType(obj.getType());
    }

}
