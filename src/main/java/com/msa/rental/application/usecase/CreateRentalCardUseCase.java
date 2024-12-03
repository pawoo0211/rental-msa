package com.msa.rental.application.usecase;

import com.msa.rental.framework.web.dto.RentalCardOutPutDTO;
import com.msa.rental.framework.web.dto.UserInPutDTO;

public interface CreateRentalCardUseCase {
    RentalCardOutPutDTO createRentalCard(UserInPutDTO userInPutDTO);
}
