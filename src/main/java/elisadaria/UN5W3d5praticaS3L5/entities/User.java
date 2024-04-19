package elisadaria.UN5W3d5praticaS3L5.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import elisadaria.UN5W3d5praticaS3L5.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
@Data
@Entity
@Table(name = "users")
@JsonIgnoreProperties({"eventList","password","role","authorities", "accountNonExpired", "credentialsNonExpired", "accountNonLocked", "enabled"})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String avatar;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @OneToMany(mappedBy = "user")
    private List<Event>eventList;

    public User(String name, String surname, LocalDate dateOfBirth, String avatar, String email, String password,UserRole role) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.avatar = avatar;
        this.email = email;
        this.password = password;
        this.role=UserRole.USER;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
