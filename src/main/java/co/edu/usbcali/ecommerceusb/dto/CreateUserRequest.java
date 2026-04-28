package co.edu.usbcali.ecommerceusb.dto;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@lombok.Getter

public class CreateUserRequest {
    private string fullName;
    private string phone;
    private string email;
    private Interger  documentTypeId;
    private string documentNumber;
    private string birthDate;
    private string country;
    private string address;
}
