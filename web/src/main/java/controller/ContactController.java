package controller;

import dto.RequestContactDto;
import dto.SearchContactDto;
import exceptions.EntityNotFoundException;
import exceptions.ViolationErrorCustom;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.ContactService;
import service.response.ContactListResponse;

import javax.validation.Valid;
import java.text.ParseException;


@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public ContactListResponse retrieveList(@RequestParam int size, @RequestParam int number) {
        return contactService.getAllContact(size, number);
    }

    @PostMapping("/find")
    public ContactListResponse findBy(@RequestParam int size, @RequestParam int number,
                                      @Valid @RequestBody SearchContactDto searchContactDto, BindingResult bindingResult) throws ViolationErrorCustom, ParseException {
        checkValidation(bindingResult);
        return contactService.findBy(size, number, searchContactDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveById(@PathVariable int id) throws EntityNotFoundException {
        RequestContactDto requestContactDto = contactService.getById(id);
        return new ResponseEntity<>(requestContactDto, HttpStatus.OK);
    }

    @PostMapping
    public int createContact(@Valid @RequestBody RequestContactDto requestContactDto, BindingResult bindingResult) throws ViolationErrorCustom, ParseException, EntityNotFoundException {
        checkValidation(bindingResult);
        return contactService.createNewContact(requestContactDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable int id) throws EntityNotFoundException {
        contactService.deleteContact(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateContact(@Valid @RequestBody RequestContactDto requestContactDto, BindingResult bindingResult, @PathVariable int id) throws EntityNotFoundException, ViolationErrorCustom, ParseException {
        checkValidation(bindingResult);
        contactService.updateContact(requestContactDto, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void checkValidation (BindingResult bindingResult) throws ViolationErrorCustom {
        if (bindingResult.getErrorCount() > 0) {
            FieldError fieldError = bindingResult.getFieldErrors().get(0);
            throw new ViolationErrorCustom(fieldError.getDefaultMessage());
        }
    }
}
