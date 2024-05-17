package com.example.r2s_mobile_store.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserDTO extends RegisterDTO {
    private long id;
}
