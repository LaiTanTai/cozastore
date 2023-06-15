package CozaStore.com.ProjectCozastore.service;

import CozaStore.com.ProjectCozastore.Entity.UserEntity;
import CozaStore.com.ProjectCozastore.payload.Request.SignUpRequest;
import CozaStore.com.ProjectCozastore.respository.UserRepository;
import CozaStore.com.ProjectCozastore.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class UserService implements UserServiceImp {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean addUser(@Valid SignUpRequest request) {
        boolean isSuccess = false;
        try{
            UserEntity user = new UserEntity();
            user.setUsername(request.getUsername());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setEmail(request.getEmail());

            userRepository.save(user);
            isSuccess = true;
        }catch (Exception e){

        }
        return isSuccess;
    }
}