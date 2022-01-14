package btlhttt.controllers;

import btlhttt.models.ComputerError;
import btlhttt.models.ResponseObject;
import btlhttt.repositories.ComputerErrorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/computererrors")
public class ComputerErrorController {
    @Autowired
    private ComputerErrorRepository repository;

    @GetMapping
    ResponseEntity<ResponseObject> getAllComputerErrors() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Query successfully", repository.findAll()));
    }

    @GetMapping("/{uuid}")
    ResponseEntity<ResponseObject> findByUuid(@PathVariable String uuid) {
        Optional<ComputerError> foundComputerError = repository.findByUuid(uuid);
        return foundComputerError.isPresent()
                ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query successfully", foundComputerError))
                :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("fail", "Cannot find with uuid: " + uuid, ""));
    }

    @PostMapping
    ResponseEntity<ResponseObject> insert(@RequestBody ComputerError computerError) {
        List<ComputerError> foundComErrors = repository.findByDescription(computerError.getDescription().trim());
        return (foundComErrors.size() > 0)
                ?
                ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                        new ResponseObject("fail", "Computer error already taken", ""))
                :
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Insert successfully", repository.save(computerError))
                );
    }

    @PutMapping("/{uuid}")
    ResponseEntity<ResponseObject> update(@RequestBody ComputerError newComputerError, @PathVariable String uuid) {
        List<ComputerError> foundComErrors = repository.findByDescription(newComputerError.getDescription().trim());
        if (foundComErrors.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("fail", "Computer error already taken", ""));
        }
//        ComputerError updateComputerError = repository.findByUuid(uuid)
//                .map(computerError ->
//                {
//                    computerError.setDescription(newComputerError.getDescription());
//                    computerError.setValue(newComputerError.getValue());
//                    return repository.save(computerError);
//                })
//                .orElseGet(() -> {
//                    newComputerError.setId(id);
//                    return repository.save(newComputerError);
//                });
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new ResponseObject("ok", "Update Product Successfullly", updateComputerError)
//        );
        Optional<ComputerError> updateComputerError = repository.findByUuid(uuid);
        if(updateComputerError.isPresent()) {
            newComputerError.setUuid(uuid);
            repository.save(newComputerError);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Update Computer Error Successfullly", updateComputerError));
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("fail", "Cannot find by uuid: " + uuid, ""));
        }

    }

    @DeleteMapping("/{uuid}")
    ResponseEntity<ResponseObject> delete(@PathVariable String uuid) {
//        boolean exist = repository.existsByUuid(uuid);
//        System.out.println(exist);
        Optional<ComputerError> computerError = repository.findByUuid(uuid);
        if(computerError.isPresent()) {
            repository.delete(computerError.get());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete successfully", ""));
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("fail", "Cannot find by uuid: " + uuid, ""));
        }
//        boolean exists = repository.existsByUuid(uuid);
//        if (exists) {
//            repository.deleteByUuid(uuid);
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject("ok", "Delete successfully", ""));
//        } else
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseObject("fail", "Cannot find by uuid: " + uuid, ""));
    }
}
