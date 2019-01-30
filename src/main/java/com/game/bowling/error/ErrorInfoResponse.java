package com.game.bowling.error;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;

/**
 * Error model returned by exception handler.
 */
@AllArgsConstructor
@ApiModel
@Builder
public class ErrorInfoResponse {

    @NonNull
    @ApiModelProperty(required = true, notes = "The URL of the request.")
    public String url;

    @ApiModelProperty(required = true, notes = "The error code.")
    public String errorCode;

    @NonNull
    @ApiModelProperty(required = true, notes = "The exception model that was thrown.")
    public String exception;

    @ApiModelProperty(notes = "Error messages to be shown to the user")
    public String message;
}
