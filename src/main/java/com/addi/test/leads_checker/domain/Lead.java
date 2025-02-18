package com.addi.test.leads_checker.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Lead {
    Long id;
    String nationalIdNumber;
    LocalDateTime dateOfBirth;
    String firstName;
    String lastName;
    String email;
}
