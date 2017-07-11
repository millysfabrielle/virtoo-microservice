package br.edu.unievangelica.domain.animal;

import br.edu.unievangelica.core.exception.CustomNotFoundException;
import br.edu.unievangelica.core.exception.ExceptionMessageCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnimalService {

    @Autowired
    AnimalRepository animalRepository;

    @Transactional(readOnly = true)
    public List<AnimalDTO> findAll() {

        return Optional
                .ofNullable(animalRepository.findAll())
                .map(list -> list.stream().map(Animal::toDTO).collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    @Transactional(readOnly = true)
    public AnimalDTO findById(long id) {

        return Optional
                .ofNullable(animalRepository.findOne(id))
                .map(Animal::toDTO)
                .orElseThrow( () -> new CustomNotFoundException(ExceptionMessageCode.MENSAGEM_NOT_FOUND) );

    }

    @Transactional
    public AnimalDTO save(AnimalDTO animalDTO){
        return animalRepository.save(animalDTO.toAnimalEntity()).toDTO();
    }

    @Transactional
    public AnimalDTO update(AnimalDTO animalDTO){
        if(assertExists(animalDTO.getAnimalId())){
            Animal animal = animalRepository.getOne(animalDTO.getAnimalId());
            return animalRepository.save(animalDTO.toAnimalEntity()).toDTO();
        } else {
            throw new CustomNotFoundException(ExceptionMessageCode.MENSAGEM_NOT_FOUND);
        }
    }

    @Transactional
    public void delete(long id){
        if(this.assertExists(id)){
            animalRepository.delete(id);
        } else {
            throw new CustomNotFoundException(ExceptionMessageCode.MENSAGEM_NOT_FOUND);
        }
    }

    private boolean assertExists(long id){
        return Optional
                .ofNullable(animalRepository.getOne(id))
                .isPresent();
    }

}
