package co.edu.usbcali.ecommerceusb.mapper;

import co.edu.usbcali.ecommerceusb.dto.UserResponse;
import co.edu.usbcali.ecommerceusb.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    //use(Entity - Model) ->UserResponse(DTO)
    public static UserResponse toUserResponse(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                //uso de un If Ternario
                .documentTypeId(
                        user.getDocumentType() != null ? user.getDocumentType().getId() : null)
                .documentTypeName(
                        user.getDocumentType() != null ? user.getDocumentType().getName() : null)
                .documentNumber(user.getDocumentNumber())
                .build();
    }

    public static List<UserResponse> toUserResponseList(List<User> users) {
        List<UserResponse> userResponseList = new ArrayList<>();
        for (User user : users) {
            UserResponse userResponse = toUserResponse(user);
            userResponseList.add(userResponse);
        }
        return userResponseList;
    }
}
