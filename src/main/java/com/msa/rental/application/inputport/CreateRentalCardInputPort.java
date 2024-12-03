package com.msa.rental.application.inputport;

import com.msa.rental.application.outputport.RentalCardOutPutPort;
import com.msa.rental.application.usecase.CreateRentalCardUseCase;
import com.msa.rental.framework.web.dto.RentalCardOutPutDTO;
import com.msa.rental.framework.web.dto.UserInPutDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateRentalCardInputPort implements CreateRentalCardUseCase {
    private final RentalCardOutPutPort rentalCardOutPutPort;

    @Override
    public RentalCardOutPutDTO createRentalCard(UserInPutDTO userInPutDTO) {
        return null;
    }
}
