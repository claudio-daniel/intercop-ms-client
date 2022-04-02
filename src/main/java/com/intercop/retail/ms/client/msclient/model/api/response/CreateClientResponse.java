package com.intercop.retail.ms.client.msclient.model.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CreateClientResponse {

    @ApiModelProperty(value = "Client Created Response")
    ClientResponse clientCreated;

    @ApiModelProperty(value = "Message", example = "Client created")
    String message;

    public CreateClientResponse(ClientResponse clientCreated, String message) {
        this.clientCreated = clientCreated;
        this.message = message;
    }
}
