package pl.reaktor.w3.miniblog.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.ResourceProperties;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
@Column(unique = true)
    private String roleName;

}
