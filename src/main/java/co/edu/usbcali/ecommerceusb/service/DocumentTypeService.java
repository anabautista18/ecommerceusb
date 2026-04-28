package co.edu.usbcali.ecommerceusb.service;

import co.edu.usbcali.ecommerceusb.dto.DocumentTypeRequest;
import co.edu.usbcali.ecommerceusb.dto.DocumentTypeResponse;
import co.edu.usbcali.ecommerceusb.model.DocumentType;

import java.util.List;
import java.util.Optional;

public interface DocumentTypeService {
    List<DocumentTypeResponse> findAll();
    Optional<DocumentTypeResponse> findById(Long id);
    DocumentTypeResponse save(DocumentTypeRequest documentType);

}
