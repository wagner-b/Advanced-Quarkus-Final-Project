package tech.ada.model;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
@UserDefinition
public class User extends PanacheEntity {

    @NotBlank(message = "Name must not be blank")
    @Username
    private String username;

    @NotBlank(message = "Email must not be blank")
    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @NotBlank(message = "Password must not be blank")
    @Size(min = 8, message = "Password must have at least 8 characters")
    @Password
    private String password;

    @NotBlank(message = "Role must not be blank")
    @Roles
    private String role;


    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getRole() {
        return role;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public static void add(@Valid String username, @Valid String email,
                           @Valid String password, @Valid String role) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email.toLowerCase().trim());
        user.setPassword(BcryptUtil.bcryptHash(password));
        user.setRole(role);
        user.persist();
    }
}