package CozaStore.com.ProjectCozastore.payload.Request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SignUpRequest {
    @NotNull(message = "username not null")
    @NotEmpty(message = "username not empty")
    @Email(message = "username invalid format")
    private String username;
    @NotNull(message = "Password not null")
    @NotEmpty(message = "Password not empty")
    @Length(min = 8)
    private String password ;
    @NotNull(message = "Email not null")
    @NotEmpty(message = "Email not empty")
    @Email(message = "Email invalid format")
    private String email;


    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
