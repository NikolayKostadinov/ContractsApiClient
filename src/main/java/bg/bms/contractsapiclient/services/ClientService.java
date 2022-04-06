package bg.bms.contractsapiclient.services;

import bg.bms.contractsapiclient.models.Status;
import org.springframework.http.ResponseEntity;

public interface ClientService{
    ResponseEntity<String> someRestCall(String id, Status status, String comment);
    ResponseEntity<String> someRestCallWithEmptyBody(String id, Status status);
}
