package netBeans;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

class Customer {
    private int id;
    private String name;
    private String checkIn;
    private String checkOut;
    private String email;
    private String phone;

    public Customer(int id, String name, String checkIn, String checkOut, String email, String phone) {
        this.id = id;
        this.name = name;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.email = email;
        this.phone = phone;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCheckIn() { return checkIn; }
    public String getCheckOut() { return checkOut; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }

    public void setName(String name) { this.name = name; }
    public void setCheckIn(String checkIn) { this.checkIn = checkIn; }
    public void setCheckOut(String checkOut) { this.checkOut = checkOut; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }


    public String toString() {
        return id + "," + name + "," + checkIn + "," + checkOut + "," + email + "," + phone;
    }

    public static Customer fromString(String data) {
        String[] parts = data.split(",");
        if (parts.length < 6) {
            throw new IllegalArgumentException("Invalid customer data: " + data);
        }
        return new Customer(
            Integer.parseInt(parts[0]),
            parts[1],
            parts[2],
            parts[3],
            parts[4],
            parts[5]
        );
    }
}

public class CustomerManager {
    private static final String FILE_NAME = "customers.txt";

    public static boolean isPhoneExists(String phone) {
        for (Customer c : getAllCustomers()) {
            if (c.getPhone().equals(phone))
                return true;
        }
        return false;
    }

    // تحقق من صحة رقم الهاتف (يبدأ بـ01 وطوله 11 رقم)
    public static boolean isValidPhone(String phone) {
        return phone.startsWith("01") && phone.length() == 11 && phone.matches("\\d+");
    }

    // تحقق من صحة البريد الإلكتروني يحتوي على @gmail.com (حساسية حالة الأحرف غير مهمة)
    public static boolean isValidEmail(String email) {
        return email.toLowerCase().endsWith("@gmail.com");
    }

    // تحقق من صحة التاريخ بصيغة yyyy-MM-dd
    public static boolean isValidDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    // تحقق أن ال ID رقم فقط وغير موجود مسبقاً
    public static boolean isIdExists(int id) {
        for (Customer c : getAllCustomers()) {
            if (c.getId() == id)
                return true;
        }
        return false;
    }

    // تحقق أن الاسم غير مكرر (غير حساس لحالة الأحرف)
    public static boolean isNameExists(String name) {
        for (Customer c : getAllCustomers()) {
            if (c.getName().equalsIgnoreCase(name))
                return true;
        }
        return false;
    }

    // تحقق أن البريد غير مكرر (غير حساس لحالة الأحرف)
    public static boolean isEmailExists(String email) {
        for (Customer c : getAllCustomers()) {
            if (c.getEmail().equalsIgnoreCase(email))
                return true;
        }
        return false;
    }

