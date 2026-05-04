package co.edu.usbcali.ecommerceusb.service;

import co.edu.usbcali.ecommerceusb.dto.DocumentTypeRequest;
import co.edu.usbcali.ecommerceusb.dto.DocumentTypeResponse;

import java.util.List;

public interface DocumentTypeService {
    List<DocumentTypeResponse> getAllDocumentTypes();
    DocumentTypeResponse getDocumentTypeById(Long id);
    DocumentTypeResponse save(DocumentTypeRequest documentTypeRequest);
    DocumentTypeResponse getDocumentTypeByCode(String code);
} 
