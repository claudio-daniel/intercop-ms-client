package com.intercop.retail.ms.client.msclient.model.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Data
public class CreateClientRequest {

    @NotNull(message = "First name is required")
    @ApiModelProperty(name = "firstName", dataType = "string", example = "AZ", value = "The name of the client")
    private String firstName;

    @NotNull(message = "Last name is required")
    @ApiModelProperty(name = "lastName", dataType = "string", example = "AZ", value = "The last name of the client")
    private String lastName;

    @NotNull(message = "Age is required")
    @ApiModelProperty(name = "age", dataType = "integer", example = "99", value = "The age of the client")
    private Integer age;

    @NotNull(message = "Birth date is required")
    @ApiModelProperty(name = "birthDate", dataType = "date", example = "2021-10-21T21:51:45.998+00:00", value = "Birth date of the client")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ")
    private ZonedDateTime birthDate;
}
