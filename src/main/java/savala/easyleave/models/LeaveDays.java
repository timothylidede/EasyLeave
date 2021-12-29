package savala.easyleave.models;

public class LeaveDays {
    int totalDays,daysTaken,daysRemaining,bonusDays;

    public LeaveDays(int totalDays, int daysTaken, int daysRemaining, int bonusDays) {
        this.totalDays = totalDays;
        this.daysTaken = daysTaken;
        this.daysRemaining = daysRemaining;
        this.bonusDays = bonusDays;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public int getDaysTaken() {
        return daysTaken;
    }

    public void setDaysTaken(int daysTaken) {
        this.daysTaken = daysTaken;
    }

    public int getRemainingDays() {
        return totalDays-daysTaken;
    }

    public void setRemaining(int daysRemaining) {
        this.daysRemaining = daysRemaining;
    }

    public int getBonusDays() {
        return bonusDays;
    }

    public void setBonusDays(int bonusDays) {
        this.bonusDays = bonusDays;
    }
}
