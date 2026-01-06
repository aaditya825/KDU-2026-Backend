package Corporate_Data_Audit;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EmployeeAuditService {

    public static List<Employee> filterEmployees( List<Employee> employees, Predicate<Employee> condition) {
        return employees.stream()
                .filter(condition)
                .collect(Collectors.toList());
    }

    public static List<String> getUppercaseNames(List<Employee> employees) {
        return employees.stream()
                .map(e -> e.getName().toUpperCase())
                .collect(Collectors.toList());
    }

    public static double calculateTotalSalary(List<Employee> employees) {
        return employees.stream()
                .mapToDouble(Employee::getSalary)
                .sum();
    }
}

