package elisadaria.UN5W3d5praticaS3L5.controllers;

import elisadaria.UN5W3d5praticaS3L5.exceptions.BadRequestEx;
import elisadaria.UN5W3d5praticaS3L5.payloads.LoginRequestDTO;
import elisadaria.UN5W3d5praticaS3L5.payloads.LoginRespDTO;
import elisadaria.UN5W3d5praticaS3L5.payloads.UserRequestDTO;
import elisadaria.UN5W3d5praticaS3L5.payloads.UserRespDTO;
import elisadaria.UN5W3d5praticaS3L5.services.UsersAuth;
import elisadaria.UN5W3d5praticaS3L5.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UsersAuth ua;
    @Autowired
    private UsersService usersService;
    //http://localhost:3002/auth/register
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserRespDTO saveU(@RequestBody @Validated UserRequestDTO payload, BindingResult validation){
     if(validation.hasErrors()){
         throw new BadRequestEx(validation.getAllErrors());
     }
     return new UserRespDTO(this.usersService.save(payload).getId());
    }
    //http://localhost:3002/auth/login
    @PostMapping("/login")
    public LoginRespDTO login(@RequestBody LoginRequestDTO payload){
        return new LoginRespDTO(this.ua.logging(payload));
    }
}