    public static void addCustomer(Customer customer) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(customer.toString());
            writer.newLine();
            System.out.println(" Customer added successfully.");
        } catch (IOException e) {
            System.out.println(" Error while adding customer.");
        }
    }

        // دي دالة بتجيب كل العملاء من الفايل وتحوّلهم لقائمة
    public static List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("D:\\designs\\testPlProject3\\customers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    list.add(Customer.fromString(line));
                } catch (Exception e) {
                    System.out.println(" Skipping invalid customer data: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println(" Error reading customer data.");
        }
        return list;
    }
    public String update_customer(int id, String name, LocalDate checkin, LocalDate checkout, String phone) {
    File originalFile = new File("customers.txt");
    File tempFile = new File("customers_temp.txt");
    boolean found = false;


    if (!phone.matches("01\\d{9}")) {
        return "Invalid phone number. It must be 11 digits and start with 01.";
    }


    // if (!checkout.isAfter(checkin)) {
    //     return "Check-out date must be after check-in date.";
    // }

    try (
        Scanner scan = new Scanner(originalFile);
        FileWriter writer = new FileWriter(tempFile)
    ) {
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] data = line.split(",");

            int currentId = Integer.parseInt(data[0].trim());
            if (currentId == id) {

                found = true;
                String newLine = id + "," + name.trim() + "," + checkin + "," + checkout + "," + phone.trim();
                writer.write(newLine + System.lineSeparator());
            } else {
                writer.write(line + System.lineSeparator());
            }
        }
    } catch (IOException e) {
        return "Error processing file: " + e.getMessage();
    }

    if (!found) {
        tempFile.delete(); 
        return "Customer with ID " + id + " not found.";
    }


    if (!originalFile.delete()) {
        return "Failed to delete original file.";
    }

    if (!tempFile.renameTo(originalFile)) {
        return "Failed to rename temp file.";
    }

    return "Customer updated successfully.";
}
// Helper methods
    public String deleteCustomer(int customer_id) {
    File originalFile = new File("customers.txt");
    File tempFile = new File("customers_temp.txt");
    boolean found = false;

    try (
        Scanner scan = new Scanner(originalFile);
        FileWriter writer = new FileWriter(tempFile)
    ) {
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] data = line.split(",");

            int currentId = Integer.parseInt(data[0].trim());
            if (currentId == customer_id) {
                found = true; 
                continue;
            }
            writer.write(line + System.lineSeparator()); 
        }
    } catch (IOException e) {
        return "Error processing file: " + e.getMessage();
    }

    if (!found) {
        tempFile.delete(); 
        return "Customer with ID " + customer_id + " not found.";
    }

    if (!originalFile.delete()) {
        return "Failed to delete original file.";
    }

    if (!tempFile.renameTo(originalFile)) {
        return "Failed to rename temp file.";
    }

    return "Customer deleted successfully.";
}
}
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         while (true) {
//             System.out.println("\n===== Customer Management Menu =====");
//             System.out.println("1. Add Customer");
//             System.out.println("2. Update Customer");
//             System.out.println("3. Delete Customer");
//             System.out.println("4. View All Customers");
//             System.out.println("0. Exit");
//             System.out.print("Choose option: ");
//             int choice = sc.nextInt();

//             switch (choice) {
//                 case 1:
//                     System.out.print("Enter ID: ");
//                     int id = sc.nextInt();
//                     sc.nextLine();  
//                     System.out.print("Enter name: ");
//                     String name = sc.nextLine();
//                     System.out.print("Enter check-in time: ");
//                     String checkIn = sc.nextLine();
//                     System.out.print("Enter check-out time: ");
//                     String checkOut = sc.nextLine();
//                     System.out.print("Enter email: ");
//                     String email = sc.nextLine();
//                     System.out.print("Enter phone: ");
//                     String phone = sc.nextLine();
//                     addCustomer(new Customer(id, name, checkIn, checkOut, email, phone));
//                     break;
//                 case 2:
//                     System.out.print("Enter ID to update: ");
//                     int uid = sc.nextInt();
//                     sc.nextLine();
//                     // Customer toUpdate = getCustomerById(uid);
//                     if (toUpdate != null) {
//                         System.out.print("Enter new name: ");
//                         String newName = sc.nextLine();
//                         System.out.print("Enter new check-in time: ");
//                         String newCheckIn = sc.nextLine();
//                         System.out.print("Enter new check-out time: ");
//                         String newCheckOut = sc.nextLine();
//                         System.out.print("Enter new email: ");
//                         String newEmail = sc.nextLine();
//                         System.out.print("Enter new phone: ");
//                         String newPhone = sc.nextLine();
//                         toUpdate.setName(newName);
//                         toUpdate.setCheckIn(newCheckIn);
//                         toUpdate.setCheckOut(newCheckOut);
//                         toUpdate.setEmail(newEmail);
//                         toUpdate.setPhone(newPhone);
//                         // updateCustomer();
//                     } else {
//                         System.out.println(" Customer not found.");
//                     }
//                     break;
//                 case 3:
//                     System.out.print("Enter ID to delete: ");
//                     int did = sc.nextInt();
//                     // deleteCustomer(did);
//                     break;
//                 case 4:
//                     System.out.println("\n List of Customers:");
//                     for (Customer c : getAllCustomers()) {
//                         System.out.println("-----------------------------");
//                         System.out.println("ID: " + c.getId());
//                         System.out.println("Name: " + c.getName());
//                         System.out.println("Check-in: " + c.getCheckIn());
//                         System.out.println("Check-out: " + c.getCheckOut());
//                         System.out.println("Email: " + c.getEmail());
//                         System.out.println("Phone: " + c.getPhone());
//                     }
//                     break;
//                 case 0:
//                     System.out.println(" Exiting...");
//                     return;
//                 default:
//                     System.out.println(" Invalid option.");
//             }
//         }
//     }
// }



