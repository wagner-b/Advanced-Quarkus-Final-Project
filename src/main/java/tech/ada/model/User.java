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
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "user_auth")
@UserDefinition
public class User extends PanacheEntity {

    @NotBlank(message = "Name must not be blank")
    @Username
    private String name;

    @NotBlank(message = "Email must not be blank")
    @Column(nullable = false, unique = true)
//    @Email
    private String email;

    @NotBlank(message = "Password must not be blank")
//    @Size(min = 8, message = "Password must have at least 8 characters")
    @Password
    private String password;

    @NotBlank(message = "Role must not be blank")
    @Roles
    private String role;

    public User(String name, String email,
                String password, String role) {
        this.name = name;
        this.email = email.toLowerCase().trim();
        this.password = BcryptUtil.bcryptHash(password);
        this.role = role;
    }

    protected User() {}

    public String getName() {return name;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public String getRole() {return role;}

    public void setName(
            String name
    ) {this.name = name;}

    public void setEmail(
            String email
    ) {this.email = email.toLowerCase().trim();}

    public void setPassword(
            String password
    ) {this.password = BcryptUtil.bcryptHash(password);}

    public void setRole(String role) {this.role = role;}

}