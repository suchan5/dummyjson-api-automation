package com.suchan.qa.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentUserResponse {
    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;

}
