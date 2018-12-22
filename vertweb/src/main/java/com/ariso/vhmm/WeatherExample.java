package com.ariso.vhmm;

import com.hankcs.algorithm.Viterbi;

public class WeatherExample
{
    enum Weather
    {
        Rainy,
        Sunny,
    }
    enum Activity
    {
        walk,
        shop,
        clean,
    }
    static int[] states = new int[]{Weather.Rainy.ordinal(), Weather.Sunny.ordinal()};
    static int[] observations = new int[]{Activity.walk.ordinal(), Activity.shop.ordinal(), Activity.clean.ordinal()};
    static double[] start_probability = new double[]{0.6, 0.4};
    static double[][] transititon_probability = new double[][]{
            {0.7, 0.3},
            {0.4, 0.6},
    };
    static double[][] emission_probability = new double[][]{
            {0.1, 0.4, 0.5},
            {0.6, 0.3, 0.1},
    };

    
//
//states = ('Rainy', 'Sunny')
//
//observations = ('walk', 'shop', 'clean')
// 
//start_probability = {'Rainy': 0.6, 'Sunny': 0.4}
// 
//transition_probability = {
//    'Rainy' : {'Rainy': 0.7, 'Sunny': 0.3},
//    'Sunny' : {'Rainy': 0.4, 'Sunny': 0.6},
//    }
// 
//emission_probability = {
//    'Rainy' : {'walk': 0.1, 'shop': 0.4, 'clean': 0.5},
//    'Sunny' : {'walk': 0.6, 'shop': 0.3, 'clean': 0.1},
//}
//

    public static void main(String[] args)
    {
        int[] result = Viterbi.compute(observations, states, start_probability, transititon_probability, emission_probability);
        for (int r : result)
        {
            System.out.print(Weather.values()[r] + " ");
        }
        System.out.println();
    }
}