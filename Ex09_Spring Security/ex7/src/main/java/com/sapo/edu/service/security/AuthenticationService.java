package com.sapo.edu.service.security;

import com.sapo.edu.model.AuthenticationResponse;
import com.sapo.edu.model.Users;
import com.sapo.edu.repository.UsersRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UsersRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    // Constructor để inject dependencies UsersRepository, PasswordEncoder, JwtService và AuthenticationManager
    public AuthenticationService(UsersRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    // Phương thức để đăng ký người dùng
    public AuthenticationResponse register(Users request) {
        // Tạo một đối tượng người dùng mới và thiết lập các thông tin từ yêu cầu
        Users user = new Users();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        // Mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        // Lưu người dùng vào cơ sở dữ liệu và nhận lại thông tin người dùng đã được lưu
        user = repository.save(user);

        // Tạo token JWT dựa trên thông tin người dùng và trả về đối tượng AuthenticationResponse
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }

    // Phương thức để xác thực người dùng
    public AuthenticationResponse authenticate(Users request) {
        // Sử dụng AuthenticationManager để xác thực thông tin đăng nhập của người dùng
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        // Tìm người dùng trong cơ sở dữ liệu và kiểm tra xem có tồn tại không
        Users user = repository.findByUsername(request.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));

        // Nếu tồn tại, tạo token JWT và trả về đối tượng AuthenticationResponse
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }
}
