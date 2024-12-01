package com.msa.rental.domain.model;

import com.msa.rental.domain.model.vo.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class RentalCard {
    private RentalCardNo rentalCardNo;
    private IDName member;
    private RentStatus rentStatus;
    private LateFee lateFee;
    private List<RentalItem> rentalItemList = new ArrayList<>();
    private List<ReturnItem> returnItemList = new ArrayList<>();

    public RentalCard(RentalCardNo rentalCardNo, IDName member, RentStatus rentStatus, LateFee lateFee) {
        this.rentalCardNo =  rentalCardNo;
        this.member = member;
        this.rentStatus = rentStatus;
        this.lateFee = lateFee;
    }

    // 대여 카드 생성
    public static RentalCard createRentalCard(IDName idName) {
        return new RentalCard(
                RentalCardNo.createRentalCardNo(),
                idName,
                RentStatus.RENT_AVAILABLE,
                LateFee.createLateFee()
        );
    }

    // 대여 처리
    public RentalCard rentItem(Item item) {
        checkRentalAvailable();
        this.addRentalItem(RentalItem.createRentalItem(item));
        return this;
    }

    private void addRentalItem(RentalItem rentalItem) {
        rentalItemList.add(rentalItem);
    }

    private void addReturnItem(ReturnItem returnItem) {
        returnItemList.add(returnItem);
    }

    private void removeRentalItem(RentalItem rentalItem) {
        rentalItemList.remove(rentalItem);
    }

    private void checkRentalAvailable() {
        if (rentStatus == RentStatus.RENT_UNAVAILABLE) {
            throw new IllegalArgumentException("대여 불가 상태입니다.");
        }
        if (5 < rentalItemList.size()) {
            throw new IllegalArgumentException("5개 이상 도서를 보유중입니다.");
        }
    }

    public RentalCard returnItem(Item item, LocalDate returnDate) {
        RentalItem rentalItem = rentalItemList.stream()
                .filter(hasItem -> hasItem.equals(item))
                .findFirst()
                .get();
        calculateLateFee(rentalItem, returnDate);
        addReturnItem(ReturnItem.createReturnItem(rentalItem));
        removeRentalItem(rentalItem);
        return this;
    }

    private void calculateLateFee(RentalItem rentalItem, LocalDate returnDate) {
        if (0 < returnDate.compareTo(rentalItem.getOverdueDate())) {
            int point = Period.between(rentalItem.getOverdueDate(), returnDate).getDays() * 10;
            lateFee.addPoint(point);
        }
    }

    public RentalCard overdueItem(Item item) {
        RentalItem rentalItem = rentalItemList.stream()
                .filter(hasItem -> hasItem.equals(item))
                .findFirst()
                .get();

        rentalItem.setOverdued(true);
        rentStatus = RentStatus.RENT_UNAVAILABLE;
        rentalItem.setOverdueDate(LocalDate.now().minusDays(1));
        return this;
    }

    public int makeAvailableRental(int point) throws Exception {
        if (rentalItemList.size() != 0) throw new IllegalArgumentException("모든 도서가 반납 되어야 정지를 해제 할 수 있습니다.");
        if (lateFee.getPoint() != point) throw new IllegalArgumentException("해당 포인트로 연체할 수 없습니다.");

        lateFee = lateFee.removePoint(point);
        if (lateFee.getPoint() == 0) {
            rentStatus = RentStatus.RENT_AVAILABLE;
        }

        return lateFee.getPoint();
    }
}