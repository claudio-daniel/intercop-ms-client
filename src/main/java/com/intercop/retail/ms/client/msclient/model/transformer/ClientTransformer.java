package com.intercop.retail.ms.client.msclient.model.transformer;

import com.intercop.retail.ms.client.msclient.model.api.request.CreateClientRequest;
import com.intercop.retail.ms.client.msclient.model.api.response.ClientResponse;
import com.intercop.retail.ms.client.msclient.model.entity.Client;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ClientTransformer implements Function<Client, ClientResponse> {

    @Override
    public ClientResponse apply(Client client) {
        var clientResponse = new ClientResponse();
        clientResponse.setId(client.getId().toString());
        clientResponse.setAge(client.getAge());
        clientResponse.setBirthDate(client.getBirthDate());
        clientResponse.setFirstName(client.getFirstName());
        clientResponse.setLastName(client.getLastName());
        return clientResponse;
    }

    public Client fromClientRequestToClient(CreateClientRequest createClientRequest) {
        var client = new Client();
        client.setAge(createClientRequest.getAge());
        client.setBirthDate(createClientRequest.getBirthDate());
        client.setFirstName(createClientRequest.getFirstName());
        client.setLastName(createClientRequest.getLastName());

        return client;
    }
}
