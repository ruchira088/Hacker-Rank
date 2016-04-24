package com.learning.challenges;

import org.junit.Test;

public class MatrixRotationTest
{
    @Test
    public void testMatrixMerge()
    {
        MatrixRotation matrixRotation = new MatrixRotation();

        String[] row_0 = {"1", "2", "3", "4"};
        String[] row_1 = {"5", null, null, "8"};
        String[] row_2 = {"9", null, null, "12"};
        String[] row_3 = {"13", "14", "15", "16"};

        String[][] matrix = new String[4][4];
        matrix[0] = row_0;
        matrix[1] = row_1;
        matrix[2] = row_2;
        matrix[3] = row_3;

        String[] row_0_K = {"A", "4"};
        String[] row_1_K = {"B", "C"};

        String[][] key = new String[2][2];
        key[0] = row_0_K;
        key[1] = row_1_K;

        matrixRotation.getMatrixAsString(matrix);
        matrixRotation.getMatrixAsString(matrixRotation.matrixMerge(matrix, key));
    }
}
