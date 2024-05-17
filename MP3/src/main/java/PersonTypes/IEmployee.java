package PersonTypes;

import java.time.LocalDate;

public interface IEmployee {
    double getSalary();
    String getSpecialization();
    int getMonthsOfWork();
    LocalDate getHireDate();
    void changeRoleToMechanic(String specialization);
}
