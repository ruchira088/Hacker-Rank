package com.learning;

import com.learning.challenges.GridSearch;
import com.learning.challenges.MatrixRotation;
import com.learning.challenges.SherlockAndBeast;

import java.io.FileInputStream;
import java.util.Scanner;

import static com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text.NEW_LINE;

public class MainClass
{
    private static final String INPUT_FILE_PATH = "src/main/resources/input.txt";


    public static void main(String[] args)
    {
        try
        {
            FileInputStream inputStream = new FileInputStream(INPUT_FILE_PATH);
            Scanner scanner = new Scanner(inputStream);

            StringBuilder stringBuilder = new StringBuilder();

            while(scanner.hasNextLine())
            {
                stringBuilder.append(scanner.nextLine());
                stringBuilder.append(NEW_LINE);
            }

            HackerRank hackerRank = new MatrixRotation();

            long startTime = System.currentTimeMillis();

            hackerRank.execute(stringBuilder.toString());

            System.out.println("Duration: " + (System.currentTimeMillis() - startTime) + " ms");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
