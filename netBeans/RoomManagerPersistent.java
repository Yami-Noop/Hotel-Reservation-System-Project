package netBeans;

import java.io.*;
import java.util.*;

import netBeans.Service;

// class Service {
//     int id;
//     String name;
//     int price;

//     public Service(int id, String name, int price) {
//         this.id = id;
//         this.name = name;
//         this.price = price;
//     }

//     @Override
//     public String toString() {
//         return id + "," + name + "," + price;
//     }

//     public static Service fromString(String line) {
//         String[] parts = line.split(",");
//         return new Service(Integer.parseInt(parts[0]), parts[1], Integer.parseInt(parts[2]));
//     }
// }

class Room {
    int roomId;
    String type;
    boolean reserved;
    int price;
    int customerId;
    List<Integer> serviceIds;

    public Room(int roomId, String type, boolean reserved, int price) {
        this.roomId = roomId;
        this.type = type;
        this.reserved = reserved;
        this.price = price;
        this.customerId = -1; // مفيش عميل حاجز في الأول
        this.serviceIds = new ArrayList<>();
    }

    public String toFileString() {
        return roomId + "," + reserved + "," + type + "," + price + "," + customerId + "," + serviceIds.toString().replace("[", "").replace("]", "");
    }

    public static Room fromFileString(String line) {
        String[] parts = line.split(",", 6);
        Room room = new Room(
            Integer.parseInt(parts[0]),
            parts[2],
            Boolean.parseBoolean(parts[1]),
            Integer.parseInt(parts[3])
        );

        room.customerId = (parts.length >= 5 && !parts[4].trim().isEmpty())
            ? Integer.parseInt(parts[4].trim())
            : -1;

        if (parts.length == 6 && !parts[5].trim().isEmpty()) {
            String[] ids = parts[5].split(",");
            for (String id : ids) {
                id = id.trim();
                if (!id.isEmpty()) {
                    try {
                        room.serviceIds.add(Integer.parseInt(id));
                    } catch (NumberFormatException e) {
                        System.out.println(" Ignored invalid service ID: " + id);
                    }
                }
            }
        }

        return room;
    }
}

public class RoomManagerPersistent {
    static final String ROOM_FILE = "D:\\designs\\testPlProject3\\rooms.txt";
    static final String SERVICE_FILE = "services.txt";
    static List<Room> rooms = new ArrayList<>();
    static List<Service> services = new ArrayList<>();

    // public static void loadFiles() {
    //     try {
    //         File sf = new File(SERVICE_FILE);
    //         if (!sf.exists()) initServices(); // لو ملف الخدمات مش موجود نعمله من الأول
    //         else {
    //             BufferedReader br = new BufferedReader(new FileReader(SERVICE_FILE));
    //             String line;
    //             while ((line = br.readLine()) != null) {
    //                 services.add(Service.fromString(line));
    //             }
    //             br.close();
    //         }

    //         File rf = new File(ROOM_FILE);
    //         if (rf.exists()) {
    //             BufferedReader br = new BufferedReader(new FileReader(ROOM_FILE));
    //             String line;
    //             while ((line = br.readLine()) != null) {
    //                 rooms.add(Room.fromFileString(line));
    //             }
    //             br.close();
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }

    public String addRoom(int roomId, String roomType, int price, String customerId) throws IOException {
    File file = new File("rooms.txt");


    if (file.exists()) {
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            String line = scan.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split(",");
            int existingId = Integer.parseInt(parts[0].trim());
            if (existingId == roomId) {
                scan.close();
                return "Room ID " + roomId + " already exists.";
            }
        }
        scan.close();
    }


    boolean isReserved;
    if (customerId.equals("-1")) {
        isReserved = false;
        customerId = "";  
    } else {

        if (!customerId.matches("\\d+")) {
            return "Invalid Customer ID. It must be a number or -1.";
        }
        isReserved = true;
    }


    FileWriter fw = new FileWriter(file, true);
    String newRoom = roomId + "," + isReserved + "," + roomType + "," + price + "," + customerId + "," + "" + "\n";
    fw.write(newRoom);
    fw.close();

    return "Room added successfully.";
}

