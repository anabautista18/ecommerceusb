package co.edu.usbcali.ecommerceusb.exceptions;

import lombok.Builder;
import lombok.Data;
import java.time.OffsetDateTime;

@Data
@Builder
public class apiError {

    private Integer status;
    private String error;
    private String message;
    private OffsetDateTime timestamp;
}