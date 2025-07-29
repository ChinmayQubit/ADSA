import java.util.Scanner;

public class Hash {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter hash table size: ");
        int size = sc.nextInt();

        if (size <= 0) {
            System.out.println("Size must be greater than 0");
        } else {
            int sum = 0;
            for (int i = 0; i < name.length(); i++) {
                sum += name.charAt(i);
            }
            int pos = sum % size;
            System.out.println("ASCII Sum = " + sum);
            System.out.println("Hash Position = " + pos);
        }

        sc.close();
    }
}