public static boolean updateOrDeleteRoom(int roomId, String roomType, int price, String customerId, boolean isReserved, boolean isUpdate) {
    List<String> rooms = readRooms(); // You'll need to implement readRooms() similar to readServices()
    List<String> updatedList = new ArrayList<>();
    boolean found = false;

    for (String room : rooms) {
        String[] parts = room.split(",");
        if (parts.length >= 5 && Integer.parseInt(parts[0].trim()) == roomId) {
            found = true;
            if (isUpdate) {
                // Add the updated room information
                String updatedRoom = String.format("%d,%b,%s,%d,%s",
                    roomId,
                    isReserved,
                    roomType,
                    price,
                    customerId);
                updatedList.add(updatedRoom);
            }
            // If isUpdate is false, skip adding (delete operation)
        } else {
            // Keep all other rooms unchanged
            updatedList.add(room);
        }
    }

    if (!found) {
        return false;
    }

    try (PrintWriter writer = new PrintWriter(new FileWriter("rooms.txt"))) {
        for (String room : updatedList) {
            writer.println(room);
        }
        return true;
    } catch (IOException e) {
        System.err.println("Error updating rooms file: " + e.getMessage());
        return false;
    }
}

// Helper method to read rooms (similar to your readServices())
private static List<String> readRooms() {
    List<String> rooms = new ArrayList<>();
    try (Scanner scanner = new Scanner(new File("rooms.txt"))) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                rooms.add(line);
            }
        }
    } catch (FileNotFoundException e) {
        System.err.println("Rooms file not found: " + e.getMessage());
    }
    return rooms;
}

    public String deleteRoom(int roomId) throws IOException {
    File originalFile = new File("rooms.txt");
    File tempFile = new File("rooms_temp.txt");
    boolean found = false;
    String deletedRoomInfo = "";

    // Check if file exists
    if (!originalFile.exists()) {
        return "Rooms file not found. No rooms exist.";
    }

    try (
        Scanner scan = new Scanner(originalFile);
        FileWriter fw = new FileWriter(tempFile)
    ) {
        while (scan.hasNextLine()) {
            String line = scan.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split(",");
            int currentId = Integer.parseInt(parts[0].trim());
            
            if (currentId == roomId) {
                found = true;
                // Save deleted room info for the return message
                deletedRoomInfo = "Room ID: " + parts[0].trim() + 
                                ", Type: " + parts[2].trim() + 
                                ", Price: " + parts[3].trim();
                continue; // Skip writing this line to delete it
            }
            
            // Write all other rooms to temp file
            fw.write(line + "\n");
        }
    }

    if (!found) {
        tempFile.delete(); // Clean up temp file if room not found
        return "Room with ID " + roomId + " not found.";
    }

    // Replace original file with temp file
    if (!originalFile.delete()) {
        return "Failed to delete original file.";
    }

    if (!tempFile.renameTo(originalFile)) {
        return "Failed to rename temp file.";
    }

    return "Room deleted successfully: " + deletedRoomInfo;
}

    // public static void initServices() {
    //     services = Arrays.asList(
    //         new Service(1, "Spa", 300),
    //         new Service(2, "Laundry", 100),
    //         new Service(3, "Breakfast", 50),
    //         new Service(4, "Airport Pickup", 200),
    //         new Service(5, "Gym Access", 0),
    //         new Service(6, "Room Cleaning", 0),
    //         new Service(7, "Swimming Pool", 0),
    //         new Service(8, "Dinner", 120),
    //         new Service(9, "Mini Bar", 180),
    //         new Service(10, "Massage", 250)
    //     );
    //     try (PrintWriter pw = new PrintWriter(new FileWriter(SERVICE_FILE))) {
    //         for (Service s : services) {
    //             pw.println(s);
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }

    // public static void showServices() {
    //     for (Service s : services) {
    //         System.out.println(s.id + ". " + s.name + " ($" + s.price + ")");
    //     }
    // }

    public static Room findRoom(int id) {
        for (Room r : rooms) {
            if (r.roomId == id) return r;
        }
        return null;
    }


}

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         loadFiles(); // نحمل الفايلات أول ما البرنامج يشتغل

