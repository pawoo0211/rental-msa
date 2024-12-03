package com.msa.rental.framework.web;

import com.msa.rental.application.usecase.CreateRentalCardUseCase;
import com.msa.rental.application.usecase.OverdueItemUseCase;
import com.msa.rental.framework.web.dto.RentalCardOutPutDTO;
import com.msa.rental.framework.web.dto.UserInPutDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RentalController {
    private final CreateRentalCardUseCase createRentalCardUseCase;
    private final OverdueItemUseCase overdueItemUseCase;

    @PostMapping("/rentalCards")
    public ResponseEntity<RentalCardOutPutDTO> createRentalCard(@RequestBody UserInPutDTO userInPutDTO) {
        return ResponseEntity.ok(createRentalCardUseCase.createRentalCard(userInPutDTO));
    }
}
