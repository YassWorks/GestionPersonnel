package gl2.example.Personnel.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gl2.example.Personnel.model.Employee;
import gl2.example.Personnel.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> searchEmployeesByName(String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }

    public String calculateEmploymentDuration(Date hireDate) {
        if (hireDate == null) {
            return "Hire date not available";
        }
        LocalDate hireLocalDate = hireDate.toInstant()
                                          .atZone(ZoneId.systemDefault())
                                          .toLocalDate();
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(hireLocalDate, currentDate);

        return String.format("%d years, %d months, %d days",
                             period.getYears(),
                             period.getMonths(),
                             period.getDays());
    }
}
