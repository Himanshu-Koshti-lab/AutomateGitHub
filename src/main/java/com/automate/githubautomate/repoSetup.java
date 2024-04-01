package com.automate.githubautomate;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class repoSetup {

    public static void main(String[] args) {

        String repo = "https://github.com/Himanshu-Koshti-lab/LogbackProject.git";

        String repoName = Arrays.stream(repo.split("/")).filter(string -> string.contains(".git")).collect(Collectors.joining(","));

        List<String> name = List.of(repoName.split(","));

        repoName = name.get(0).replace(".git", "");

        String[] command = {"git", "clone",};
        ProcessCommand(command, "");

        String[] checkBranch = {"git", "branch", "-a"};
        ProcessCommand(checkBranch, "");

        System.out.println(" Create working Branch");

        String[] checkoutBranch = {"git", "checkout", "dev"};
        ProcessCommand(checkoutBranch, repoName);
        ProcessCommand(checkBranch, repoName);


        System.out.println("Current Working Branch with Status");
        String[] checkCurrentBranch = {"git", "status"};
        ProcessCommand(checkCurrentBranch, repoName);


        //Do some changes in repo


        String[] commitBranch = {"git", "commit", "-a", "-m", "Automate Commit"};
        ProcessCommand(commitBranch, repoName);


        String[] PushBranch = {"git", "push", "origin", "dev"};
        ProcessCommand(PushBranch, repoName);
    }

    private static void ProcessCommand(String[] command, String string) {
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.directory(new File("E:\\" + string));
        Process process;
        try {
            process = processBuilder.start();
            process.waitFor();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String s;

        try {
            while ((s = reader.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
