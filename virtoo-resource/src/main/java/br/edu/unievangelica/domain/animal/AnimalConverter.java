package br.edu.unievangelica.domain.animal;

import org.modelmapper.ModelMapper;

import java.util.Optional;

public class AnimalConverter {

    ModelMapper modelMapper;

    public AnimalConverter() {
        Optional.ofNullable(this.modelMapper).map( mapper -> this.modelMapper).orElse(new ModelMapper());
    }

    public AnimalDTO toDTO(Animal source){
        AnimalDTO animalDTO = modelMapper.map(source, AnimalDTO.class);

        // specific transformations

        return animalDTO;
    }

    public Animal toEntity(AnimalDTO source){
        Animal animal = modelMapper.map(source, Animal.class);

        // specific transformations

        return animal;
    }

}
