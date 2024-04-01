package com.automate.githubautomate;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class repoSetup {
    static int i = 0;
    public static void main(String[] args) {
        String[] command = {"git", "clone", "https://github.com/Himanshu-Koshti-lab/LogbackProject.git"};
        ProcessCommand(command, "");
        String[] checkBranch = {"git", "branch", "-a"};
        ProcessCommand(checkBranch, "");
        System.out.println(" Create Dev Branch");
        String[] checkoutBranch = {"git", "checkout", "dev"};
        ProcessCommand(checkoutBranch, "LogbackProject");
        ProcessCommand(checkBranch,"LogbackProject");
        System.out.println("Current Working Branch with Status");
        String[] checkCurrentBranch = {"git", "status" };
        ProcessCommand(checkCurrentBranch, "LogbackProject");

    }

    private static void ProcessCommand(String[] command , String string) {
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

        try{
            while ((s = reader.readLine()) != null){
                System.out.println(s);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
