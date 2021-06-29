package service.response;

import dto.ContactDto;
import java.util.List;



public class ContactResponse {
   private List<ContactDto> contactDtoList;

    public ContactResponse() {
    }

    public ContactResponse(List<ContactDto> contactDto) {
        this.contactDtoList= contactDto;
    }
}
