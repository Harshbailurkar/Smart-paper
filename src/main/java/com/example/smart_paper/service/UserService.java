package com.example.smart_paper.service;

import com.example.smart_paper.exceptions.UserNotFoundException;
import com.example.smart_paper.models.LinkMaster;
import com.example.smart_paper.models.UserModel;
import com.example.smart_paper.models.UserType;
import com.example.smart_paper.repository.LinkMasterRepository;
import com.example.smart_paper.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.smart_paper.repository.UserTypeRepo;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserTypeRepo userTypeRepo;

    @Autowired
    private LinkMasterRepository linkMasterRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserModel createUser(UserModel user, String typeName) {
        if (repo.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("A user with this email already exists.");
        }

        UserType userType = userTypeRepo.findByName(typeName);
        if (userType == null) {
            userType = userTypeRepo.findByName("parent");
            if (userType == null) {
                throw new IllegalArgumentException("User type 'parent' does not exist.");
            }
        }

        user.setUserType(userType);
        user.setPassword(encoder.encode(user.getPassword()));
        UserModel createdUser = repo.save(user);
        createdUser.setJWTToken(jwtService.generateToken(createdUser.getEmail()));
        return repo.save(createdUser);
    }

    public Map<String, Object> loginUser(UserModel u) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(u.getEmail(), u.getPassword()));

        if (authentication.isAuthenticated()) {
            UserModel user = repo.findByEmail(u.getEmail());
            String token = jwtService.generateToken(u.getEmail());
            user.setJWTToken(token);
            repo.save(user);

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("userId", user.getId());
            response.put("email", user.getEmail());
            response.put("role", user.getUserType().getName());
            return response;
        }
        throw new RuntimeException("Authentication failed");
    }

    public UserModel updateUser(Long id, UserModel user) {
        UserModel existingUser = repo.findById(Math.toIntExact(id))
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));

        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            existingUser.setPassword(encoder.encode(user.getPassword()));
        }

        return repo.save(existingUser);
    }

    public List<LinkMaster> getLinksByRole() {
        // Get the authentication object from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();

            UserModel user = repo.findByEmail(username);
            String userRole = user.getUserType().getName();  // Retrieve role from the user entity

            Long roleId = userRole.equals("parent") ? 1L : 2L;
            return linkMasterRepository.findAllByUserTypeId(roleId);
        }

        return null; // Or handle this case accordingly
    }

    public UserModel getUserById(Long id) {
        return repo.findById(Math.toIntExact(id)).orElse(null);
    }

    public UserType getUserRoleById(Long id) {
        UserModel user = repo.findById(Math.toIntExact(id))
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
        return user.getUserType();
    }
}
