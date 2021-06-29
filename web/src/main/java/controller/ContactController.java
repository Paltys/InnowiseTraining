package controller;


import dto.ContactDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.impl.ContactServiceImpl;
import service.response.ContactResponse;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
//(value="/api/contacts", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContactController {

    @Autowired
    ContactServiceImpl contactService;

    @GetMapping
    public List<ContactDto> retrieveList() {
        return contactService.getAllContact();
    }


//    public ContactResponseDto retrieveContact(){
//
//        return contactService.getById(id);
//    }
}
