package com.shaadielephant.shaadielephant.budget_item;

/**
 * Created by Callndata on 2/19/2016.
 */
public class customclass_budget_details {
    //for budget
    String marriageDate,BudgetfullName;
    int budgetID,TotalBudget,budget_plannedCost,budget_availbleAmount,budget_actualAmount,budget_paidAmount,budget_pendingAmount;

    public String getMarriageDate() {
        return marriageDate;
    }

    public void setMarriageDate(String marriageDate) {
        this.marriageDate = marriageDate;
    }

    public String getBudgetfullName() {
        return BudgetfullName;
    }

    public void setBudgetfullName(String budgetfullName) {
        BudgetfullName = budgetfullName;
    }

    public int getBudgetID() {
        return budgetID;
    }

    public void setBudgetID(int budgetID) {
        this.budgetID = budgetID;
    }

    public int getTotalBudget() {
        return TotalBudget;
    }

    public void setTotalBudget(int totalBudget) {
        TotalBudget = totalBudget;
    }

    public int getBudget_plannedCost() {
        return budget_plannedCost;
    }

    public void setBudget_plannedCost(int budget_plannedCost) {
        this.budget_plannedCost = budget_plannedCost;
    }

    public int getBudget_availbleAmount() {
        return budget_availbleAmount;
    }

    public void setBudget_availbleAmount(int budget_availbleAmount) {
        this.budget_availbleAmount = budget_availbleAmount;
    }

    public int getBudget_actualAmount() {
        return budget_actualAmount;
    }

    public void setBudget_actualAmount(int budget_actualAmount) {
        this.budget_actualAmount = budget_actualAmount;
    }

    public int getBudget_paidAmount() {
        return budget_paidAmount;
    }

    public void setBudget_paidAmount(int budget_paidAmount) {
        this.budget_paidAmount = budget_paidAmount;
    }

    public int getBudget_pendingAmount() {
        return budget_pendingAmount;
    }

    public void setBudget_pendingAmount(int budget_pendingAmount) {
        this.budget_pendingAmount = budget_pendingAmount;
    }
}
