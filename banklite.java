import java.sql.*;
import java.util.Scanner;

public class banklite {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== BANK MENU =====");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Check Balance");
        System.out.print("Enter choice: ");

        int choice = sc.nextInt();

        try {
            String url = "jdbc:mysql://localhost:3306/banklite";
            String user = "root";
            String pass = "@Msr8986";

            Connection con = DriverManager.getConnection(url, user, pass);
            System.out.println("Connected to MySQL successfully!");

            Statement st = con.createStatement();

            switch (choice) {

                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.next();

                    System.out.print("Enter balance: ");
                    double bal = sc.nextDouble();

                    st.executeUpdate(
                            "INSERT INTO accounts(name,balance) VALUES('" + name + "'," + bal + ")");
                    System.out.println("Account Created Successfully!");
                    break;

                case 2:
                    System.out.print("Enter ID: ");
                    int id1 = sc.nextInt();

                    System.out.print("Enter amount: ");
                    double amt1 = sc.nextDouble();

                    st.executeUpdate(
                            "UPDATE accounts SET balance = balance + " + amt1 + " WHERE id=" + id1);
                    System.out.println("Amount Deposited!");
                    break;

                case 3:
                    System.out.print("Enter ID: ");
                    int id2 = sc.nextInt();

                    System.out.print("Enter amount: ");
                    double amt2 = sc.nextDouble();

                    st.executeUpdate(
                            "UPDATE accounts SET balance = balance - " + amt2 + " WHERE id=" + id2);
                    System.out.println("Amount Withdrawn!");
                    break;

                case 4:
                    ResultSet rs = st.executeQuery("SELECT * FROM accounts");
                    while (rs.next()) {
                        System.out.println(
                                rs.getInt("id") + " " +
                                        rs.getString("name") + " " +
                                        rs.getDouble("balance"));
                    }
                    break;

                default:
                    System.out.println("Invalid Option");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
