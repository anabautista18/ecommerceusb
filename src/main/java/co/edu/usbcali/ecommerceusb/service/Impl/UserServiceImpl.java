package co.edu.usbcali.ecommerceusb.service.impl;

import co.edu.usbcali.ecommerceusb.dto.CreateUserRequest;
import co.edu.usbcali.ecommerceusb.dto.UserResponse;
import co.edu.usbcali.ecommerceusb.mapper.UserMapper;
import co.edu.usbcali.ecommerceusb.model.DocumentType;
import co.edu.usbcali.ecommerceusb.model.User;
import co.edu.usbcali.ecommerceusb.repository.DocumentTypeRepository;
import co.edu.usbcali.ecommerceusb.repository.UserRepository;
import co.edu.usbcali.ecommerceusb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Override
    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            return List.of();
        }

        return UserMapper.toUserResponseList(users);
    }

    @Override
    public UserResponse getUserById(Long id) throws Exception {
        if (id == null) {
            throw new IllegalArgumentException("Debe ingresar el id para buscar");
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new Exception(String.format("Usuario no encontrado con el id: %d", id)));

        return UserMapper.toUserResponse(user);
    }

    @Override
    public UserResponse getUserByEmail(String email) throws Exception {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Debe ingresar el email");
        }

        User userByEmail = userRepository.findByEmail(email)
                .orElseThrow(() -> new Exception(String.format("Usuario no encontrado con el email: %s", email)));

        return UserMapper.toUserResponse(userByEmail);
    }

    @Override
    public UserResponse createUser(CreateUserRequest createUserRequest) throws Exception {
        if (Objects.isNull(createUserRequest)) {
            throw new IllegalArgumentException("El objeto createUserRequest no puede ser nulo");
        }

        if (Objects.isNull(createUserRequest.getFullName()) || createUserRequest.getFullName().isBlank()) {
            throw new IllegalArgumentException("El campo fullName no puede ser nulo ni vacío");
        }

        if (Objects.isNull(createUserRequest.getPhone()) || createUserRequest.getPhone().isBlank()) {
            throw new IllegalArgumentException("El campo phone no puede ser nulo ni vacío");
        }

        if (Objects.isNull(createUserRequest.getEmail()) || createUserRequest.getEmail().isBlank()) {
            throw new IllegalArgumentException("El campo email no puede ser nulo ni vacío");
        }

        if (createUserRequest.getDocumentTypeId() == null || createUserRequest.getDocumentTypeId() <= 0) {
            throw new IllegalArgumentException("El campo documentTypeId debe ser mayor a 0");
        }

        if (Objects.isNull(createUserRequest.getDocumentNumber()) || createUserRequest.getDocumentNumber().isBlank()) {
            throw new IllegalArgumentException("El campo documentNumber no puede ser nulo ni vacío");
        }

        if (Objects.isNull(createUserRequest.getBirthDate()) || createUserRequest.getBirthDate().isBlank()) {
            throw new IllegalArgumentException("El campo birthDate no puede ser nulo ni vacío");
        }

        if (Objects.isNull(createUserRequest.getCountry()) || createUserRequest.getCountry().isBlank()) {
            throw new IllegalArgumentException("El campo country no puede ser nulo ni vacío");
        }

        if (Objects.isNull(createUserRequest.getAddress()) || createUserRequest.getAddress().isBlank()) {
            throw new IllegalArgumentException("El campo address no puede ser nulo ni vacío");
        }

        if (userRepository.existsByEmail(createUserRequest.getEmail())) {
            throw new IllegalArgumentException("Ya existe un usuario con el email ingresado");
        }

        if (userRepository.existsByDocumentNumberAndDocumentTypeId(createUserRequest.getDocumentNumber(), createUserRequest.getDocumentTypeId())) {
            throw new IllegalArgumentException("Ya existe un usuario con el mismo documento y tipo de documento");
        }

        DocumentType documentType = documentTypeRepository.findById(createUserRequest.getDocumentTypeId())
                .orElseThrow(() -> new Exception("El tipo de documento no existe"));

        User user = UserMapper.createUserRequestToUser(createUserRequest, documentType);
        user = userRepository.save(user);

        return UserMapper.toUserResponse(user);
    }

    @Override
    public UserResponse updateUser(Long id, CreateUserRequest createUserRequest) throws Exception {
        if (id == null) {
            throw new IllegalArgumentException("Debe ingresar el id para actualizar");
        }
        if (Objects.isNull(createUserRequest)) {
            throw new IllegalArgumentException("El objeto createUserRequest no puede ser nulo");
        }

        if (Objects.isNull(createUserRequest.getFullName()) || createUserRequest.getFullName().isBlank()) {
            throw new IllegalArgumentException("El campo fullName no puede ser nulo ni vacío");
        }

        if (Objects.isNull(createUserRequest.getPhone()) || createUserRequest.getPhone().isBlank()) {
            throw new IllegalArgumentException("El campo phone no puede ser nulo ni vacío");
        }

        if (Objects.isNull(createUserRequest.getEmail()) || createUserRequest.getEmail().isBlank()) {
            throw new IllegalArgumentException("El campo email no puede ser nulo ni vacío");
        }

        if (createUserRequest.getDocumentTypeId() == null || createUserRequest.getDocumentTypeId() <= 0) {
            throw new IllegalArgumentException("El campo documentTypeId debe ser mayor a 0");
        }

        if (Objects.isNull(createUserRequest.getDocumentNumber()) || createUserRequest.getDocumentNumber().isBlank()) {
            throw new IllegalArgumentException("El campo documentNumber no puede ser nulo ni vacío");
        }

        if (Objects.isNull(createUserRequest.getBirthDate()) || createUserRequest.getBirthDate().isBlank()) {
            throw new IllegalArgumentException("El campo birthDate no puede ser nulo ni vacío");
        }

        if (Objects.isNull(createUserRequest.getCountry()) || createUserRequest.getCountry().isBlank()) {
            throw new IllegalArgumentException("El campo country no puede ser nulo ni vacío");
        }

        if (Objects.isNull(createUserRequest.getAddress()) || createUserRequest.getAddress().isBlank()) {
            throw new IllegalArgumentException("El campo address no puede ser nulo ni vacío");
        }

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new Exception(String.format("Usuario no encontrado con el id: %d", id)));

        // Check if email is being changed and if it's already taken
        if (!existingUser.getEmail().equals(createUserRequest.getEmail()) && userRepository.existsByEmail(createUserRequest.getEmail())) {
            throw new IllegalArgumentException("Ya existe un usuario con el email ingresado");
        }

        // Check if document is being changed and if it's already taken
        if ((!existingUser.getDocumentNumber().equals(createUserRequest.getDocumentNumber()) || !existingUser.getDocumentType().getId().equals(createUserRequest.getDocumentTypeId()))
                && userRepository.existsByDocumentNumberAndDocumentTypeId(createUserRequest.getDocumentNumber(), createUserRequest.getDocumentTypeId())) {
            throw new IllegalArgumentException("Ya existe un usuario con el mismo documento y tipo de documento");
        }

        DocumentType documentType = documentTypeRepository.findById(createUserRequest.getDocumentTypeId())
                .orElseThrow(() -> new Exception("El tipo de documento no existe"));

        existingUser.setFullName(createUserRequest.getFullName());
        existingUser.setPhone(createUserRequest.getPhone());
        existingUser.setEmail(createUserRequest.getEmail());
        existingUser.setDocumentType(documentType);
        existingUser.setDocumentNumber(createUserRequest.getDocumentNumber());
        existingUser.setBirthDate(LocalDate.parse(createUserRequest.getBirthDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        existingUser.setCountry(createUserRequest.getCountry());
        existingUser.setAddress(createUserRequest.getAddress());

        existingUser = userRepository.save(existingUser);

        return UserMapper.toUserResponse(existingUser);
    }
}
