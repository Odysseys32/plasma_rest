package token.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import token.dto.request.UserRequest;
import token.model.Role;
import token.model.User;
import token.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    public ResponseEntity<String> createStudent(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("this email is already have in!");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        user.setFirst_name(request.getName());
        user.setRole(Role.STUDENT);
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<String> createManager(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("this email is already have in!");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        user.setFirst_name(request.getName());
        user.setRole(Role.TEACHER);
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }
}