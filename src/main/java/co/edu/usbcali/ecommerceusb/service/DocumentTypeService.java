package co.edu.usbcali.ecommerceusb.service;

import co.edu.usbcali.ecommerceusb.model.DocumentType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentTypeService {

    private List<DocumentType> documentTypes = new ArrayList<>();

    public DocumentTypeService() {
        documentTypes.add(DocumentType.builder()
                .id(1L)
                .code("CC")
                .name("Cédula de Ciudadanía")
                .build());
        documentTypes.add(DocumentType.builder()
                .id(2L)
                .code("TI")
                .name("Tarjeta de Identidad")
                .build());
    }

    public List<DocumentType> findAll() {
        return documentTypes;
    }

    public Optional<DocumentType> findById(Long id) {
        return documentTypes.stream().filter(dt -> dt.getId().equals(id)).findFirst();
    }

    public DocumentType save(DocumentType documentType) {
        documentType.setId((long) (documentTypes.size() + 1));
        documentTypes.add(documentType);
        return documentType;
    }
}