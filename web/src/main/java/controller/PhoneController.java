package controller;


import dto.PhoneDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.PhoneService;
import service.response.ErrorResponce;

@RestController
@RequestMapping("/api/phones")
public class PhoneController {


    private final PhoneService phoneService;

    public PhoneController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveById(@PathVariable int id) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("Content-Type", "application/json");

        try {
            PhoneDto phoneDto = phoneService.getById(id);
            return new ResponseEntity<>(phoneDto, HttpStatus.OK);
        } catch (RuntimeException e) {
            ErrorResponce errorResponce = new ErrorResponce("404", "Sorry. Phone not found", "phone");

            return new ResponseEntity<>(errorResponce, params, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public int createPhone(@RequestBody PhoneDto newPhone){
        return phoneService.createNewPhone(newPhone);
    }

    @DeleteMapping("{id}")
    public  ResponseEntity<?> deletePhone(@PathVariable int id) {
        try {
            phoneService.deletePhone(id);
        } catch (RuntimeException e) {
            ErrorResponce errorResponce = new ErrorResponce("404","Sorry. Phone not found","phone");
            return new ResponseEntity<>(errorResponce, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>updatePhone(@RequestBody PhoneDto phoneDto,@PathVariable int id){
        try {
            phoneService.updatePhone(phoneDto,id);
        } catch (RuntimeException e) {
            ErrorResponce errorResponce = new ErrorResponce("404","Sorry. Phone not found","phone");
            return new ResponseEntity<>(errorResponce, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
