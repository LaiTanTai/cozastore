package CozaStore.com.ProjectCozastore.Controller;
import CozaStore.com.ProjectCozastore.payload.Request.SignUpRequest;
import CozaStore.com.ProjectCozastore.payload.Respone.BaseRespone;
import CozaStore.com.ProjectCozastore.service.UserService;
import CozaStore.com.ProjectCozastore.utils.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtHelper jwthelper;
    @RequestMapping(value = "/signin",method = RequestMethod.POST)
    public ResponseEntity<?> signin(
            @RequestParam String email, @RequestParam String password
    ){
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(email,password);
        authenticationManager.authenticate(token);
        String jwt = jwthelper.generateToken(email);
        BaseRespone respone = new BaseRespone();
        respone.setStatusCode(200);
        respone.setData(jwt);
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }
    @RequestMapping(value = "/signup",method = RequestMethod.POST)
    public ResponseEntity<?>signup(@RequestBody SignUpRequest request){
        userService.addUser(request);
        BaseRespone respone = new BaseRespone();
        respone.setStatusCode(200);
        respone.setData("");
        return new ResponseEntity<>(respone,HttpStatus.OK);
    }
}
