package Bag;
import java.util.HashSet;
import java.util.Set;


public class Mechanic {
    private String name;
    private Set<RepairAssignment> repairAssignments;

    public Mechanic(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
        this.repairAssignments = new HashSet<>();
    }

    public String getName() {
        return name;
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
        if (repairAssignment.getMechanic() != this) {
            throw new IllegalArgumentException("Repair assignment does not belong to this repair.");
        }
    }
}