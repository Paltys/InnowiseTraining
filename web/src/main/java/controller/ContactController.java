package controller;

import dto.ContactDto;
import dto.RequestContactDto;
import dto.SearchContactDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import service.response.ErrorResponse;

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
                                              @Validated @RequestBody SearchContactDto searchContactDto) {
        return contactService.findBy(size, number, searchContactDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveById(@PathVariable int id) {
        try {
            ContactDto contactDto = contactService.getById(id);
            return new ResponseEntity<>(contactDto,HttpStatus.OK);
        } catch (RuntimeException e) {
            ErrorResponse errorResponse = new ErrorResponse("404","Sorry. Contact not found");

            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public int createContact( @Validated @RequestBody RequestContactDto requestContactDto){
        return contactService.createNewContact(requestContactDto);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<?> deleteContact(@PathVariable int id) {
        try {
            contactService.deleteContact(id);
        } catch (RuntimeException e) {
            ErrorResponse errorResponse = new ErrorResponse("404","Sorry. Contact not found");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>updateContact( @Validated @RequestBody RequestContactDto requestContactDto,@PathVariable int id){
        try {
            contactService.updateContact(requestContactDto,id);
        } catch (RuntimeException e) {
            ErrorResponse errorResponse = new ErrorResponse("404","Sorry. Contact not found");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
