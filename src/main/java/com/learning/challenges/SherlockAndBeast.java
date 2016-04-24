package com.learning.challenges;

import com.learning.HackerRank;

import java.util.Arrays;
import java.util.stream.Stream;

import static com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text.NEW_LINE;

public class SherlockAndBeast implements HackerRank {

    @Override
    public void execute(String p_input)
    {
        Stream<Integer> integerStream = Arrays.stream(p_input.substring(p_input.indexOf(NEW_LINE) + 1).split(NEW_LINE))
                .map(t -> Integer.parseInt(t));

        integerStream.map(this::findNumber).forEach(number -> System.out.println(number));
    }

    private String findNumber(int p_number)
    {
        for(int i=p_number/3; i >= 0; i--)
        {
            int result = 0;

            for(int j=0; result<=p_number; j++)
            {
                result = 3*i + 5*j;

                if(result == p_number)
                {
                    return createNumber(i, j);
                }
            }
        }

        return createNumber(0, 0);
    }

    private String createNumber(int p_threes, int p_fives)
    {
        StringBuilder stringBuilder = new StringBuilder();

        for(int i=0; i<p_threes; i++)
        {
            stringBuilder.append("555");
        }

        for(int i=0; i<p_fives; i++)
        {
            stringBuilder.append("33333");
        }

        String string = stringBuilder.toString();

        return string.isEmpty() ? "-1" : string;
    }
}
