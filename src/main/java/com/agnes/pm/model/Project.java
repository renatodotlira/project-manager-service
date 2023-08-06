package com.agnes.pm.model;

import com.agnes.pm.model.types.ProjectStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name="client_id", nullable=false)
    private Client client;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "project"
    )
    @JsonManagedReference
    private List<Activity> activities = new ArrayList<>();

}
