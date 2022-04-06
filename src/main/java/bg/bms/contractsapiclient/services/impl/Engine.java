package bg.bms.contractsapiclient.services.impl;

import bg.bms.contractsapiclient.models.ContractModel;
import bg.bms.contractsapiclient.models.Status;
import bg.bms.contractsapiclient.services.ClientService;
import bg.bms.contractsapiclient.utils.ConsoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import java.io.IOException;

@Component
public class Engine implements Runnable {
    private final ConsoleService console;
    private final ClientService client;

    public Engine(ConsoleService console,
                  ClientService client) {
        this.console = console;
        this.client = client;
    }

    @Override
    public void run() {
        console.printInfoMessage("Client started...");
        int i = 0;
        try {
            printMenu();
            i = console.readIntFromConsole("Please enter operation number: ");
            while (i != 0) {
                try {
                    switch (i) {
                        case 1:
                            callRestService("Hello world!");
                            break;
                        case 2:
                            callRestService(null);
                            break;
                        case 3:
                            callRestService("Hello world!", null);
                            break;
                        case 4:
                            callRestService("Hello world!", null, true);
                            break;
                    }
                    System.out.println();
                    printMenu();
                    i = console.readIntFromConsole("Please enter operation number: ");
                } catch (IOException | NumberFormatException e) {
                    console.printErrorMessage(String.format("%s - %s", e.getClass().getSimpleName(), e.getMessage()));
                }
            }

            console.printInfoMessage("Client stopped...");
            return;
        } catch (IOException | NumberFormatException e) {
            console.printErrorMessage(String.format("%s - %s", e.getClass().getSimpleName(), e.getMessage()));
        }
    }

    private void printMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("********************************").append(System.lineSeparator());
        sb.append("*     Available functions      *").append(System.lineSeparator());
        sb.append("********************************").append(System.lineSeparator());
        sb.append("*  1. Correct put request      *").append(System.lineSeparator());
        sb.append("*  2. Put empty comment        *").append(System.lineSeparator());
        sb.append("*  3. Put Status null          *").append(System.lineSeparator());
        sb.append("*  4. Put empty body           *").append(System.lineSeparator());
        sb.append("*  0. Exit                     *").append(System.lineSeparator());
        sb.append("********************************");
        console.printInfoMessage(sb.toString());
    }

    private void callRestService(String comment, Status status, boolean withEmptyBody) {
        try {
            ResponseEntity<String> response;
            if (withEmptyBody){
                response = this.client.someRestCallWithEmptyBody(comment, status);
            }else{
            response = this.client.someRestCall(
                    ClientServiceImpl.DEFAULT_CONTRACT_ID,
                    status,
                    comment);
            }

            String message = response.getStatusCode().value() + " " + response.getStatusCode().getReasonPhrase();
            console.printSuccessMessage(message);

        } catch (RestClientException ex) {
            console.printErrorMessage(ex.getLocalizedMessage());
        }
    }

    private void callRestService(String comment, Status status) {
        callRestService(comment, status, false);
    }

    private void callRestService(String comment) {
        callRestService(comment, Status.REJECTED,false);
    }


    private void printMessage(ContractModel contractModel) {
        console.printInfoMessage(contractModel.getComment());
    }
}
