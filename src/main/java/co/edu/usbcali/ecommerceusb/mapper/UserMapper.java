package co.edu.usbcali.ecommerceusb.mapper;

import co.edu.usbcali.ecommerceusb.dto.CreateUserRequest;
import co.edu.usbcali.ecommerceusb.dto.UserResponse;
import co.edu.usbcali.ecommerceusb.model.DocumentType;
import co.edu.usbcali.ecommerceusb.model.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .documentTypeId(user.getDocumentType() != null ? user.getDocumentType().getId() : null)
                .documentTypeName(user.getDocumentType() != null ? user.getDocumentType().getName() : null)
                .documentNumber(user.getDocumentNumber())
                .build();
    }

    public static List<UserResponse> toUserResponseList(List<User> users) {
        List<UserResponse> userResponseList = new ArrayList<>();
        for (User user : users) {
            userResponseList.add(toUserResponse(user));
        }
        return userResponseList;
    }

    public static User createUserRequestToUser(CreateUserRequest createUserRequest, DocumentType documentType) {
        return User.builder()
                .fullName(createUserRequest.getFullName())
                .phone(createUserRequest.getPhone())
                .email(createUserRequest.getEmail())
                .documentType(documentType)
                .documentNumber(createUserRequest.getDocumentNumber())
                .birthDate(LocalDate.parse(createUserRequest.getBirthDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .country(createUserRequest.getCountry())
                .address(createUserRequest.getAddress())
                .build();
    }
}