//         while (true) {
//             System.out.println("\n1. Add Room\n2. Update Room\n3. Delete Room\n4. Manage Room Services\n5. Show Rooms\n0. Exit");
//             System.out.print("Choose: ");
//             int ch = sc.nextInt();
//             switch (ch) {
//                 case 1:
//                 // roomid, type, price, customerid (if there)
//                     System.out.print("Room ID: ");
//                     int id = sc.nextInt();
//                     sc.nextLine();
//                     System.out.print("Type (Single/Double): ");
//                     String type = sc.nextLine();
//                     System.out.print("Room Price: ");
//                     int price = sc.nextInt();
//                     Room newRoom = new Room(id, type, false, price); // الغرفة مش محجوزة ك defualt value
//                     System.out.print("Customer ID (if any, -1 if none): ");
//                     newRoom.customerId = sc.nextInt();
//                     rooms.add(newRoom);
//                     saveRooms();
//                     System.out.println(" Room added.");
//                     break;
//                 case 2:
//                     System.out.print("Room ID to update: ");
//                     int uid = sc.nextInt();
//                     Room ur = findRoom(uid);
//                     if (ur != null) {
//                         //room id ,type, price, status, customer id(if there)
//                         sc.nextLine();
//                         System.out.print("New type: ");
//                         ur.type = sc.nextLine();
//                         System.out.print("New price: ");
//                         ur.price = sc.nextInt();
//                         System.out.print("Is Reserved? (true/false): ");
//                         ur.reserved = sc.nextBoolean();
//                         System.out.print("New Customer ID (-1 if none): ");
//                         ur.customerId = sc.nextInt();
//                         saveRooms();
//                         System.out.println("Room updated.");
//                     } else {
//                         System.out.println(" Room not found.");
//                     }
//                     break;
//                 case 3:
//                 // room id
//                     System.out.print("Room ID to delete: ");
//                     int did = sc.nextInt();
//                     if (rooms.removeIf(r -> r.roomId == did)) {
//                         saveRooms();
//                         System.out.println(" Room deleted.");
//                     } else {
//                         System.out.println(" Room not found.");
//                     }
//                     break;
//                 case 4:
//                     System.out.print("Room ID to manage services: ");
//                     int rid = sc.nextInt();
//                     Room rr = findRoom(rid);
//                     if (rr != null) {
//                         while (true) {
//                             System.out.println("1. Add Service\n2. Remove Service\n3. Show Room\n0. Back");
//                             int op = sc.nextInt();
//                             if (op == 0) break;
//                             else if (op == 1) {
//                                 showServices();
//                                 System.out.print("Enter service ID: ");
//                                 int sid = sc.nextInt();

//                                 boolean serviceExists = services.stream().anyMatch(s -> s.id == sid);
//                                 if (!serviceExists) {
//                                     System.out.println(" Service ID does not exist.");
//                                 } else if (!rr.serviceIds.contains(sid)) {
//                                     rr.serviceIds.add(sid);
//                                     System.out.println(" Service added.");
//                                 } else {
//                                     System.out.println(" Service already exists.");
//                                 }
//                             } else if (op == 2) {
//                                 System.out.print("Enter service ID to remove: ");
//                                 int sid = sc.nextInt();
//                                 if (rr.serviceIds.removeIf(s -> s == sid)) {
//                                     System.out.println(" Service removed.");
//                                 } else {
//                                     System.out.println(" Service not found.");
//                                 }
//                             } else if (op == 3) {
//                                 System.out.println(rr.toFileString());
//                             }
//                         }
//                         saveRooms();
//                     } else {
//                         System.out.println(" Room not found.");
//                     }
//                     break;
//                 case 5:
//                     for (Room r : rooms) {
//                         System.out.println(r.toFileString());
//                     }
//                     break;
//                 case 0:
//                     return;
//             }
//         }
//     }

