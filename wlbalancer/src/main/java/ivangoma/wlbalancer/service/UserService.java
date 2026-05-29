package ivangoma.wlbalancer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ivangoma.wlbalancer.dto.ChangePasswordDTO;
import ivangoma.wlbalancer.dto.UserRequestDTO;
import ivangoma.wlbalancer.dto.UserResponseDTO;
import ivangoma.wlbalancer.mapper.UserMapper;
import ivangoma.wlbalancer.model.User;
import ivangoma.wlbalancer.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserMapper userMapper, UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userMapper = userMapper;
        this.repository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO createUser(UserRequestDTO request){

        try {
            User user = userMapper.toEntity(request);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User savedUser = repository.save(user);
            return userMapper.toDTO(savedUser); 
                      
        } catch (IllegalArgumentException e) {
            return null;
        }
    }


    public void deleteUser(Long id) {

        try {
            Optional<User> optUser= repository.findById(id);
            if(optUser.isEmpty()) throw new NoSuchElementException("User not found");
            repository.delete(optUser.get());
            
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public List<UserResponseDTO> findAll() {
        
        List<User> users = repository.findAll();
        List<UserResponseDTO> response = new ArrayList<>();

        for(User u: users){
            response.add(userMapper.toDTO(u));
        }

        return response;
    }

    public UserResponseDTO findById(Long id) {
        
        Optional<User> optUser = repository.findById(id);
        if(optUser.isEmpty()) throw new NoSuchElementException("User not found");
        return userMapper.toDTO(optUser.get());
    }

    public UserResponseDTO findByUsername(String username) {

        Optional<User> optUser = repository.findByUsername(username);
        if(optUser.isEmpty()) throw new UsernameNotFoundException("User not found");
        return userMapper.toDTO(optUser.get());
    }

    public void changePassword(Long id, ChangePasswordDTO changePassword) {
        
        Optional<User> optUser= repository.findById(id);
        if(optUser.isEmpty()) throw new NoSuchElementException("User not found");

        User user = optUser.get();
        String encodedOldPassword = passwordEncoder.encode(changePassword.oldPassword());

        if (!user.getPassword().equals(encodedOldPassword)) throw new BadCredentialsException("Incorrect old password");

        String encodedNewPassword = passwordEncoder.encode(changePassword.newPassword());
        user.setPassword(encodedNewPassword);
        repository.save(user);

        
    }

    public void changeUsername(Long id, String newUsername) {
        
        Optional<User> optUser= repository.findById(id);
        if(optUser.isEmpty()) throw new NoSuchElementException("User not found");

        User user = optUser.get();
        if (repository.findByUsername(newUsername).isPresent()) throw new IllegalArgumentException("Username already exists");

        user.setUsername(newUsername);
        repository.save(user);
        
    }
}
