package controller;


import dto.ContactDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.ContactService;
import service.response.ContactListResponse;
import service.response.ErrorResponce;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveById(@PathVariable int id) {
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("Content-Type", "application/json");

        try {
            ContactDto contactDto = contactService.getById(id);
            return new ResponseEntity<>(contactDto,HttpStatus.OK);
        } catch (RuntimeException e) {
            ErrorResponce errorResponce = new ErrorResponce("404","Sorry. Contact not found","contact");

            return new ResponseEntity<>(errorResponce,params,HttpStatus.NOT_FOUND);
        }
    }
}
