package netBeans;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class RoomManager {

    public boolean validatePhoneNumber(String phoneNumber)
    {
        if (phoneNumber.startsWith("01") && phoneNumber.length() == 11 && phoneNumber.chars().allMatch(Character::isDigit))
        {
            return true;
        }

        return false;
    }

    public boolean registerCustomer(String name, int id, LocalDate checkin, LocalDate checkout, String phoneNumber) {
    boolean exists = false;
    String cleanedInput = name.replaceAll("\\s+", "").toLowerCase();

    try (Scanner scanner = new Scanner(new File("customers.txt"))) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            if (parts.length < 5) {
                System.out.println("Malformed line in customers.txt: " + line);
                continue;
            }

            String cleanedName = parts[1].replaceAll("\\s+", "").toLowerCase();
            int customer_id = Integer.parseInt(parts[0].trim());
            String phone_number = parts[4].trim();

            if (cleanedName.equals(cleanedInput) || customer_id == id || phone_number.equals(phoneNumber)) {
                exists = true;
                break;
            }
        }
    } catch (IOException e) {
        System.out.println("File read error: " + e.getMessage());
    }

    if (!exists) {
        try (FileWriter writer = new FileWriter("customers.txt", true)) {
            writer.write(id + "," + name + "," + checkin + "," + checkout + "," + phoneNumber + "\n");
            System.out.println("Customer added.");
            return true;
        } catch (IOException e) {
            System.out.println("Write error: " + e.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(null, "Customer already exists.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    return false;
}

    public String[] availableRooms() {
        ArrayList<String> listRooms = new ArrayList<>();

        // System.out.println("Available Rooms:");
        try (Scanner reader = new Scanner(new File("rooms.txt"))) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine().trim();
                String[] parts = line.split(",");

                if (parts.length < 6) {
                    System.out.println("Malformed line: " + line);
                    continue;
                }

                String roomNumber = parts[0].trim();
                String isReserved = parts[1].trim();
                String type = parts[2].trim();

                if (isReserved.equalsIgnoreCase("false")) {
                    String roomInfo = "Room " + roomNumber + " (" + type + ") - Available";
                    listRooms.add(roomInfo);
                    // System.out.println(roomInfo);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return listRooms.toArray(new String[0]);
    }

    public String[] reservedRooms() {
        ArrayList<String> listRooms = new ArrayList<>();

        // System.out.println("Reserved Rooms:");
        try (Scanner reader = new Scanner(new File("rooms.txt"))) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine().trim();
                String[] parts = line.split(",");

                if (parts.length < 6) {
                    System.out.println("Malformed line: " + line);
                    continue;
                }

                String roomNumber = parts[0].trim();
                String isReservied = parts[1].trim();
                String type = parts[2].trim();

                if (isReservied.equalsIgnoreCase("true")) {
                    String roomInfo = "Room " + roomNumber + " (" + type + ") - Reserved";
                    listRooms.add(roomInfo);
                    // System.out.println(roomInfo);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return listRooms.toArray(new String[0]);
    }

    public String[] FilterByType(String type) {
        ArrayList<String> listRooms = new ArrayList<>();

        // System.out.println("Reserved Rooms:");
        try (Scanner reader = new Scanner(new File("rooms.txt"))) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine().trim();
                String[] parts = line.split(",");

                if (parts.length < 3) {
                    System.out.println("Malformed line: " + line);
                    continue;
                }

                String roomNumber = parts[0].trim();
                String status = parts[1].trim();
                if (status.equals("true")){status = "Available";}
                else if (status.equals("false")){status = "Reserved";}
                if (parts[2].equalsIgnoreCase(type)) {
                    String roomInfo = "Room " + roomNumber + " (" + type + ") - " + status;
                    listRooms.add(roomInfo);
                    // System.out.println(roomInfo);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return listRooms.toArray(new String[0]);
    }

    public ArrayList<String> FilterByServices(String serviceName) {
    ArrayList<String> matchedRooms = new ArrayList<>();

    try (Scanner reader = new Scanner(new File("rooms.txt"))) {
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] parts = line.split(",");
            if (parts.length < 6) {
                continue; // skip malformed lines
            }

            String[] services = parts[5].split(";");
            for (String service : services) {
                if (service.trim().equalsIgnoreCase(serviceName.trim())) {
                    matchedRooms.add(line);
                    break; // no need to check other services for this room
                }
            }
        }
    } catch (IOException e) {
        matchedRooms.add("Error reading rooms.txt: " + e.getMessage());
    }

    return matchedRooms;
}

    public String assign_room(int roomId, int customerId) {
        File customerFile = new File("customers.txt");
        File originalRoomFile = new File("rooms.txt");
        File tempRoomFile = new File("rooms_temp.txt");

        // 1. Check if the customer exists
        boolean customerExists = false;

        if (!customerFile.exists()) {
            return "customers.txt file not found.";
        }

        try (Scanner customerReader = new Scanner(customerFile)) {
            while (customerReader.hasNextLine()) {
                String line = customerReader.nextLine();
                String[] parts = line.split(",", -1);

                if (parts.length > 0) {
                    try {
                        int currentCustomerId = Integer.parseInt(parts[0]);
                        if (currentCustomerId == customerId) {
                            customerExists = true;
                            break;
                        }
                    } catch (NumberFormatException e) {
                        // Skip invalid customer lines
                    }
                }
            }
        } catch (IOException e) {
            return "Error reading customers file: " + e.getMessage();
        }

        if (!customerExists) {
            return "Customer ID not found.";
        }

        // 2. Assign room if customer exists
        if (!originalRoomFile.exists()) {
            return "rooms.txt file not found.";
        }

        boolean roomFound = false;
        boolean roomAssigned = false;

        try (
            Scanner roomReader = new Scanner(originalRoomFile);
            FileWriter writer = new FileWriter(tempRoomFile)
        ) {
            while (roomReader.hasNextLine()) {
                String line = roomReader.nextLine();
                String[] parts = line.split(",", -1);

                if (parts.length < 6) {
                    writer.write(line + "\n");
                    continue;
                }

                try {
                    int currentRoomId = Integer.parseInt(parts[0]);
                    boolean isReserved = Boolean.parseBoolean(parts[1]);

                    if (currentRoomId == roomId) {
                        roomFound = true;

                        if (isReserved) {
                            writer.write(line + "\n");
                            return "Room is already reserved!";
                        } else {
                            parts[1] = "true"; // Mark as reserved
                            parts[4] = String.valueOf(customerId); // Assign customer ID
                            writer.write(String.join(",", parts) + "\n");
                            roomAssigned = true;
                        }
                    } else {
                        writer.write(line + "\n");
                    }

                } catch (NumberFormatException e) {
                    writer.write(line + "\n");
                }
            }

            roomReader.close();
            writer.close();

            if (roomFound && roomAssigned) {
                originalRoomFile.delete();
                tempRoomFile.renameTo(originalRoomFile);
                return "success";
            }

            if (!roomFound) {
                return "Room ID not found.";
            }

        } catch (IOException e) {
            return "Error updating room file: " + e.getMessage();
        }

        return "Unknown error.";
    }

    public List<String> nearCheckoutWithinTwoDays() {
    List<String> clients = new ArrayList<>();

    try (Scanner reader = new Scanner(new File("customers.txt"))) {
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] parts = line.split(",");

            if (parts.length < 4) {
                System.out.println("Malformed line in customers.txt: " + line);
                continue;
            }

            try {
                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                LocalDate today = LocalDate.now();
                LocalDate checkout = LocalDate.parse(parts[3].trim());

                long daysBetween = ChronoUnit.DAYS.between(today, checkout);

                if (daysBetween >= 0 && daysBetween <= 2) {
                    if (daysBetween == 0) {
                        clients.add("Customer " + name + " (ID: " + id + ") is leaving today.");
                    } else {
                        clients.add("Customer " + name + " (ID: " + id + ") will leave in " + daysBetween + " day(s).");
                    }
                }

            } catch (NumberFormatException | DateTimeParseException e) {
                System.out.println("Error parsing line: " + line);
            }
        }
    } catch (IOException e) {
        System.out.println("Error reading customers.txt: " + e.getMessage());
    }

    return clients;
}

    public String [] Show_Services() {
        ArrayList<String> listServices = new ArrayList<>();
        
        try (Scanner reader = new Scanner(new File("services.txt"))) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                listServices.add(line);
                // System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return listServices.toArray(new String[0]);
    }

    public boolean isNumeric(String str) {
    try {
        Integer.parseInt(str.trim());
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}
//@
    public String getNameOfService(int service_id) {
    try (Scanner reader = new Scanner(new File("services.txt"))) {
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] parts = line.split(",");
            
            if (parts.length >= 2) {
                try {
                    int id = Integer.parseInt(parts[0].trim());
                    if (id == service_id) {
                        return parts[1].trim(); 
                    }
                } 
                catch (NumberFormatException e)//هنا ال exception دا بيخليك تهندل الحته  بتاعت انك تحاول تحول string مينفعش يتحول ل int
                {
                    System.out.println("Invalid ID format in services.txt: " + parts[0]);
                }
            }  
            else 
            {
                System.out.println("Malformed line in services.txt: " + line);
            }
        }
    }
    catch (FileNotFoundException e)
    {
        System.out.println("Error: services.txt file not found.");
    }
    return null; 
}
//@
    public void add_services(int customer_id) {
    Show_Services();

    try (Scanner input = new Scanner(System.in)) {
        System.out.println("Please choose service IDs (separated by comma): ");
        String chosen = input.nextLine();

        if (chosen.isEmpty()) {
            System.out.println("No services selected. Operation cancelled.");
            return;
        }

        String[] selectedServices = chosen.split(",");
        String serviceNames = "";

        for (String serviceIdStr : selectedServices) {
            serviceIdStr = serviceIdStr.trim();
            if (!isNumeric(serviceIdStr)) {
                System.out.println("Invalid service ID: " + serviceIdStr);
                return;
            }

            int serviceId = Integer.parseInt(serviceIdStr);
            String serviceName = getNameOfService(serviceId);

            if (serviceName == null) {
                System.out.println("Service ID not found: " + serviceId);
                return;
            }

            if (!serviceNames.contains(serviceName)) {
                if (!serviceNames.isEmpty()) {
                    serviceNames += ";";
                }
                serviceNames += serviceName;
            }
        }

        try (FileWriter writer = new FileWriter("customer_services.txt", true)) {
            writer.write(customer_id + "," + serviceNames + "\n");
            System.out.println("Services assigned successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to customer_services.txt: " + e.getMessage());
        }

    } catch (Exception e) {
        System.out.println("Error reading input: " + e.getMessage());
    }
}

    public List<String> generate_Invoice(int customer_id) {
    String customerName = "";
    String checkin = "", checkout = "";
    int roomNumber = -1;
    double roomPricePerNight = 0;
    long stayDays = 0;
    String[] customerServices = new String[0];
    double servicesTotal = 0;

    // 1. Read customer data
    try (Scanner reader = new Scanner(new File("customers.txt"))) {
        boolean found = false;
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] parts = line.split(",");

            if (parts.length >= 4) {
                int id = Integer.parseInt(parts[0].trim());
                if (id == customer_id) {
                    customerName = parts[1].trim();
                    checkin = parts[2].trim();
                    checkout = parts[3].trim();

                    if (checkin.isEmpty() || checkout.isEmpty()) return null;

                    LocalDate inDate = LocalDate.parse(checkin);
                    LocalDate outDate = LocalDate.parse(checkout);
                    stayDays = ChronoUnit.DAYS.between(inDate, outDate);
                    if (stayDays <= 0) return null;

                    found = true;
                    break;
                }
            }
        }

        if (!found) return null;

    } catch (IOException e) {
        return null;
    }

    // 2. Get room info
    try (Scanner reader = new Scanner(new File("rooms.txt"))) {
        while (reader.hasNextLine()) {
            String[] parts = reader.nextLine().split(",", -1);
            if (parts.length >= 5 && parts[4].equals(String.valueOf(customer_id))) {
                roomNumber = Integer.parseInt(parts[0].trim());
                roomPricePerNight = Double.parseDouble(parts[3].trim());
                break;
            }
        }
    } catch (IOException e) {
        return null;
    }

    // 3. Get services
    try (Scanner reader = new Scanner(new File("customer_services.txt"))) {
        while (reader.hasNextLine()) {
            String[] parts = reader.nextLine().split(",", 2);
            if (parts.length == 2 && parts[0].trim().equals(String.valueOf(customer_id))) {
                customerServices = parts[1].split(";");
                break;
            }
        }
    } catch (IOException e) {
        return null;
    }

    // 4. Calculate total service cost
    for (String service : customerServices) {
        try (Scanner serviceReader = new Scanner(new File("services.txt"))) {
            while (serviceReader.hasNextLine()) {
                String line = serviceReader.nextLine();
                String[] parts = line.split(",");

                if (parts.length >= 3 && parts[1].trim().equalsIgnoreCase(service.trim())) {
                    servicesTotal += Double.parseDouble(parts[3].trim());
                    break;
                }
            }
        } catch (IOException e) {
            return null;
        }
    }

    double roomTotal = roomPricePerNight * stayDays;
    double finalTotal = roomTotal + servicesTotal;

    // 5. Build return list
    List<String> invoiceList = new ArrayList<>();
    invoiceList.add(customerName);
    invoiceList.add(String.valueOf(roomNumber));
    invoiceList.add(String.valueOf(stayDays));
    invoiceList.add(String.valueOf(roomPricePerNight));
    invoiceList.add(String.valueOf(roomTotal));
    invoiceList.add(customerServices.length > 0 ? String.join(", ", customerServices) : "None");
    invoiceList.add(String.valueOf(servicesTotal));
    invoiceList.add(String.valueOf(finalTotal));

    return invoiceList;
}



}