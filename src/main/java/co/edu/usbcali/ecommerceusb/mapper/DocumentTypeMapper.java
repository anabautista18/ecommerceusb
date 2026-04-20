package co.edu.usbcali.ecommerceusb.mapper;

import co.edu.usbcali.ecommerceusb.dto.DocumentTypeRequest;
import co.edu.usbcali.ecommerceusb.dto.DocumentTypeResponse;
import co.edu.usbcali.ecommerceusb.model.DocumentType;

import java.util.ArrayList;
import java.util.List;

public class DocumentTypeMapper {

    public static DocumentTypeResponse toDocumentTypeResponse(DocumentType documentType) {
        return DocumentTypeResponse.builder()
                .id(documentType.getId().intValue())
                .code(documentType.getCode())
                .name(documentType.getName())
                .build();
    }

    public static List<DocumentTypeResponse> toDocumentTypeResponseList(List<DocumentType> documentTypes) {
        List<DocumentTypeResponse> documentTypeResponseList = new ArrayList<>();
        for (DocumentType documentType : documentTypes) {
            DocumentTypeResponse documentTypeResponse = toDocumentTypeResponse(documentType);
            documentTypeResponseList.add(documentTypeResponse);
        }
        return documentTypeResponseList;
    }

    public static DocumentType toDocumentType(DocumentTypeRequest request) {
        return DocumentType.builder()
                .code(request.getCode())
                .name(request.getName())
                .build();
    }
}
