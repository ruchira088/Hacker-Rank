package com.learning.challenges;

import com.learning.HackerRank;

import static com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text.NEW_LINE;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MatrixRotation implements HackerRank {
    @Override
    public void execute(String p_input) {
        List<Integer> integerList = Arrays.stream(p_input.split(NEW_LINE)[0].split(" "))
                .map(t -> Integer.parseInt(t))
                .collect(Collectors.toList());

        Integer rows = integerList.get(0);
        Integer colums = integerList.get(1);
        Integer rotations = integerList.get(2);

        String[][] strings = Arrays.stream(p_input.substring(p_input.indexOf(NEW_LINE) + NEW_LINE.length()).split(NEW_LINE))
                .map(line -> line.split(" ")).collect(Collectors.toList()).toArray(new String[0][0]);

        String matrixAsString = getMatrixAsString(rotate(strings, rotations));


        System.out.println(matrixAsString);
        System.out.println(compare(matrixAsString));
    }

    boolean compare(String p_result)
    {
        StringBuilder stringBuilder = new StringBuilder();
        try
        {
            FileInputStream inputStream = new FileInputStream("src/main/resources/expectedResult.txt");
            Scanner scanner = new Scanner(inputStream);

            while(scanner.hasNextLine())
            {
                stringBuilder.append(scanner.nextLine());
                stringBuilder.append(NEW_LINE);
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return p_result.trim().equals(stringBuilder.toString().trim());
    }

    private Object[][] rotate(Object[][] p_matrix, int p_rotations) {
        int rows = p_matrix.length;
        int columns = p_matrix[0].length;

        Object[] rectangle = new Object[2 * columns + 2 * (rows - 2)];

        int index = 0;

        for (int i = columns - 1; i >= 0; i--, index++) {
            rectangle[index] = p_matrix[0][i];
        }

        for (int i = 1; i < rows - 1; i++, index++) {
            rectangle[index] = p_matrix[i][0];
        }

        for (int i = 0; i < columns; i++, index++) {
            rectangle[index] = p_matrix[rows - 1][i];
        }

        for (int i = rows - 2; i > 0; i--, index++) {
            rectangle[index] = p_matrix[i][columns - 1];
        }

        Object[][] matrix = new Object[rows][columns];

        Object[] shifted = rectangle;

        for (int i = 0; i < p_rotations%rectangle.length; i++)
        {
            shifted = shiftRight(shifted);
        }

        index = 0;

        for (int i = columns - 1; i >= 0; i--, index++) {
            matrix[0][i] = shifted[index];
        }

        for (int i = 1; i < rows - 1; i++, index++) {
            matrix[i][0] = shifted[index];
        }

        for (int i = 0; i < columns; i++, index++) {
            matrix[rows - 1][i] = shifted[index];
        }

        for (int i = rows - 2; i > 0; i--, index++) {
            matrix[i][columns - 1] = shifted[index];
        }

        Object[][] innerMatrix = innerMatrix(p_matrix);

        if (innerMatrix != null) {
            matrixMerge(matrix, rotate(innerMatrix, p_rotations));
        }

        return matrix;
    }

    Object[][] matrixMerge(Object[][] p_main, Object[][] p_key) {
        for (int i = 0; i < p_key.length; i++) {
            for (int j = 0; j < p_key[0].length; j++) {
                p_main[i + 1][j + 1] = p_key[i][j];
            }
        }

        return p_main;
    }

    private Object[][] innerMatrix(Object[][] p_array) {
        if (p_array.length <= 2 || p_array[0].length <= 2) {
            return null;
        }

        int rows = p_array.length - 2;
        int columns = p_array[0].length - 2;

        Object[][] matrix = new Object[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = p_array[i + 1][j + 1];
            }
        }

        return matrix;
    }

    private Object[] shiftRight(Object[] p_array) {
        Object[] array = new Object[p_array.length];

        for (int i = 0; i < p_array.length; i++) {
            array[(i + 1) < p_array.length ? (i + 1) : 0] = p_array[i];
        }

        return array;
    }

    String getMatrixAsString(Object[][] p_matrix) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < p_matrix.length; i++)
        {
            String line = "";

            for (int j = 0; j < p_matrix[i].length; j++)
            {
                line += p_matrix[i][j] + " ";
            }

            stringBuilder.append(line.trim());
            stringBuilder.append(NEW_LINE);
        }

        return stringBuilder.toString().trim();
    }
}
