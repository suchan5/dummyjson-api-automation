package com.suchan.qa.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserResponse {
    private String name;
    private String job;
    private String id;
    private String createdAt;
}
