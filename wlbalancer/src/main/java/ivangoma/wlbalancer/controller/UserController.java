package ivangoma.wlbalancer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ivangoma.wlbalancer.dto.UserRequestDTO;
import ivangoma.wlbalancer.dto.UserResponseDTO;
import ivangoma.wlbalancer.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody UserRequestDTO user) {
        
        UserResponseDTO response = service.createUser(user);
        if (response == null){
            return ResponseEntity.internalServerError()
        }
    }
    

}
