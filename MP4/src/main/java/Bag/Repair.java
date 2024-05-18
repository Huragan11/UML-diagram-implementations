package Bag;

import java.util.HashSet;
import java.util.Set;

public class Repair {
    private String description;
    private Set<RepairAssignment> repairAssignments;

    public Repair(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or empty.");
        }
        this.description = description;
        this.repairAssignments = new HashSet<>();
    }

    public String getDescription() {
        return description;
    }

    public Set<RepairAssignment> getRepairAssignments() {
        return new HashSet<>(repairAssignments);
    }

    public void addRepairAssignment(RepairAssignment repairAssignment) {
        checkRepairAssignment(repairAssignment);
        repairAssignments.add(repairAssignment);
    }

    public void removeRepairAssignment(RepairAssignment repairAssignment) {
        checkRepairAssignment(repairAssignment);
        repairAssignments.remove(repairAssignment);
    }

    private void checkRepairAssignment(RepairAssignment repairAssignment) {
        if (repairAssignment == null) {
            throw new IllegalArgumentException("Repair assignment cannot be null.");
        }
        if (repairAssignment.getRepair() != this) {
            throw new IllegalArgumentException("Repair assignment does not belong to this repair.");
        }
    }
}
