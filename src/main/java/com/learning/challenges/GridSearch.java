package com.learning.challenges;

import com.learning.HackerRank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text.NEW_LINE;

public class GridSearch implements HackerRank
{
    private static final boolean DEBUG_MODE = true;

    @Override
    public void execute(String p_input)
    {
        int testCases = Integer.parseInt(p_input.split(NEW_LINE)[0]);

        String[] data = p_input.substring(p_input.indexOf(NEW_LINE) + 1).split(NEW_LINE);

        int line = 0;

        for(int i=0; i<testCases; i++)
        {
            String[] mainMatrix = createMatrix(line, data);
            line += mainMatrix.length + 1;

            String[] keyMatrix = createMatrix(line, data);
            line += keyMatrix.length + 1;

            if(DEBUG_MODE)
            {
                print(mainMatrix);
                print(keyMatrix);
            }

            System.out.println(containsMatrix(mainMatrix, keyMatrix));

        }
    }

    private Integer[] matches(String p_word, String p_key)
    {
        ArrayList<Integer> list = new ArrayList<>();

        if(p_word.contains(p_key))
        {
            int index = p_word.indexOf(p_key);
            list.add(index);

            list.addAll(Arrays.asList(matches(p_word.substring(index + 1), p_key)).stream()
                    .map(t -> index + 1 + t).collect(Collectors.toList()));
        }

        return list.toArray(new Integer[0]);
    }

    private boolean check(String[] p_main, int p_x, int p_y, String[] p_key)
    {
        for(int i = p_x; i < p_x + p_key.length; i++)
        {
            String substring = p_main[i].substring(p_y, p_y + p_key[0].length());
            String key = p_key[i - p_x];

            if(!substring.equals(key))
            {
                return false;
            }
        }

        return true;
    }


    public boolean containsMatrix(String[] p_main, String[] p_key)
    {
        for(int i=0; i < p_main.length; i++)
        {
            Integer[] matches = matches(p_main[i], p_key[0]);

            if(matches.length > 0)
            {
                for(Integer match: matches)
                {
                    if(check(p_main, i, match, p_key))
                    {
                        return true;
                    }
                }

            }
        }

        return false;

    }

    private String[] createMatrix(int p_line, String[] p_data)
    {
        int rows = Integer.parseInt(p_data[p_line].split(" ")[0]);
        p_line++;

        String[] matrix = new String[rows];

        for(int i=p_line; i < p_line+rows; i++)
        {
            matrix[i-p_line] = p_data[i];
        }

        return matrix;
    }

    private void print(String[] matrix)
    {
        Arrays.stream(matrix).forEach(System.out::println);
    }
}
