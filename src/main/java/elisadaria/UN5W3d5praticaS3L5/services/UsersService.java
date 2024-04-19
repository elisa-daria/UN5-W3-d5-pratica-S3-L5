package elisadaria.UN5W3d5praticaS3L5.services;

import elisadaria.UN5W3d5praticaS3L5.entities.User;
import elisadaria.UN5W3d5praticaS3L5.enums.UserRole;
import elisadaria.UN5W3d5praticaS3L5.exceptions.BadRequestEx;
import elisadaria.UN5W3d5praticaS3L5.exceptions.NotFoundEx;
import elisadaria.UN5W3d5praticaS3L5.payloads.UserRequestDTO;
import elisadaria.UN5W3d5praticaS3L5.repositories.UsersDAO;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    UsersDAO usersDAO;
    @Autowired
    PasswordEncoder encoder;

    //FINDING BY EMAIL
    public User findByEmail(String email) {
       return this.usersDAO.findByEmail(email).orElseThrow(()->new NotFoundEx("L'utente con email "+email +" non esiste"));
    }

    //FIND BY ID
    public User findById(long id){
        return this.usersDAO.findById(id).orElseThrow(()->new NotFoundEx(id+ "non c'è"));
    }

    //SAVE
    public User save(UserRequestDTO payload){
        this.usersDAO.findByEmail(payload.email()).ifPresent (user->{throw new BadRequestEx("L'email "+user.getEmail()+"è già in uso");});
                User newU=new User(payload.name(), payload.surname(), payload.dateOfBirth(),"https://ui-avatars.com/api/?name="+ payload.name() + "+" + payload.surname(), payload.email(), encoder.encode(payload.password()));
        return usersDAO.save(newU);
    }
    //DELETE
    public void delete(long id){
        User found =this.findById(id);
        this.usersDAO.delete(found);
    }
    //CHANGE ROLE
    public User changeRole(long id, User upgrated){
        User found=this.findById(id);
        found.setRole(upgrated.getRole());
                return this.usersDAO.save(found);
    }
    //GET USERS

    public Page<User>gettingUs(int size,int page,String sort_by){
        if(size>150)size=100;
        Pageable pageable= PageRequest.of(page,size, Sort.by(sort_by));
        return this.usersDAO.findAll(pageable);
    }



}
