package br.edu.unievangelica.domain.animal;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "animal")
public class Animal {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "animal_id_seq")
    @SequenceGenerator(
            name = "animal_id_seq",
            sequenceName = "animal_id_seq",
            allocationSize = 1)
    @Column(name = "animal_id")
    @Getter
    private long animalId;

    @NotNull
    @Column(name = "nome")
    @Getter
    @Setter
    private String nome;

    @NotNull
    @Column(name = "raca")
    @Getter
    @Setter
    private String raca;

    public AnimalDTO toDTO(){
        ModelMapper modelMapper = new ModelMapper();
        AnimalDTO animalDTO = modelMapper.map(this, AnimalDTO.class);

        // specific transformations

        return animalDTO;
    }

}
