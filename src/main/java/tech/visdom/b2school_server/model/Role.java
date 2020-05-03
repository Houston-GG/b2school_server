package tech.visdom.b2school_server.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="NAME", length = 63, nullable = false)
    private String name;

    @Column(name="DESCRIPTION", nullable = false)
    private String description;
}
