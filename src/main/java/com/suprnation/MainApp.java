package com.suprnation;

import com.google.common.base.Joiner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class MainApp {

    Node minPath(List<List<Node>> vals)
    {
        Node[] calc = new Node[vals.stream().mapToInt(List::size).sum()];

        int valSize = vals.size() - 1;


        // Traverse in bottom up style
        for (int row = vals.size() - 1; row >= 0; row--) {

            if (row == vals.size()-1){

                // base case
                for (int k = 0; k < vals.get(valSize).size(); k++) {
                    calc[k] = vals.get(valSize).get(k);
                }

                continue;
            }

            for (int col = 0; col < vals.get(row + 1).size() - 1; col++) {

                int minVal = calc[col].minOf(calc[col + 1]);

                calc[col].setValue(vals.get(row).get(col).getValue() + minVal);

                if (calc[col].getPath().isEmpty()){
                    // base case
                    calc[col].getPath().add(0, minVal);

                }

                // keep track of where we came from
                calc[col].getPath().add(0, vals.get(row).get(col).getValue());
            }
        }

        return calc[0];
    }

    public static void main(String[] args) throws IOException {


        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        List<List<Node>> vals = newArrayList();

        String str;
        while ((str = input.readLine()) != null) {
            vals.add(Node.of(str));
        }

        Node n = new MainApp().minPath(vals);
        System.out.println(Joiner.on(" + ").join(n.getPath()) + " = "+n.getValue());
    }
}
