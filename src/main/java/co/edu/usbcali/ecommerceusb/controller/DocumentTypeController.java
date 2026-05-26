package co.edu.usbcali.ecommerceusb.controller;

import co.edu.usbcali.ecommerceusb.dto.DocumentTypeRequest;
import co.edu.usbcali.ecommerceusb.dto.DocumentTypeResponse;
import co.edu.usbcali.ecommerceusb.service.DocumentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/document-types")
@RequiredArgsConstructor
public class DocumentTypeController {

    private final DocumentTypeService documentTypeService;

    @GetMapping
    public ResponseEntity<List<DocumentTypeResponse>> getAllDocumentTypes() {
        List<DocumentTypeResponse> responses = documentTypeService.getAllDocumentTypes();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentTypeResponse> getDocumentTypeById(@PathVariable Long id) {
        DocumentTypeResponse response = documentTypeService.getDocumentTypeById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DocumentTypeResponse> createDocumentType(@RequestBody DocumentTypeRequest request) {
        DocumentTypeResponse created = documentTypeService.save(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentTypeResponse> updateDocumentType(@PathVariable Long id, @RequestBody DocumentTypeRequest request) {
        return new ResponseEntity<>(documentTypeService.update(id, request), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocumentType(@PathVariable Long id) {
        documentTypeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}





