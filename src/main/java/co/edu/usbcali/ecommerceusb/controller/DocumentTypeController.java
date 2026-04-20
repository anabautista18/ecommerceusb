package co.edu.usbcali.ecommerceusb.controller;

import co.edu.usbcali.ecommerceusb.dto.DocumentTypeRequest;
import co.edu.usbcali.ecommerceusb.dto.DocumentTypeResponse;
import co.edu.usbcali.ecommerceusb.mapper.DocumentTypeMapper;
import co.edu.usbcali.ecommerceusb.model.DocumentType;
import co.edu.usbcali.ecommerceusb.service.DocumentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/document-types")
@RequiredArgsConstructor
public class DocumentTypeController {

    private final DocumentTypeService documentTypeService;

    @GetMapping
    public ResponseEntity<List<DocumentTypeResponse>> getAllDocumentTypes() {
        List<DocumentType> documentTypes = documentTypeService.findAll();
        List<DocumentTypeResponse> responses = DocumentTypeMapper.toDocumentTypeResponseList(documentTypes);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentTypeResponse> getDocumentTypeById(@PathVariable Long id) {
        Optional<DocumentType> documentType = documentTypeService.findById(id);
        if (documentType.isPresent()) {
            DocumentTypeResponse response = DocumentTypeMapper.toDocumentTypeResponse(documentType.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<DocumentTypeResponse> createDocumentType(@RequestBody DocumentTypeRequest request) {
        DocumentType documentType = DocumentTypeMapper.toDocumentType(request);
        DocumentType saved = documentTypeService.save(documentType);
        DocumentTypeResponse response = DocumentTypeMapper.toDocumentTypeResponse(saved);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
