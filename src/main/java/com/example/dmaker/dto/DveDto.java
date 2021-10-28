package com.example.dmaker.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * @author dsg
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class DveDto {

    String firstName;
    Integer age;
    Integer experienceYears;
    LocalDateTime startAt;

    public void printLog() {
        log.info(getFirstName());
    }

}
