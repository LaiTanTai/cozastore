package CozaStore.com.ProjectCozastore.provider;

import CozaStore.com.ProjectCozastore.Entity.UserEntity;
import CozaStore.com.ProjectCozastore.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomAuthenProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        //Lấy username người dùng truyền vào
        String email = authentication.getName();
        System.out.print(email);
        //Lấy mật khẩu người dùng truyền vào
        String password = authentication.getCredentials().toString();

        UserEntity user = userRepository.findByEmail(email);

        if(user != null && passwordEncoder.matches(password,user.getPassword())){
            System.out.print("success");
            //Đăng nhập thành công
            return new UsernamePasswordAuthenticationToken(email,user.getPassword(),new ArrayList<>());
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //Khai báo loại chứng thực sử dụng để so sánh
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
