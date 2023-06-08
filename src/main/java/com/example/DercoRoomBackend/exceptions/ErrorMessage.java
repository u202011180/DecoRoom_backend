package com.example.DercoRoomBackend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    private  int statusCode;
    private Date timestamp;
    private String message;
    private String description;
}
