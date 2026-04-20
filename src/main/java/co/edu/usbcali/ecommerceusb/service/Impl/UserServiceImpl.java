package co.edu.usbcali.ecommerceusb.service.Impl;

import co.edu.usbcali.ecommerceusb.dto.UserResponse;
import co.edu.usbcali.ecommerceusb.mapper.UserMapper;
import co.edu.usbcali.ecommerceusb.service.UserService;
import co.edu.usbcali.ecommerceusb.repository.UserRepository;
import co.edu.usbcali.ecommerceusb.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // Esto inyectará el userRepository automáticamente
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            return List.of();
        }

        /* Si la lista contiene información, retornamos la lista mapeada */
        return UserMapper.toUserResponseList(users);
    }

    @Override
    public UserResponse getUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(UserMapper::toUserResponse).orElse(null);
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        // Assuming you add a method in repository, but for now, find all and filter
        List<User> users = userRepository.findAll();
        Optional<User> user = users.stream().filter(u -> u.getEmail().equals(email)).findFirst();
        return user.map(UserMapper::toUserResponse).orElse(null);
    }
}