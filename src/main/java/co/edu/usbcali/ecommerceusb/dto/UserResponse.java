package co.edu.usbcali.ecommerceusb.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserResponse {

    private Long id;
    private String fullName;
    private String email;
    private Long documentTypeId;
    private String documentTypeName;
    private String documentNumber;

}
