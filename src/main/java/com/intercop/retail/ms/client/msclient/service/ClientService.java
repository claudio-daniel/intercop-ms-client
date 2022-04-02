package com.intercop.retail.ms.client.msclient.service;

import com.intercop.retail.ms.client.msclient.dao.ClientDAO;
import com.intercop.retail.ms.client.msclient.model.api.request.CreateClientRequest;
import com.intercop.retail.ms.client.msclient.model.api.response.*;
import com.intercop.retail.ms.client.msclient.model.entity.Client;
import com.intercop.retail.ms.client.msclient.model.transformer.ClientTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.intercop.retail.ms.client.msclient.model.constant.ClientFluxMessage.*;

@Service
public class ClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);
    private final ClientDAO clientDAO;
    private final ClientTransformer clientTransformer;

    @Autowired
    public ClientService(ClientDAO clientDAO, ClientTransformer clientTransformer) {
        this.clientDAO = clientDAO;
        this.clientTransformer = clientTransformer;
    }

    public ClientResponseList findListClients() {
        LOGGER.info("Search list of clients");

        try {
            var clientResponseList = clientDAO.findAll()
                    .stream()
                    .map(clientTransformer)
                    .map(this::calculateDeathDate)
                    .collect(Collectors.toList());

            return clientResponseList.isEmpty() ?
                    new ClientResponseList(clientResponseList, CLIENT_LIST_NOT_FOUND) :
                    new ClientResponseList(clientResponseList, CLIENT_LIST_OK);
        } catch (Exception e) {
            LOGGER.error("Error while searching the clients list.", e);
            return new ClientResponseList(new ArrayList<>(), CLIENT_LIST_NOT_FOUND);
        }
    }

    public CreateClientResponse createClient(CreateClientRequest createClientRequest) {
        LOGGER.info("Crate a client");
        try {
            var client = clientDAO.save(clientTransformer.fromClientRequestToClient(createClientRequest));

            return new CreateClientResponse(clientTransformer.apply(client), CREATED_CLIENT_OK);
        } catch (Exception e) {
            LOGGER.error("Error to create client", e);
            return new CreateClientResponse(new ClientResponse(), CREATED_CLIENT_ERROR);
        }
    }

    public FindKpiClientsResponse getKpiClients() {
        try {
            LOGGER.info("Calculating standard deviation");
            var clientList = clientDAO.findAll();

            var averageClients = calculateAverageClients(clientList);
            var standardDeviation = calculateStandardDeviation(clientList, averageClients);

            var kpiClients = new KpiClientsResponse();
            kpiClients.setAverage(averageClients);
            kpiClients.setStandardDeviation(standardDeviation);

            return new FindKpiClientsResponse(kpiClients, KPI_CLIENTS_OK);
        } catch (Exception e) {
            LOGGER.error("Error while calculating standard deviation", e);
            return new FindKpiClientsResponse(new KpiClientsResponse(), KPI_CLIENTS_ERROR);
        }
    }

    private ClientResponse calculateDeathDate(ClientResponse clientResponse) {
        clientResponse.setEstimatedDateDeath(clientResponse.getBirthDate().plusYears(100));
        return clientResponse;
    }

    private Double calculateStandardDeviation(List<Client> clientList, Double averageClients) {
        var standardDeviation = 0.0;

        if (clientList.size() > 0) {
            var deviations = clientList
                    .stream()
                    .mapToDouble(client -> Math.pow((client.getAge() - averageClients), 2.0))
                    .sum();

            standardDeviation = Math.sqrt(deviations / clientList.size());
        }

        return standardDeviation;
    }

    private Double calculateAverageClients(List<Client> clientList) {
        var sumOfAges = clientList
                .stream()
                .mapToInt(Client::getAge)
                .sum();

        return clientList.size() > 0 ? sumOfAges / clientList.size() : 0.0;
    }
}
