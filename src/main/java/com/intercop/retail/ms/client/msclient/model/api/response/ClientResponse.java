package com.intercop.retail.ms.client.msclient.model.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ClientResponse {
    @ApiModelProperty(value = "Client Id", example = "1")
    private String id;

    @ApiModelProperty(value = "First name of client", example = "1")
    private String firstName;

    @ApiModelProperty(value = "Last name of client", example = "1")
    private String lastName;

    @ApiModelProperty(value = "Age of client", example = "1")
    private Integer age;

    @ApiModelProperty(value = "Birth date of client", example = "2021-10-21T21:51:45.998+00:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ")
    private ZonedDateTime birthDate;

    @ApiModelProperty(value = "Estimated date of death", example = "2021-10-21T21:51:45.998+00:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ")
    private ZonedDateTime estimatedDateDeath;
}
