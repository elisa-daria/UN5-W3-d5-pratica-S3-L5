package elisadaria.UN5W3d5praticaS3L5.controllers;

import elisadaria.UN5W3d5praticaS3L5.entities.User;
import elisadaria.UN5W3d5praticaS3L5.exceptions.BadRequestEx;
import elisadaria.UN5W3d5praticaS3L5.payloads.UserRequestDTO;
import elisadaria.UN5W3d5praticaS3L5.payloads.UserRespDTO;
import elisadaria.UN5W3d5praticaS3L5.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")

public class UsersController {
    @Autowired
    UsersService usersService;
    //http://localhost:3002/users
    @GetMapping("")
    @PreAuthorize("hasAuthority('EVENT_PLANNER')")

    public Page<User>getUsers(
            @RequestParam(defaultValue = "1")int page,
            @RequestParam(defaultValue = "15")int size,
            @RequestParam(defaultValue = "name")String sort_by

    ){
        return this.usersService.gettingUs(page, size, sort_by);
    }
    //http://localhost:3002/users/me
    @GetMapping("/me")
    public User myProfile(@AuthenticationPrincipal User current){
        return current;
    }
    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMyProfile(@AuthenticationPrincipal User current){
        usersService.delete(current.getId());
    }
    //http://localhost:3002/users/id
    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('EVENT_PLANNER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteU (@PathVariable long userId){
        this.usersService.delete(userId);
    }




}
