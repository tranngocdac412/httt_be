package btlhttt.controllers;

import btlhttt.dto.ComputerError_MarkDto;
import btlhttt.models.*;
import btlhttt.repositories.ComputerErrorMarkRepository;
import btlhttt.repositories.ComputerErrorRepository;
import btlhttt.repositories.ComputerMarkRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/computererrormarks")
public class ComputerErrorMarkController {
    @Autowired
    private ComputerMarkRepository computerMarkRepository;

    @Autowired
    private ComputerErrorRepository computerErrorRepository;

    @Autowired
    private ComputerErrorMarkRepository computerErrorMarkrepository;

    @GetMapping
    List<ComputerError_Mark> getAllComputerErrorMarks() {
        return computerErrorMarkrepository.findAll();
    }

    @GetMapping("/{uuid}")
    ResponseEntity<ResponseObject> findByUuid(@PathVariable String uuid) {
        Optional<ComputerError_Mark> foundComputerErrorMark = computerErrorMarkrepository.findByUuid(uuid);
        return foundComputerErrorMark.isPresent()
                ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query successfully", foundComputerErrorMark))
                :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("fail", "Cannot find with uuid: " + uuid, ""));
    }

    @GetMapping("/marks/{uuids}")
    ResponseEntity<ResponseObject> findByComputerMarkUuid(@PathVariable String uuids) {
        String [] markUuids = uuids.split("&&");
        List<ComputerError_Mark> foundComputerErrorMarks = new ArrayList<>();
        // get all error-mark
        for(String uuid : markUuids) {
            foundComputerErrorMarks.addAll(computerErrorMarkrepository.findByComputerMarkUuid(uuid));
        }

        //get errors - solutions
        List<String> arrErrors = new ArrayList<>();
        List<String> arrSolutions = new ArrayList<>();
        for(ComputerError_Mark computerErrorMark : foundComputerErrorMarks) {
            if(!arrErrors.contains(computerErrorMark.getComputerError().getDescription())) {
                arrErrors.add(computerErrorMark.getComputerError().getDescription());
                arrSolutions.add(computerErrorMark.getComputerError().getSolution());
            }
        }


        //get values
        double [] values =new double[arrErrors.size()];
        for(int i=0; i<arrErrors.size(); i++) {
            values[i]=0.0;
        }
        for(int i=0; i<arrErrors.size(); i++) {
            for(ComputerError_Mark computerErrorMark : foundComputerErrorMarks) {
                if(arrErrors.get(i) == computerErrorMark.getComputerError().getDescription()) {
                    values[i] += computerErrorMark.getValue();
                }
            }
        }

        //new result
        List<DiagnosticResult> results = new ArrayList<>();
        for(int i=0; i<arrErrors.size(); i++) {
            results.add(new DiagnosticResult(arrErrors.get(i), arrSolutions.get(i), values[i]));
        }

        //sort value big -> small
        results.sort(Comparator.comparing(DiagnosticResult::getValue).reversed());

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Query successfully", results));


    }

    @PostMapping
    ResponseEntity<ResponseObject> insert(@RequestBody @NotNull ComputerError_MarkDto computerErrorMarkDto) {
        String computerErrorUuid = computerErrorMarkDto.getComputerErrorUuid();
        String computerMarkUuid = computerErrorMarkDto.getComputerMarkUuid();
        Optional<ComputerError> computerError = computerErrorRepository.findByUuid(computerErrorUuid);
        Optional<ComputerMark> computerMark = computerMarkRepository.findByUuid(computerMarkUuid);
        if (computerError.isPresent() && computerMark.isPresent()) {
            ComputerError_Mark computerErrorMark = new ComputerError_Mark(computerError.get(), computerMark.get(), computerErrorMarkDto.getValue());
            List<ComputerError_Mark> computerErrorMarks = computerErrorMarkrepository.findAll();
            for (int i = 0; i < computerErrorMarks.size(); i++) {
                if (computerErrorMarks.get(i).getComputerMark() == computerErrorMark.getComputerMark()
                        && computerErrorMarks.get(i).getComputerError() == computerErrorMark.getComputerError())
                    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                            new ResponseObject("fail", "Already taken", "")
                    );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Insert successfully", computerErrorMarkrepository.save(computerErrorMark))
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("fail", "Cannot find error/mark by uuid", "")
        );
    }


    @DeleteMapping("/{uuid}")
    ResponseEntity<ResponseObject> delete(@PathVariable String uuid) {
//        boolean exist = computerErrorMarkrepository.existsByUuid(uuid);
//        System.out.println(exist);
        Optional<ComputerError_Mark> computerErrorMark = computerErrorMarkrepository.findByUuid(uuid);
        if (computerErrorMark.isPresent()) {
            computerErrorMarkrepository.delete(computerErrorMark.get());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete successfully", ""));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("fail", "Cannot find by uuid: " + uuid, ""));
        }
//        boolean exists = computerErrorMarkrepository.existsByUuid(uuid);
//        if (exists) {
//            computerErrorMarkrepository.deleteByUuid(uuid);
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject("ok", "Delete successfully", ""));
//        } else
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseObject("fail", "Cannot find by uuid: " + uuid, ""));
    }
}
