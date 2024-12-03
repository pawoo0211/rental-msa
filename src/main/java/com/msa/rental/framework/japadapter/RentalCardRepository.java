package com.msa.rental.framework.japadapter;

import com.msa.rental.domain.model.RentalCard;
import com.msa.rental.domain.model.vo.RentalCardNo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalCardRepository extends JpaRepository<RentalCard, RentalCardNo> {
}
