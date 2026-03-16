package co.edu.usbcali.ecommerceusb.controller;

import co.edu.usbcali.ecommerceusb.model.DocumentType;
import co.edu.usbcali.ecommerceusb.service.DocumentTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/document-types")
public class DocumentTypeController {

    private final DocumentTypeService documentTypeService;

    // Inyección de dependencias por constructor
    public DocumentTypeController(DocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    // Inyección de dependencias por @Autowired
    @GetMapping
    public List<DocumentType> getAllDocumentTypes() {
        return documentTypeService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<DocumentType> getDocumentTypeById(@PathVariable Long id) {
        return documentTypeService.findById(id);
    }

    @PostMapping
    public DocumentType createDocumentType(@RequestBody DocumentType documentType) {
        return documentTypeService.save(documentType);
    }
}
