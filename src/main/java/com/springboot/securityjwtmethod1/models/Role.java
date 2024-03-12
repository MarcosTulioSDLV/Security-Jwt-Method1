package com.springboot.securityjwtmethod1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.securityjwtmethod1.enums.RoleName;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_ROLE")
@AllArgsConstructor
@Getter @Setter @ToString(exclude = "users")
@EqualsAndHashCode
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    @Setter(AccessLevel.PRIVATE)
    private List<User> users= new ArrayList<>();


    public Role(){
    }


    @Override
    public String getAuthority() {
        return roleName.name();
    }
}
