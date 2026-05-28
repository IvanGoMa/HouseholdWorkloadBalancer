package ivangoma.wlbalancer.service;

import org.springframework.stereotype.Service;

import ivagoma.wlbalancer.model.User;
import ivangoma.wlbalancer.dto.UserRequestDTO;
import ivangoma.wlbalancer.dto.UserResponseDTO;
import ivangoma.wlbalancer.mapper.UserMapper;
import ivangoma.wlbalancer.repository.UserRepository;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository repository;

    public UserService(UserMapper userMapper, UserRepository userRepository){
        this.userMapper = userMapper;
        this.repository = userRepository;
    }

    public UserResponseDTO createUser(UserRequestDTO request){
        try {
            User user = userMapper.toEntity(request);
            User savedUser = repository.save(user);
            return userMapper.toDTO(savedUser); 
                      
        } catch (Exception e) {
            return null;
        }
    }
}
