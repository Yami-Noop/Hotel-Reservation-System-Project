package netBeans.empsDealing;
import java.io.*;
import java.util.*;

class Employee {
    private int id;
    private String name;
    private String phone;
    private String email;

    public Employee(int id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }

    public void setName(String name) { this.name = name; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }

    public String toString() {
        return id + "," + name + "," + phone + "," + email;
    }

    public static Employee fromString(String data) {
        String[] parts = data.split(",");
        return new Employee(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3]);
    }
}

public class EmployeeManager {
    private static final String FILE_NAME = "employees.txt";
    private static final String USER_FILE = "users.txt";

public static boolean loginAsManager(String user, String pass) {
    return user.equals("admin") && pass.equals("admin");
}

public static boolean loginAsEmployee(String user, String pass) {
    try (BufferedReader reader = new BufferedReader(new FileReader("D:\\designs\\testPlProject3\\Zaky\\Employeee\\users.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 2 && parts[0].equals(user) && parts[1].equals(pass)) {
                return true;
            }
        }
    } catch (IOException e) {
        System.out.println("Error reading user file.");
    }
    return false;
}

    public static boolean addEmployee(Employee employee) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\designs\\testPlProject3\\Zaky\\Employeee\\employees.txt", true))) {
            writer.write(employee.toString());
            writer.newLine();
            // System.out.println("Employee added successfully.");
            return true;
        } catch (IOException e) {
            // System.out.println("Error while adding employee.");
            return false;
        }
    }

    public static List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(Employee.fromString(line));
            }
        } catch (IOException e) {
            // ممكن نطبع رسالة هنا لو عايز
        }
        return list;
    }

    public static void update_Employee(Employee updatedEmployee) {
        List<Employee> employees = getAllEmployees();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Employee employee : employees) {
                if (employee.getId() == updatedEmployee.getId()) {
                    writer.write(updatedEmployee.toString());
                } else {
                    writer.write(employee.toString());
                }
                writer.newLine();
            }
            System.out.println("Employee updated successfully.");
        } catch (IOException e) {
            System.out.println("Error while updating employee.");
        }
    }


    public static void updateEmployee(int employee_id) {
        List<String> employees = readEmployees();
        List<String> updatedList = new ArrayList<>();
        boolean found = false;
        Scanner scanner = new Scanner(System.in);

        for (String employee : employees) {
            String[] parts = employee.split(",");
            if (Integer.parseInt(parts[0]) == employee_id) {
                found = true;

                System.out.print("Enter new employee name: ");
                String employee_name = scanner.nextLine();
                System.out.print("Enter new employee phone: ");
                String employee_phone = scanner.nextLine();
                System.out.print("Enter new employee email: ");
                String employee_email = scanner.nextLine();

                updatedList.add(employee_id + "," + employee_name + "," + employee_phone+ "," + employee_email);
                System.out.println("Employee updated successfully.");
            } else {
                updatedList.add(employee);
            }
        }

        if (!found) {
            System.out.println("Employee ID not found.");
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (String employee : updatedList) {
                writer.println(employee);
            }
        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
        }
    }

    public static List<String> readEmployees() {
        List<String> employees = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("D:\\designs\\testPlProject3\\Zaky\\Employeee\\employees.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                employees.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading employees file: " + e.getMessage());
        }
        return employees;
    }


    public static void deleteEmployee(int employee_id) {
        List<String> employees = readEmployees();
        List<String> updatedList = new ArrayList<>();
        boolean found = false;

        for (String employee : employees) {
            String[] parts = employee.split(",");
            if (Integer.parseInt(parts[0]) == employee_id) {
                found = true;
                System.out.println("Employee deleted successfully.");
            } else {
                updatedList.add(employee);
            }
        }

        if (!found) {
            System.out.println("Employee ID not found.");
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("D:\\designs\\testPlProject3\\Zaky\\Employeee\\employees.txt"))) {
            for (String employee : updatedList) {
                writer.println(employee);
            }
        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
        }
    }



    public static void delete_Employee(int id) {
        List<Employee> employees = getAllEmployees();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Employee employee : employees) {
                if (employee.getId() != id) {
                    writer.write(employee.toString());
                    writer.newLine();
                }
            }
            System.out.println("Employee deleted successfully.");
        } catch (IOException e) {
            System.out.println("Error while deleting employee.");
        }
    }

    public static Employee getEmployeeById(int id) {
        for (Employee e : getAllEmployees()) {
            if (e.getId() == id)
                return e;
        }
        return null;
    }

    public static void managerMenu(Scanner sc) {
        while (true) {
            System.out.println("\n********* Manager Menu *********");
            System.out.println("1. Add Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. Delete Employee");
            System.out.println("4. View All Employees");
            System.out.println("0. Logout");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter phone: ");
                    String phone = sc.nextLine();
                    System.out.print("Enter email: ");
                    String email = sc.nextLine();
                    addEmployee(new Employee(id, name, phone, email));
                    break;
                case 2:
                    System.out.print("Enter ID to update: ");
                    int uid = sc.nextInt();
                    sc.nextLine();
                    Employee toUpdate = getEmployeeById(uid);
                    if (toUpdate != null) {
                        System.out.print("Enter new name: ");
                        String newName = sc.nextLine();
                        System.out.print("Enter new phone: ");
                        String newPhone = sc.nextLine();
                        System.out.print("Enter new email: ");
                        String newEmail = sc.nextLine();
                        toUpdate.setName(newName);
                        toUpdate.setPhone(newPhone);
                        toUpdate.setEmail(newEmail);
                        // updateEmployee(toUpdate);
                    } else {
                        System.out.println("Employee not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter ID to delete: ");
                    int did = sc.nextInt();
                    deleteEmployee(did);
                    break;
                case 4:
                    System.out.println("\nList of Employees:");
                    for (Employee e : getAllEmployees()) {
                        System.out.println("ID: " + e.getId() + ", Name: " + e.getName() +
                                ", Phone: " + e.getPhone() + ", Email: " + e.getEmail());
                    }
                    break;
                case 0:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public static void employeeMenu() {
        System.out.println("\n********* Employee View *********");
        System.out.println("List of Employees:");
        for (Employee e : getAllEmployees()) {
            System.out.println("ID: " + e.getId() + ", Name: " + e.getName() +
                    ", Phone: " + e.getPhone() + ", Email: " + e.getEmail());
        }
    }

    // Duplicate main method removed to resolve compilation error.
}
