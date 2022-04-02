package com.intercop.retail.ms.client.msclient.model.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ClientResponseList {

    @ApiModelProperty(value = "List of Client Response")
    List<ClientResponse> clientList;

    @ApiModelProperty(value = "Message", example = "Client created")
    String message;

    public ClientResponseList(List<ClientResponse> clientList, String message) {
        this.clientList = clientList;
        this.message = message;
    }
}
