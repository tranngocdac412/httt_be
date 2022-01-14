package btlhttt.controllers;

import btlhttt.models.ComputerMark;
import btlhttt.models.ResponseObject;
import btlhttt.repositories.ComputerMarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/computermarks")
public class ComputerMarkController {
    @Autowired
    private ComputerMarkRepository repository;

    @GetMapping
    ResponseEntity<ResponseObject> getAllComputerMarks() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Query successfully", repository.findAll()));
    }

    @GetMapping("/{uuid}")
    ResponseEntity<ResponseObject> findByUuid(@PathVariable String uuid) {
        Optional<ComputerMark> foundComputerMark = repository.findByUuid(uuid);
        return foundComputerMark.isPresent()
                ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query successfully", foundComputerMark))
                :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("fail", "Cannot find with uuid: " + uuid, ""));
    }

    @PostMapping
    ResponseEntity<ResponseObject> insert(@RequestBody ComputerMark computerMark) {
        List<ComputerMark> foundComMarks = repository.findByDescription(computerMark.getDescription().trim());
        return (foundComMarks.size() > 0)
                ?
                ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                        new ResponseObject("fail", "Computer Mark already taken", ""))
                :
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Insert successfully", repository.save(computerMark))
                );
    }

    @PutMapping("/{uuid}")
    ResponseEntity<ResponseObject> update(@RequestBody ComputerMark newComputerMark, @PathVariable String uuid) {
        List<ComputerMark> foundComMarks = repository.findByDescription(newComputerMark.getDescription().trim());
        if (foundComMarks.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("fail", "Computer Mark already taken", ""));
        }
//        ComputerMark updateComputerMark = repository.findByUuid(uuid)
//                .map(computerMark ->
//                {
//                    computerMark.setDescription(newComputerMark.getDescription());
//                    computerMark.setValue(newComputerMark.getValue());
//                    return repository.save(computerMark);
//                })
//                .orElseGet(() -> {
//                    newComputerMark.setId(id);
//                    return repository.save(newComputerMark);
//                });
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new ResponseObject("ok", "Update Product Successfullly", updateComputerMark)
//        );
        Optional<ComputerMark> updateComputerMark = repository.findByUuid(uuid);
        if (updateComputerMark.isPresent()) {
            newComputerMark.setUuid(uuid);
            repository.save(newComputerMark);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Update Computer Mark Successfullly", updateComputerMark));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("fail", "Cannot find by uuid: " + uuid, ""));
        }

    }

    @DeleteMapping("/{uuid}")
    ResponseEntity<ResponseObject> delete(@PathVariable String uuid) {
//        boolean exist = repository.existsByUuid(uuid);
//        System.out.println(exist);
        Optional<ComputerMark> computerMark = repository.findByUuid(uuid);
        if (computerMark.isPresent()) {
            repository.delete(computerMark.get());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete successfully", ""));
        } else {
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
