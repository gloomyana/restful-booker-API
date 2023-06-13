package ru.gloomyana.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class AuthRequestModel {
    private String username, password;
}
