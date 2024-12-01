package com.msa.rental.domain.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LateFee {
    private int point;

    public static LateFee createLateFee() {
        return new LateFee(0);
    }

    public LateFee addPoint(int point) {
        return new LateFee(this.point + point);
    }

    public LateFee removePoint(int point) throws Exception {
        if (this.point < point) {
            throw new Exception("현재 포인트보다 삭감 포인트 금액이 더 큽니다.");
        }

        return new LateFee(this.point - point);
    }
}
