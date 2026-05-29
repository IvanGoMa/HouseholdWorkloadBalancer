package ivangoma.wlbalancer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ivangoma.wlbalancer.dto.ChangePasswordDTO;
import ivangoma.wlbalancer.dto.UserRequestDTO;
import ivangoma.wlbalancer.dto.UserResponseDTO;
import ivangoma.wlbalancer.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;



@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody UserRequestDTO user) {
        
        try {
            UserResponseDTO response = service.createUser(user);
            if (response == null){
                return ResponseEntity.badRequest().body("Username already exists");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error has occurred: " + e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){

        try {
            service.deleteUser(id);
            return ResponseEntity.ok("Deleted user with id: " + id);
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(String.format("User with id %d not found", id));
        } catch (Exception e){
            return ResponseEntity.internalServerError().body("An error has occurred: " + e);
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllUsers() {

        try {
            List<UserResponseDTO> users = service.findAll();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error has occurred: " + e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {

        try {
            UserResponseDTO user = service.findById(id);
            return ResponseEntity.ok(user);

        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(String.format("User with id %d not found", id));

        } catch (Exception e){
            return ResponseEntity.internalServerError().body("An error has occurred: " + e);
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {

        try {
            UserResponseDTO user = service.findByUsername(username);
            return ResponseEntity.ok(user);

        } catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().body(String.format("User with username %s not found", username));

        } catch (Exception e){
            return ResponseEntity.internalServerError().body("An error has occurred: " + e);
        }
    }
    
    @PatchMapping("/password/{id}")
    public ResponseEntity<?> changePassword(@PathVariable Long id, @RequestBody ChangePasswordDTO changePassword){

        try {
            service.changePassword(id, changePassword);
            return ResponseEntity.ok("Password changed");

        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(String.format("User with id %d not found", id));
        
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error has occurred: " + e);
        }
    }

    @PatchMapping("/username/{id}")
    public ResponseEntity<?> changeUsername(@PathVariable Long id, @RequestBody String newUsername){

        try {
            service.changeUsername(id, newUsername);
            return ResponseEntity.ok("Username changed");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error has occurred: " + e);
        }
    }
    
    

}
