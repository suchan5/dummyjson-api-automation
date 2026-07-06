package com.suchan.qa.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsersResponse {
    private List<CurrentUserResponse> users;
    private Integer total;
    private Integer skip;
    private Integer limit;
}
