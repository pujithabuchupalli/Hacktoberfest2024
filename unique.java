import java.util.HashSet;
import java.util.Scanner;

public class FindDuplicates {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        int n = scanner.nextInt();

        int[] arr = new int[n];
        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        HashSet<Integer> seen = new HashSet<>();
        System.out.println("Duplicates in the array:");
        boolean foundDuplicate = false;

        for (int num : arr) {
            if (!seen.add(num)) {
                System.out.println(num);
                foundDuplicate = true;
            }
        }

        if (!foundDuplicate) {
            System.out.println("No duplicates found.");
        }

        scanner.close();
    }
}
