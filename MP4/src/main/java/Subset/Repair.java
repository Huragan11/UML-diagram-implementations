package Subset;

import java.util.Set;
import java.util.HashSet;

public class Repair {

    private String description;
    private Set<Mechanic> mechanics;
    private Mechanic leader;

    public Repair(String description) {
        checkDescription(description);
        this.description = description;
        this.mechanics = new HashSet<>();
        this.leader = null;
    }

    private void checkDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or empty.");
        }
    }

    public String getDescription() {
        return description;
    }

    public Set<Mechanic> getMechanics() {
        return new HashSet<>(mechanics);
    }

    public Mechanic getLeaders() {
        return leader;
    }

    public void assignMechanic(Mechanic mechanic) {
        if (mechanic == null) {
            throw new IllegalArgumentException("Mechanic cannot be null.");
        }
        mechanics.add(mechanic);
        mechanic.assignRepair(this);
    }

    public void removeMechanic(Mechanic mechanic) {
        if (mechanic == null) {
            throw new IllegalArgumentException("Mechanic cannot be null.");
        }
        if (!mechanics.contains(mechanic)) {
            return;
        }
        if (leader == mechanic) {
            leader = null;
        }
        mechanics.remove(mechanic);
        mechanic.unassignRepair(this);
    }

    public void addLeader(Mechanic mechanic) {
        if (mechanic == null) {
            throw new IllegalArgumentException("Mechanic cannot be null.");
        }
        if (leader == mechanic) {
            return;
        }
        if (!mechanics.contains(mechanic)) {
            throw new IllegalArgumentException("Mechanic must be assigned to the repair before becoming a leader.");
        }
        leader = mechanic;
        mechanic.leadRepair(this);
    }

    public void removeLeader(Mechanic mechanic) {
        if (mechanic == null) {
            throw new IllegalArgumentException("Mechanic cannot be null.");
        }
        if (leader == mechanic) {
            return;
        }
        leader = null;
        mechanic.stopLeadingRepair(this);
    }
}
