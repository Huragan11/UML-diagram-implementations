package Bag;
import java.util.Date;

public class RepairAssignment {
    private Mechanic mechanic;
    private Repair repair;
    private Date date;

    public RepairAssignment(Mechanic mechanic, Repair repair, Date date) {
        if (mechanic == null || repair == null || date == null) {
            throw new IllegalArgumentException("Mechanic, repair and date cannot be null");
        }

        this.mechanic = mechanic;
        this.repair = repair;
        this.date = date;

        mechanic.addRepairAssignment(this);
        repair.addRepairAssignment(this);
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public Repair getRepair() {
        return repair;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        this.date = date;
    }

    public void removeAssignment() {
        mechanic.removeRepairAssignment(this);
        repair.removeRepairAssignment(this);
    }
}
