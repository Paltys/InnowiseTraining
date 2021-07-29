package controller;

import dto.ContactDto;
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
                                      @Valid @RequestBody SearchContactDto searchContactDto, BindingResult bindingResult) throws ViolationErrorCustom {
        if (bindingResult.getErrorCount() > 0) {
            FieldError fieldError = bindingResult.getFieldErrors().get(0);
            throw new ViolationErrorCustom(fieldError.getDefaultMessage());
        }
        return contactService.findBy(size, number, searchContactDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveById(@PathVariable int id) throws EntityNotFoundException {
        ContactDto contactDto = contactService.getById(id);
        return new ResponseEntity<>(contactDto, HttpStatus.OK);
    }

    @PostMapping
    public int createContact(@Valid @RequestBody ContactDto contactDto, BindingResult bindingResult) throws ViolationErrorCustom {
        if (bindingResult.getErrorCount() > 0) {
            FieldError fieldError = bindingResult.getFieldErrors().get(0);
            throw new ViolationErrorCustom(fieldError.getDefaultMessage());
        }
        return contactService.createNewContact(contactDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable int id) throws EntityNotFoundException {
        contactService.deleteContact(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateContact(@Valid @RequestBody RequestContactDto requestContactDto, BindingResult bindingResult, @PathVariable int id) throws EntityNotFoundException, ViolationErrorCustom {
        if (bindingResult.getErrorCount() > 0) {
            FieldError fieldError = bindingResult.getFieldErrors().get(0);
            throw new ViolationErrorCustom(fieldError.getDefaultMessage());
        }
        contactService.updateContact(requestContactDto, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
