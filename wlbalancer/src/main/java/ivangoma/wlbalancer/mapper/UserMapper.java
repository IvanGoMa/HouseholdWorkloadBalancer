package ivangoma.wlbalancer.mapper;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import ivagoma.wlbalancer.model.User;
import ivangoma.wlbalancer.dto.UserRequestDTO;
import ivangoma.wlbalancer.dto.UserResponseDTO;

@Component
public class UserMapper {

    public User toEntity (UserRequestDTO dto){
        Timestamp now = new Timestamp(System.currentTimeMillis());
        return new User(dto.username(), dto.password(), now, now);
    }

    public UserResponseDTO toDTO (User user){
        return new UserResponseDTO(user.getId(), user.getUsername());
    }
}
