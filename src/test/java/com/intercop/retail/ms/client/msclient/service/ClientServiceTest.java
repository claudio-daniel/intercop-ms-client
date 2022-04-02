package com.intercop.retail.ms.client.msclient.service;

import com.intercop.retail.ms.client.msclient.dao.ClientDAO;
import com.intercop.retail.ms.client.msclient.model.api.request.CreateClientRequest;
import com.intercop.retail.ms.client.msclient.model.entity.Client;
import com.intercop.retail.ms.client.msclient.model.transformer.ClientTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ClientServiceTest {

    private ClientService clientService;
    private ClientDAO clientDAO;

    @BeforeEach
    void init() {
        configureMocks();
        configureClientService();
    }

    @Test
    void shouldReturnAListOfClients() {
        when(clientDAO.findAll()).thenReturn(buildClientList());

        var clientList = clientService.findListClients();

        assertNotNull(clientList.getClientList());
        assertTrue(0 < clientList.getClientList().size());
        verify(clientDAO, times(1)).findAll();
    }

    @Test
    void shouldReturnAEmptyListOfClients() {
        var clientListResponse = clientService.findListClients();

        assertNotNull(clientListResponse);
        assertEquals(0, clientListResponse.getClientList().size());
        assertNotNull(clientListResponse.getMessage());
        verify(clientDAO, times(1)).findAll();
    }

    @Test
    void shouldReturnAEmptyListOfClientsByCatchException() {
        doThrow(RuntimeException.class).when(clientDAO).findAll();

        var clientListResponse = clientService.findListClients();

        assertNotNull(clientListResponse);
        assertEquals(0, clientListResponse.getClientList().size());
        assertNotNull(clientListResponse.getMessage());

        verify(clientDAO, times(1)).findAll();
    }

    @Test
    void shouldReturnACreatedClient() {
        when(clientDAO.save(any())).thenReturn(buildClient());

        var request = buildCreateClientRequest();
        var createdClient = clientService.createClient(request);

        assertNotNull(createdClient);
        assertNotNull(createdClient.getMessage());
        assertNotNull(createdClient.getClientCreated().getId());
        assertNotNull(createdClient.getClientCreated().getFirstName());

        verify(clientDAO, times(1)).save(any());
    }

    @Test
    void shouldReturnAErrorCreatedClient() {
        var createdClient = clientService.createClient(null);

        assertNotNull(createdClient);
        assertNotNull(createdClient.getMessage());
        assertNull(createdClient.getClientCreated().getId());

        verify(clientDAO, times(0)).save(any());
    }

    @Test
    void shouldReturnKpiOfClients() {
        when(clientDAO.findAll()).thenReturn(buildClientList());

        var kpiClientsResponse = clientService.getKpiClients();

        assertNotNull(kpiClientsResponse);
        assertNotNull(kpiClientsResponse.getMessage());
        assertNotNull(kpiClientsResponse.getKpiClientsResponse());

        verify(clientDAO, times(1)).findAll();
    }

    @Test
    void shouldReturnKpiOfClientsByCatchException() {
        doThrow(RuntimeException.class).when(clientDAO).findAll();

        var kpiClientsResponse = clientService.getKpiClients();

        assertNotNull(kpiClientsResponse);
        assertNotNull(kpiClientsResponse.getMessage());
        assertNotNull(kpiClientsResponse.getKpiClientsResponse());

        verify(clientDAO, times(1)).findAll();
    }

    private Client buildClient() {
        Client client = new Client();
        client.setId(2L);
        client.setLastName("Claudio");
        client.setFirstName("Carrizo");
        client.setAge(28);
        client.setBirthDate(ZonedDateTime.now());
        return client;
    }

    private List<Client> buildClientList() {
        return List.of(buildClient());
    }

    private CreateClientRequest buildCreateClientRequest() {
        var createClientRequest = new CreateClientRequest();
        createClientRequest.setAge(28);
        createClientRequest.setBirthDate(ZonedDateTime.now());
        createClientRequest.setFirstName("Claudio");
        createClientRequest.setLastName("Carrizo");
        return createClientRequest;
    }

    private void configureMocks() {
        clientDAO = mock(ClientDAO.class);
    }

    private void configureClientService() {
        clientService = new ClientService(clientDAO, new ClientTransformer());
    }
}
