package com.company.project.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Data
@Table(name = "PLANTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Size(min=1, max = 255, message = "Name must be between 1 and 255 characters")
    private String name;

    @Nullable
    @Size(max = 255)
    private String description;
}