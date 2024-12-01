package com.msa.rental.domain.model;

import com.msa.rental.domain.model.vo.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
public class RentalItem {
    private Item item;
    private LocalDate rentDate;
    @Setter private boolean overdued;
    @Setter @Getter private LocalDate overdueDate;

    public static RentalItem createRentalItem(Item item) {
        return new RentalItem(
                item,
                LocalDate.now(),
                false,
                LocalDate.now().plusDays(14)
        );
    }
}
