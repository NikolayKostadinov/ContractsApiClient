package bg.bms.contractsapiclient.services.impl;

import bg.bms.contractsapiclient.models.ContractModel;
import bg.bms.contractsapiclient.models.Status;
import bg.bms.contractsapiclient.services.ClientService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClientServiceImpl implements ClientService {
    public static final String DEFAULT_CONTRACT_ID = "dbd3ec2f-d742-46c0-bc30-ff202c4b5b00";
    public static final String URL = "https://extcontractsapi.public.fscc" + ".lt/api/documentum/contract/%s/status/%d";


    private final RestTemplate restTemplate;

    public ClientServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<String> someRestCall(String id, Status status, String comment) {
        HttpHeaders headers = initializeHeaders();

        ContractModel contract = new ContractModel(comment);
        HttpEntity<ContractModel> contractRequest = new HttpEntity<>(contract, headers);
        String url = String.format(URL, id, status == null ? 0 : status.getValue());
        return restTemplate.exchange(url, HttpMethod.PUT, contractRequest, String.class, id);
    }

    private HttpHeaders initializeHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "API-KEY 114E83F7-E44E-4548-95D7-AB654B3486A0");
        return headers;
    }

    @Override
    public ResponseEntity<String> someRestCallWithEmptyBody(String id, Status status) {
        HttpHeaders headers = initializeHeaders();

        HttpEntity<ContractModel> contractRequest = new HttpEntity<>(headers);
        String url = String.format(URL, id, status == null ? 0 : status.getValue());
        return restTemplate.exchange(url, HttpMethod.PUT, contractRequest, String.class, id);
    }
}
