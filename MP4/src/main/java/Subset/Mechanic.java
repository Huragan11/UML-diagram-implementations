package Subset;

import java.util.HashSet;
import java.util.Set;

public class Mechanic {

    private String name;
    private Set<Repair> assignedRepairs;
    private Set<Repair> leadingRepairs;


    public Mechanic(String name) {
        checkName(name);
        this.name = name;
        this.assignedRepairs = new HashSet<>();
        this.leadingRepairs = new HashSet<>();
    }

    private void checkName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
    }

    public String getName() {
        return name;
    }

    public Set<Repair> getAssignedRepairs() {
        return new HashSet<>(assignedRepairs);
    }

    public Set<Repair> getLeadingRepairs() {
        return new HashSet<>(leadingRepairs);
    }

    public void assignRepair(Repair repair) {
        if (repair == null) {
            throw new IllegalArgumentException("Repair cannot be null.");
        }
        this.assignedRepairs.add(repair);
        repair.assignMechanic(this);
    }

    public void unassignRepair(Repair repair) {
        if (repair == null) {
            throw new IllegalArgumentException("Repair cannot be null.");
        }
        if (!assignedRepairs.contains(repair)) {
            return;
        }
        if (leadingRepairs.contains(repair)) {
            leadingRepairs.remove(repair);
        }
        assignedRepairs.remove(repair);
        repair.removeMechanic(this);
    }
    public void leadRepair(Repair repair){
        if (repair == null) {
            throw new IllegalArgumentException("Repair cannot be null.");
        }
        if (!assignedRepairs.contains(repair)) {
            throw new IllegalArgumentException("Mechanic must be assigned to the repair before leading it.");
        }
        if (leadingRepairs.contains(repair)) {
            return;
        }
        leadingRepairs.add(repair);
        repair.addLeader(this);
    }

    public void stopLeadingRepair(Repair repair) {
        if (repair == null) {
            throw new IllegalArgumentException("Repair cannot be null.");
        }
        if (!leadingRepairs.contains(repair)) {
            return;
        }
        leadingRepairs.remove(repair);
        repair.removeLeader(this);
    }
}

