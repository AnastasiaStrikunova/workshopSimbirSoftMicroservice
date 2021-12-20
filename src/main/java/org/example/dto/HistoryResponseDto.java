package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HistoryResponseDto {
    Long idHistory;
    UUID personalAccount;
    Long amount;
    String typeOfTransaction;
    Date time;
}
