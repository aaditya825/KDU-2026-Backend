package Corporate_Data_Audit;

import java.util.List;

public class AuditRunner {

    public static void main(String[] args) {

        List<Employee> employees = Employee.getSampleData();

        // 1. High-Earning Engineers (> $70,000 AND ENGINEERING)
        List<Employee> highEarningEngineers =
                EmployeeAuditService.filterEmployees(
                        employees,
                        e -> e.getSalary() > 70_000 &&
                                "ENGINEERING".equals(e.getDepartment())
                );

        System.out.println("High-Earning Engineers:");
        highEarningEngineers.forEach(System.out::println);

        List<String> standardizedNames =
                EmployeeAuditService.getUppercaseNames(employees);

        System.out.println("\nStandardized Employee Names:");
        standardizedNames.forEach(System.out::println);

        double totalSalary =
                EmployeeAuditService.calculateTotalSalary(employees);

        System.out.println("\nTotal Annual Salary Budget: $" + totalSalary);
    }
}
