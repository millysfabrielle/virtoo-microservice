package br.edu.unievangelica.domain.animal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AnimalDTO {

    protected long animalId;

    @NotNull
    protected String nome;

    protected String raca;

    public Animal toAnimalEntity(){
        ModelMapper modelMapper = new ModelMapper();
        Animal animal = modelMapper.map(this, Animal.class);

        // specific transfirmations

        return animal;
    }

}
