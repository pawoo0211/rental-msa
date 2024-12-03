package com.msa.rental.framework.japadapter;

import com.msa.rental.application.outputport.RentalCardOutPutPort;
import com.msa.rental.domain.model.RentalCard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RentalCardOutPutAdapter implements RentalCardOutPutPort {
    private final RentalCardRepository rentalCardRepository;

    @Override
    public Optional<RentalCard> loadRentalCard(String userId) {
        return Optional.empty();
    }

    @Override
    public RentalCard save(RentalCard rentalCard) {
        return null;
    }
}
