package service.response;

import dto.ContactDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactListResponse {
    private List<ContactDto> list;
    private int totalElements;
}
