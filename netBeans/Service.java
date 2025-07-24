package netBeans;
import java.io.*;
import java.util.*;

public class Service {
    private int service_id;
    private String service_name;
    private String description;
    private float price;
    private static final String FILE_NAME = "services.txt";

    public void setService(int service_id, String service_name, String description, float price) {
        this.service_id = service_id;
        this.service_name = service_name;
        this.description = description;
        this.price = price;
    }

    public String toString() {
        return service_id + "," + service_name + "," + description + "," + price;
    }

    public static boolean serviceIdExists(int service_id) {
        List<String> services = readServices();
        for (String service : services) {
            String[] parts = service.split(",");
            if (parts.length > 0 && Integer.parseInt(parts[0]) == service_id) {
                return true;
            }
        }
        return false;
    }

    public static boolean addService(Service service) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("netBeans/services.txt", true))) {
            writer.println(service);
            System.out.println("Service saved successfully.");
            return true;

        } catch (IOException e) {
            System.out.println("Error saving service: " + e.getMessage());
            return false;
        }
    }

    public static List<String> readServices() {
        List<String> services = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("D:\\designs\\testPlProject3\\n" + //
                        "etBeans\\services.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                services.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return services;
    }

    public static List<String> readRooms() {
        List<String> services = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("D:\\designs\\testPlProject3\\rooms.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                services.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return services;
    }

    public static boolean updateOrDeleteService(int service_id, String service_name, String description, float price, boolean isUpdate) {
    List<String> services = readServices();
    List<String> updatedList = new ArrayList<>();
    boolean found = false;

    for (String service : services) {
        String[] parts = service.split(",");
        if (Integer.parseInt(parts[0]) == service_id) {
            found = true;
            if (isUpdate) {
                // Add the updated service information
                updatedList.add(service_id + "," + service_name + "," + description + "," + price);
            }
            // If isUpdate is false (delete operation), we skip adding it back (effectively deleting it)
        } else {
            // Keep all other services unchanged
            updatedList.add(service);
        }
    }

    if (!found) {
        return false;
    }

    try (PrintWriter writer = new PrintWriter(new FileWriter("D:\\designs\\testPlProject3\\netBeans\\services.txt"))) {
        for (String service : updatedList) {
            writer.println(service);
        }
        return true;
    } catch (IOException e) {
        System.err.println("Error updating file: " + e.getMessage());
        return false;
    }
}

    public static void generateReport() {
        List<String> services = readServices();
        if (services.isEmpty()) {
            System.out.println("No services recorded.");
            return;
        }

        float totalPrice = 0;
        int serviceCount = 0;
        Map<Integer, String> serviceNames = new HashMap<>();

        for (String service : services) {
            String[] parts = service.split(",");
            int service_id = Integer.parseInt(parts[0]);
            String service_name = parts[1];
            float price = Float.parseFloat(parts[3]);

            serviceNames.put(service_id, service_name);
            totalPrice += price;
            serviceCount++;
        }

        float averagePrice = (serviceCount > 0) ? totalPrice / serviceCount : 0;

        Map<Integer, Integer> serviceRequestCount = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("D:\\designs\\testPlProject3\\rooms.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 1) {
                    int service_id = Integer.parseInt(parts[1]);
                    serviceRequestCount.put(service_id, serviceRequestCount.getOrDefault(service_id, 0) + 1);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading room.txt: " + e.getMessage());
            return;
        }

        int mostRequestedServiceId = -1;
        int maxRequests = 0;

        for (Map.Entry<Integer, Integer> entry : serviceRequestCount.entrySet()) {
            if (entry.getValue() > maxRequests) {
                mostRequestedServiceId = entry.getKey();
                maxRequests = entry.getValue();
            }
        }

        System.out.println("\n    Service Usage Report    ");
        System.out.println("Total services recorded: " + serviceCount);
        System.out.println("Average service price: " + averagePrice);

        if (mostRequestedServiceId == -1) {
            System.out.println("\nNo service requests found.");
        } else {
            System.out.println("\nMost Requested Service:");
            System.out.println("Service ID: " + mostRequestedServiceId);
            System.out.println("Service Name: " + serviceNames.get(mostRequestedServiceId));
            System.out.println("Total Requests: " + maxRequests);
        }
    }

    public static void showServices() {
        List<String> services = Service.readServices();
        if (services.isEmpty()) {
            System.out.println("No services recorded.");
        } else {
            System.out.println("\n      Service Report      ");
            for (String service : services) {
                System.out.println(service);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Service");
            System.out.println("2. Update Service");
            System.out.println("3. Delete Service");
            System.out.println("4. Generate Statistical Report");
            System.out.println("5. Show Services");
            System.out.println("6. Exit");

            int choice = -1;
            while (true) {
                System.out.print("Choose an option: ");
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }

            if (choice == 1 || choice == 2 || choice == 3) {
                int service_id = -1;
                while (true) {
                    System.out.print("Enter Service ID: ");
                    try {
                        service_id = Integer.parseInt(scanner.nextLine());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid numeric Service ID.");
                    }
                }

                if (choice == 1) {
                    if (serviceIdExists(service_id)) {
                        System.out.println("Service ID already exists.");
                        continue;
                    }

                    System.out.print("Enter service name: ");
                    String service_name = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();

                    float price = -1;
                    while (true) {
                        System.out.print("Enter price: ");
                        try {
                            price = Float.parseFloat(scanner.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid numeric price.");
                        }
                    }

                    Service service = new Service();
                    service.setService(service_id, service_name, description, price);
                    addService(service);

                // } else if (choice == 2) {
                //     updateOrDeleteService(service_id, true);
                // } else {
                //     updateOrDeleteService(service_id, false);
                }
            } else if (choice == 4) {
                generateReport();
            } else if (choice == 5) {
                showServices();
            } else if (choice == 6) {
                System.out.println("Exiting program...");
                break;
            } else {
                System.out.println("Invalid choice, please try again.");
            }
        }
        scanner.close();
    }
}