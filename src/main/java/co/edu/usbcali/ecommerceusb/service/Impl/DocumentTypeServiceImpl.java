package co.edu.usbcali.ecommerceusb.service.impl;

import co.edu.usbcali.ecommerceusb.dto.DocumentTypeRequest;
import co.edu.usbcali.ecommerceusb.dto.DocumentTypeResponse;
import co.edu.usbcali.ecommerceusb.mapper.DocumentTypeMapper;
import co.edu.usbcali.ecommerceusb.model.DocumentType;
import co.edu.usbcali.ecommerceusb.repository.DocumentTypeRepository;
import co.edu.usbcali.ecommerceusb.service.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Override
    public List<DocumentTypeResponse> getAllDocumentTypes() {
        List<DocumentType> documentTypes = documentTypeRepository.findAll();
        if (documentTypes.isEmpty()) {
            return List.of();
        }

        return DocumentTypeMapper.toDocumentTypeResponseList(documentTypes);
    }

    @Override
    public DocumentTypeResponse getDocumentTypeById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Debe ingresar el id para buscar");
        }

        DocumentType documentType = documentTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Tipo de documento no encontrado con el id: %d", id)));

        return DocumentTypeMapper.toDocumentTypeResponse(documentType);
    }

    @Override
    public DocumentTypeResponse save(DocumentTypeRequest documentTypeRequest) {
        if (documentTypeRequest == null) {
            throw new IllegalArgumentException("El request de tipo de documento no puede ser nulo");
        }

        if (documentTypeRequest.getCode() == null || documentTypeRequest.getCode().isBlank()) {
            throw new IllegalArgumentException("El código no puede ser nulo ni vacío");
        }

        if (documentTypeRequest.getName() == null || documentTypeRequest.getName().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo ni vacío");
        }

        DocumentType documentType = DocumentTypeMapper.toDocumentType(documentTypeRequest);
        documentType = documentTypeRepository.save(documentType);

        return DocumentTypeMapper.toDocumentTypeResponse(documentType);
    }

    @Override
    public DocumentTypeResponse update(Long id, DocumentTypeRequest documentTypeRequest) {
        if (id == null) {
            throw new IllegalArgumentException("Debe ingresar el id para actualizar");
        }
        if (documentTypeRequest == null) {
            throw new IllegalArgumentException("El request de tipo de documento no puede ser nulo");
        }

        if (documentTypeRequest.getCode() == null || documentTypeRequest.getCode().isBlank()) {
            throw new IllegalArgumentException("El código no puede ser nulo ni vacío");
        }

        if (documentTypeRequest.getName() == null || documentTypeRequest.getName().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo ni vacío");
        }

        DocumentType existingDocumentType = documentTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Tipo de documento no encontrado con el id: %d", id)));

        existingDocumentType.setCode(documentTypeRequest.getCode());
        existingDocumentType.setName(documentTypeRequest.getName());
        existingDocumentType = documentTypeRepository.save(existingDocumentType);

        return DocumentTypeMapper.toDocumentTypeResponse(existingDocumentType);
    }

    @Override
    public DocumentTypeResponse getDocumentTypeByCode(String code) {
        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar el código para buscar");
        }

        DocumentType documentType = documentTypeRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException(String.format("Tipo de documento no encontrado con el código: %s", code)));

        return DocumentTypeMapper.toDocumentTypeResponse(documentType);
    }
}