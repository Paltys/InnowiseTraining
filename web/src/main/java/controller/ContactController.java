package controller;

import dto.ContactDto;
import dto.RequestContactDto;
import dto.SearchContactDto;
import exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
import service.impl.MailSender;
import service.response.ContactListResponse;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactService contactService;
    private final MailSender mailSender;

    public ContactController(ContactService contactService, MailSender mailSender) {
        this.contactService = contactService;
        this.mailSender=mailSender;
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
    public ResponseEntity<?> retrieveById(@PathVariable int id) throws EntityNotFoundException {
            ContactDto contactDto = contactService.getById(id);
//            mailSender.sendMail("Paltys01@yandex.ru","HappyBirthday","Happy birthday"+contactDto.getFirstName()+" "
//            +contactDto.getLastName());
            return new ResponseEntity<>(contactDto, HttpStatus.OK);
}

    @PostMapping
    public int createContact( @RequestBody ContactDto contactDto, BindingResult result) {
        System.out.println(result);
        return contactService.createNewContact(contactDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable int id) throws EntityNotFoundException {
        contactService.deleteContact(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateContact(@Validated @RequestBody RequestContactDto requestContactDto, @PathVariable int id) throws EntityNotFoundException {
        contactService.updateContact(requestContactDto, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
