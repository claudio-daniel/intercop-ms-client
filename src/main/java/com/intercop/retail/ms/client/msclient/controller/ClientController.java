package com.intercop.retail.ms.client.msclient.controller;

import com.intercop.retail.ms.client.msclient.model.api.request.CreateClientRequest;
import com.intercop.retail.ms.client.msclient.model.api.response.ClientResponse;
import com.intercop.retail.ms.client.msclient.model.api.response.ClientResponseList;
import com.intercop.retail.ms.client.msclient.model.api.response.CreateClientResponse;
import com.intercop.retail.ms.client.msclient.model.api.response.FindKpiClientsResponse;
import com.intercop.retail.ms.client.msclient.service.ClientService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.intercop.retail.ms.client.msclient.model.constant.HttpCodesConstant.*;

@Api(tags = "Client Controller", value = "Services to manage clients")
@RestController
public class ClientController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    public ClientService clientService;

    @ApiOperation(value = "Get all clients", response = ClientResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = STATUS_OK, message = "Ok"),
            @ApiResponse(code = STATUS_BAD_REQUEST, message = "Bad Request"),
            @ApiResponse(code = STATUS_FORBIDDEN, message = "Full authentication is required to access this resource"),
    })
    @GetMapping("listclientes")
    public ResponseEntity<ClientResponseList> findListClient() {
        LOGGER.info("Finding list of clients");
        var listClientsResponse = clientService.findListClients();

        return new ResponseEntity<>(listClientsResponse, HttpStatus.OK);
    }

    @ApiOperation(value = "Get kpi of clients", response = ClientResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = STATUS_OK, message = "Ok"),
            @ApiResponse(code = STATUS_BAD_REQUEST, message = "Bad Request"),
            @ApiResponse(code = STATUS_FORBIDDEN, message = "Full authentication is required to access this resource"),
    })
    @GetMapping("kpideclientes")
    public ResponseEntity<FindKpiClientsResponse> getKpiClients() {
        LOGGER.info("Finding kpi of clients");
        var kpiClientsResponse = clientService.getKpiClients();

        return new ResponseEntity<>(kpiClientsResponse, HttpStatus.OK);
    }

    @ApiOperation(value = "Create client", response = ClientResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = STATUS_OK, message = "Ok"),
            @ApiResponse(code = STATUS_BAD_REQUEST, message = "Bad Request"),
            @ApiResponse(code = STATUS_FORBIDDEN, message = "Full authentication is required to access this resource"),
    })
    @PostMapping("creacliente")
    public ResponseEntity<CreateClientResponse> createClient(@ApiParam(value = "Client to create")
                                                             @Validated @RequestBody final CreateClientRequest createClientRequest) {
        LOGGER.info("Creating a client");
        var clientsResponse = clientService.createClient(createClientRequest);

        return new ResponseEntity<>(clientsResponse, HttpStatus.CREATED);
    }

    @Autowired
    public void setClientService(final ClientService clientService) {
        this.clientService = clientService;
    }
}
