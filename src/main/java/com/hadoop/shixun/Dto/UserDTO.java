package com.hadoop.shixun.Dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class UserDTO {
    private String id;
    private String username;
    private String password;
}
