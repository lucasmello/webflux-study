package io.trajano.reactivespring.domain;

import java.math.BigDecimal;

public class PromotionModel {

    private String newPosition;
    private BigDecimal newSalary;
    private Employee newBoss;

    public String getNewPosition() {
        return newPosition;
    }

    public void setNewPosition(String newPosition) {
        this.newPosition = newPosition;
    }

    public BigDecimal getNewSalary() {
        return newSalary;
    }

    public void setNewSalary(BigDecimal newSalary) {
        this.newSalary = newSalary;
    }

    public Employee getNewBoss() {
        return newBoss;
    }

    public void setNewBoss(Employee newBoss) {
        this.newBoss = newBoss;
    }
}
