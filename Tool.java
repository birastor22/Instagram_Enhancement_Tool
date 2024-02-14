/**
 * @author Ben Irastorza
 * @version 1.1337
 */
/*
    * The program does NOT require you to surrender your instagram username and password.
    * The program also is all ran locally and securely on your personal computer.
    * The user is only required to input through the command line.

    * Step 1: Download your Instagram data per the instructions on https://help.instagram.com/181231772500920
    *  MAKE SURE TO CHOOSE "JSON" FILE FORMAT AND SELECT "ALL TIME" DATA
    * Step 2: Get the path of the Following and Followers json files under "followers_and_following"
    * Step 3: Create an empty .txt file and copy the file path
    * Step 4: Run the program and paste the 3 required file paths when requested
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintWriter;

public class Tool {
    /**
     * @param args main method parameter
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String followingPath;
        String followersPath;
        String txt;

        try {
            System.out.print("Input path to following JSON file: ");
            followingPath = sc.nextLine();
            System.out.print("Input path to followers JSON file: ");
            followersPath = sc.nextLine();
            System.out.print("Input path to empty .txt file: ");
            txt = sc.nextLine();
            System.out.println("");

            ArrayList<String> followers = followersToArr(followersPath);
            ArrayList<String> following = followingToArr(followingPath);
            ArrayList<String> answer = notFollowBack(following, followers);
            writeToFile(new File(txt), answer);

        } catch (RuntimeException re) {
            System.out.println("One of the file paths was incorrect");
        }
    }

    /**
     * followingToArr parses the JSON file and creates a list of the users following.
     * @param filePath the path to the following file
     * @return the list of following
     */
    public static ArrayList<String> followingToArr(String filePath) {
        ArrayList<String> toReturn = new ArrayList<>();
        File x = new File(filePath);
        try {
            String currLine = "";
            Scanner scan = new Scanner(x);
            while (scan.hasNextLine()) {
                currLine = scan.nextLine();
                if (currLine.contains("value")) {
                    currLine = currLine.substring(20, currLine.length() - 2);
                    toReturn.add(currLine);
                }
            }
            return toReturn;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * followersToArr parses the JSON file and creates a list of the users followers.
     * @param filePath the path to the followers file
     * @return the list of followers
     */
    public static ArrayList<String> followersToArr(String filePath) {
        ArrayList<String> toReturn = new ArrayList<>();
        File x = new File(filePath);
        try {
            String currLine = "";
            Scanner scan = new Scanner(x);
            while (scan.hasNextLine()) {
                currLine = scan.nextLine();
                if (currLine.contains("value")) {
                    currLine = currLine.substring(18, currLine.length() - 2);
                    toReturn.add(currLine);
                }
            }
            return toReturn;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * The following method loops through both lists adding the flagged usernames to a new list.
     * Optimization: updated runtime to O(n)
     * @param following the list of following
     * @param followers the list of followers
     * @return the list of people not following the user back
     */
    public static ArrayList<String> notFollowBack(ArrayList<String> following, ArrayList<String> followers) {
        ArrayList<String> answer = new ArrayList<>();
        HashSet<String> followersSet = new HashSet<>(followers);
        for (String s : following) {
            if (!followersSet.contains(s)) {
                answer.add(s);
            }
        }
        return answer;
    }

    /**
     * writeToFile writes the data to a text file for the user to have.
     * @param x the file the user chose to have the data written to.
     * @param answer the list of the people that don't follow the user back.
     */
    public static void writeToFile(File x, ArrayList<String> answer) {
        try {
            PrintWriter pw = new PrintWriter(x);
            for (String s : answer) {
                pw.println(s);
            }
            pw.close();
            System.out.println("The data has successfully been written to the text file.");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
