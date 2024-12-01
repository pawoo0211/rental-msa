package com.msa.rental.domain.model.vo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
public class RentalCardNo {
    private String no;

    public static RentalCardNo createRentalCardNo() {
        String year = String.valueOf(LocalDate.now().getYear());
        String uuid = UUID.randomUUID().toString();
        return new RentalCardNo(year + uuid);
    }
}
