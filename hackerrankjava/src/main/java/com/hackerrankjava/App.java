package com.hackerrankjava;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import com.hackerrankjava.divisiblesumpairs.DivisibleSumPairs;

//TODO: Cleanup code, enough experiments and messy stuff! :)
public class App {

    private static String relativePath =  "./HackerRankJava/hackerrankjava/src/main/java/com/hackerrankjava";

    public static void main(String[] args) throws InterruptedException, IOException {
        
        clearConsole();

        System.out.println("All available challenges include a solution, an input and an expected output .txt files (taken from HackerRank).\n\n");

        HashMap<String, String> challenges = getAllChallenges();

        Scanner scanner = new Scanner(System.in);
        boolean selectAnotherChallenge = true;
        while (selectAnotherChallenge) {
            selectAnotherChallenge=false;

            System.out.println("List of available challenges: ");
            for (String challenge: challenges.values()) {
                System.out.println("-" + challenge);
            }

            System.out.printf("\nInsert challenge to test: ");

            String selectedChallenge = scanner.nextLine();

            boolean rerun = true;
            while (rerun) {
                rerun=false;
                boolean invalidChallenge = false;
                if (selectedChallenge == "q" || selectedChallenge == "quit" || selectedChallenge == "e" || selectedChallenge == "exit") {
                    scanner.close();
                    return;
                } else if (Arrays.stream(Constants.DIVISIBLE_SUM_PAIRS).anyMatch(selectedChallenge::equals)) {
                    System.out.println("\n" + Constants.DIVISIBLE_SUM_PAIRS[0] + " Output:");
                    System.setIn(new FileInputStream(new File(getRelativePath() + '/' + challenges.get(Constants.DIVISIBLE_SUM_PAIRS[0]) + "/input.txt")));
                    DivisibleSumPairs.main(args);

                    System.out.println("\nExpected Output:");
                    File outputFile = new File(getRelativePath() + '/' + challenges.get(Constants.DIVISIBLE_SUM_PAIRS[0]) + "/expected_output.txt");
                    Scanner outputFileScanner = new Scanner(outputFile);

                    while (outputFileScanner.hasNextLine()) {
                        System.out.println(outputFileScanner.nextLine());
                    }
                    System.out.println();
                    outputFileScanner.close();
                } else {
                    System.out.println("Invalid challenge\n\n\n");
                    invalidChallenge = true;
                }

                if (!invalidChallenge) {
                    boolean rerunValidAnswer = false;
                    while (!rerunValidAnswer) {
                        System.out.println("Do you wish to rerun the challenge? (Y/N)");
                        String rerunInput = scanner.nextLine();
                            switch(rerunInput.toLowerCase()) {
                                case "y":
                                    rerun=true;
                                    rerunValidAnswer=true;
                                    break;
                                case "n":
                                    rerun=false;
                                    rerunValidAnswer=true;
                                    break;
                                default:
                                    break;
                            }
                    }

                    boolean newSelectionValidAnswer = false;
                    while (!newSelectionValidAnswer && !rerun) {
                        System.out.println("Do you wish to select another challenge? (Y/N)");
                        String selectAnotherChallengeInput = scanner.nextLine();
                            switch(selectAnotherChallengeInput.toLowerCase()) {
                                case "y":
                                    selectAnotherChallenge=true;
                                    newSelectionValidAnswer=true;
                                    break;
                                case "n":
                                    selectAnotherChallenge=false;
                                    newSelectionValidAnswer=true;
                                    break;
                                default:
                                    break;
                            }
                    }
                } else {
                    selectAnotherChallenge=true;
                }
            }
        }
        scanner.close();
        printGoku();
    }

    private static void clearConsole() throws InterruptedException, IOException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    private static HashMap<String, String> getAllChallenges() {
        HashMap<String, String> res = new HashMap<>();
        
        String[] directories = getDirectories();
        for (String directory: directories) {
            String challengeName = getJavaFileFromDirectory(getRelativePath() + "/" + directory);
            challengeName = formatChallengeName(challengeName);
            res.put(challengeName, directory);
        }

        return res;
    }

    private static String[] getDirectories() {
        File directory = new File(getRelativePath());
        String[] list = directory.list(new FilenameFilter(){
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
        return list;
    }

    private static String getJavaFileFromDirectory(String path) {
        File directory = new File(path);
        String[] list = directory.list(new FilenameFilter(){
            public boolean accept(File current, String name) {
                return name.endsWith(".java");
            }
        });
        return list[0].substring(0, list[0].length() - 5);
    }

    private static String getRelativePath() {
        return relativePath;
    }

    private static String formatChallengeName(String challenge) {
        String res = "" + challenge.charAt(0);
        for(int i = 1; i < challenge.length(); i++) {
            if (challenge.charAt(i) >= 'A' && challenge.charAt(i) <= 'Z') {
                res+=" ";
            }
            res+=challenge.charAt(i);
        }
        return res;
    }

    
    //FIXME: Fix Goku
    private static void printGoku() {
        System.out.println( "                   `\\-.   `\n" +
                            "        \\ `.  `\n" +
                            "         \\  \\ |\n" +
                            "__.._    |   \\.\n" +
                            "..---~~     ~ . |    Y\n" +
                            "~-.          `|    |\n" +
                            "`.               `~~--.\n" +
                            "\\                    ~.\n" +
                            " \\                     \\__. . -- -  .\n" +
                            ".-~~~~~      ,    ,            ~~~~~~---...._\n" +
                            ".-~___        ,'/  ,'/ ,'\\          __...---~~~\n" +
                            "~-.    /._\\_( ,(/_. 7,-.    ~~---...__\n" +
                            "_...>-  P\"\"6=`_/\"6\"~   6)    ___...--~~~\n" +
                            "~~--._ \\`--') `---'   9'  _..--~~~\n" +
                            "    ~\\ ~~/_  ~~~   /`-.--~~\n" +
                            "      `.  ---    .'   \\_\n" +
                            "        `. \" _.-'     | ~-.,-------._\n" +
                            "    ..._../~~   ./       .-'    .-~~~-.\n" +
                            ",--~~~ ,'...\\` _./.----~~.'/    /'       `-\n" +
                            "_.-(      |\\    `/~ _____..-' /    /      _.-~~`.\n" +
                            "/   |     /. ^---~~~~       ' /    /     ,'  ~.   \\\n" +
                            "(    /    (  .           _ ' /'    /    ,/      \\   )\n" +
                            "(`. |     `\\   - - - - ~   /'      (   /         .  |\n" +
                            "\\.\\|       \\            /'        \\  |`.           /\n" +
                            "/.'\\\\      `\\         /'           ~-\\         .  /\\\n" +
                            "/,   (        `\\     /'                `.___..-      \\\n" +
                            "| |    \\         `\\_/'                  //      \\.     |\n" +
                            "| |     |                             /' |       |     |");
    }
}
