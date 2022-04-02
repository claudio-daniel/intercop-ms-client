package com.intercop.retail.ms.client.msclient.model.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class KpiClientsResponse {

    @ApiModelProperty(value = "Average Age of clients", example = "1")
    private Double average;

    @ApiModelProperty(value = "Standard Deviation of clients", example = "1")
    private Double standardDeviation;
}
