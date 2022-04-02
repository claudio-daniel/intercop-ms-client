package com.intercop.retail.ms.client.msclient.model.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FindKpiClientsResponse {

    @ApiModelProperty(value = "Clients kpi data")
    private KpiClientsResponse kpiClientsResponse;

    @ApiModelProperty(value = "Message", example = "Kpi Ok")
    private String message;

    public FindKpiClientsResponse(KpiClientsResponse kpiClientsResponse, String message) {
        this.kpiClientsResponse = kpiClientsResponse;
        this.message = message;
    }
}
