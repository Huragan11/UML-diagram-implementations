package PersonTypes;

import CarTypes.Car;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static PersonTypes.PersonType.*;
import static PersonTypes.EmployeeType.*;


public class Person implements IClient, IEmployee, IOwner {

    private String name;
    private String pesel;
    private String phoneNumber;


    private double salary;
    private LocalDate hireDate;
    private static int minSalary = 2000;

    private int numberOfGarages;

    private List<Car> cars;

    private int monthsOfWork;
    private static int minMonthsOfWorkToBePromoted = 12;

    private String specialization;

    private Set<PersonType> roles;

    private EmployeeType employeeType;


    public Person(String name, String pesel, String phoneNumber, Set<PersonType> roles, EmployeeType employeeType, double salary, LocalDate hireDate, int numberOfGarages, int monthsOfWork, String specialization, List<Car> cars) {
        CheckArguments(name, pesel, phoneNumber);
        this.name = name;
        this.pesel = pesel;
        this.phoneNumber = phoneNumber;

        CheckAndAssignValuesDependingOnRoles(roles, employeeType, salary, hireDate, numberOfGarages, monthsOfWork, specialization, cars);
    }

    private void CheckAndAssignValuesDependingOnRoles(Set<PersonType> roles, EmployeeType employeeType, double salary, LocalDate hireDate, int numberOfGarages, int monthsOfWork, String specialization, List<Car> cars) {
        for (PersonType role : roles) {
            switch (role) {
                case CLIENT:
                    CheckClientArgs(cars);
                    this.roles.add(CLIENT);
                    this.cars = cars;
                    break;

                case OWNER:
                    CheckOwnerArgs(numberOfGarages);
                    this.roles.add(OWNER);
                    this.numberOfGarages = numberOfGarages;
                    break;

                case EMPLOYEE:
                    CheckEmployeeArgs(salary, hireDate);
                    this.roles.add(EMPLOYEE);
                    this.salary = salary;
                    this.hireDate = hireDate;

                    switch (employeeType) {
                        case ASSISTENT:
                            CheckAssistentArgs(monthsOfWork);
                            this.employeeType = ASSISTENT;
                            this.monthsOfWork = monthsOfWork;
                            break;

                        case MECHANIC:
                            CheckMechanicArgs(specialization);
                            this.employeeType = MECHANIC;
                            this.specialization = specialization;
                            break;

                        default:
                            throw new IllegalArgumentException("Invalid employee type.");
                    }
                    break;

                default:
                    throw new IllegalArgumentException("Invalid person type.");
            }
        }
    }

    private static void CheckClientArgs(List<Car> cars) {
        if (cars == null || cars.isEmpty()) {
            throw new IllegalArgumentException("Cars list cannot be null or empty.");
        }
    }

    private static void CheckOwnerArgs(int numberOfGarages) {
        if (numberOfGarages < 0) {
            throw new IllegalArgumentException("Insurance cost cannot be lesser than 0.");
        }
    }

    private static void CheckMechanicArgs(String specialization) {
        if (specialization == null || specialization.isBlank()) {
            throw new IllegalArgumentException("Specialization cannot be null or empty.");
        }
    }

    private static void CheckAssistentArgs(int monthsOfWork) {
        if (monthsOfWork < 0) {
            throw new IllegalArgumentException("Months of work cannot be lesser than 0.");
        }
    }

    private void CheckEmployeeArgs(double salary, LocalDate hireDate) {
        if (salary < minSalary) {
            throw new IllegalArgumentException("Salary cannot be lesser than the minimum.");
        }
        if (hireDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Hire date cannot be in the future.");
        }
    }

    private void CheckArguments(String name, String pesel, String phoneNumber) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (pesel == null || pesel.isBlank()) {
            throw new IllegalArgumentException("PESEL cannot be null or empty.");
        }
        if (phoneNumber == null || phoneNumber.isBlank() || !phoneNumber.matches("\\d{9}")) {
            throw new IllegalArgumentException("Phone number cannot be null or empty.");
        }
    }

    public String getName() {
        return name;
    }

    public String getPesel() {
        return pesel;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Set<PersonType> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    @Override
    public List<Car> getCars() {
        if (!roles.contains(CLIENT)) {
            throw new UnsupportedOperationException("Only clients have cars.");
        }
        return cars;
    }

    @Override
    public LocalDate getHireDate() {
        if (!roles.contains(EMPLOYEE)) {
            throw new UnsupportedOperationException("Only clients have cars.");
        }
        return hireDate;
    }

    @Override
    public void changeRoleToMechanic(String specialization) {
        if (!roles.contains(EMPLOYEE)) {
            throw new UnsupportedOperationException("Only employees can change their role.");
        }
        if (employeeType != ASSISTENT) {
            throw new UnsupportedOperationException("Only assistants can change their role.");
        }
        if (monthsOfWork < minMonthsOfWorkToBePromoted) {
            throw new UnsupportedOperationException("Only assistants with at least 12 months of work can be promoted.");
        }
        CheckMechanicArgs(specialization);
        employeeType = MECHANIC;
        this.specialization = specialization;
        monthsOfWork = -1;
    }

    @Override
    public double getSalary() {
        if (!roles.contains(EMPLOYEE)) {
            throw new UnsupportedOperationException("Only employees have a salary.");
        }
        return salary;
    }

    @Override
    public String getSpecialization() {
        if (!roles.contains(EMPLOYEE) || employeeType != EmployeeType.MECHANIC) {
            throw new UnsupportedOperationException("Only mechanic employees have a specialization.");
        }
        return specialization;
    }

    @Override
    public int getMonthsOfWork() {
        if (!roles.contains(EMPLOYEE) || employeeType != EmployeeType.ASSISTENT) {
            throw new UnsupportedOperationException("Only assistant employees have months of work.");
        }
        return monthsOfWork;
    }

    @Override
    public int getNumberOfGarages() {
        if (!roles.contains(OWNER)) {
            throw new UnsupportedOperationException("Only owners have garages.");
        }
        return numberOfGarages;
    }
}
