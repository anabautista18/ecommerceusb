package co.edu.usbcali.ecommerceusb.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    private String fullName;
    private String phone;
    private String email;
    private Long documentTypeId;
    private String documentNumber;
    private String birthDate;
    private String country;
    private String address;
}