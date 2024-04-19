package elisadaria.UN5W3d5praticaS3L5.services;

import elisadaria.UN5W3d5praticaS3L5.entities.User;
import elisadaria.UN5W3d5praticaS3L5.exceptions.UnAuthorizedEx;
import elisadaria.UN5W3d5praticaS3L5.payloads.LoginRequestDTO;
import elisadaria.UN5W3d5praticaS3L5.security.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
public class UsersAuth {
    @Autowired
    private UsersService usersService;
    @Autowired
    JWT jwtTool;
    @Autowired
    private PasswordEncoder encoder;
    public String logging(LoginRequestDTO payload){
        User u= usersService.findByEmail(payload.email());
        if(encoder.matches(payload.password(), u.getPassword())){
            return jwtTool.creatingT(u);
        }else{
            throw new UnAuthorizedEx("Rieffettuare il login, credenziali non valide");
        }
    }
}
