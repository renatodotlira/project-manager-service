package com.agnes.pm.model;

import com.agnes.pm.model.types.ActivityStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "activity")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name="project_id", nullable=false)
    @JsonBackReference
    private Project project;

    @Enumerated(EnumType.STRING)
    private ActivityStatus status;

}
