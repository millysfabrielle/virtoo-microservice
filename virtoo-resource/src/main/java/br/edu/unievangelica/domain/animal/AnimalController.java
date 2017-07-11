package br.edu.unievangelica.domain.animal;

import br.edu.unievangelica.core.controller.RestAbstractController;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/animal")
@CrossOrigin(origins = "*")
public class AnimalController extends RestAbstractController {

    @Autowired
    AnimalService animalService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable long id){
        return jsonResponse(animalService.findById(id));
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        return jsonResponse(animalService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> save(@Validated @RequestBody AnimalDTO animalDTO){
        return jsonResponse(animalService.save(animalDTO));
    }

    @PutMapping
    public ResponseEntity<?> update(@Validated @RequestBody AnimalDTO animalDTO){
        return jsonResponse(animalService.save(animalDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        animalService.delete(id);
        return jsonResponse(null);
    }

}
