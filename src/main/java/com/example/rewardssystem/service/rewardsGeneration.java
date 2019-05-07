package com.example.rewardssystem.service;

import com.example.rewardssystem.models.transaction;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class rewardsGeneration {

    public Map<Integer, Map<String, Integer>> getAllRecords(List<transaction> transactionsList ){
        Map<Integer, Map<String, Integer>> userRewards = new HashMap<Integer, Map<String, Integer>>();
        Map<Integer, Map<String, Integer>> individualRewardsResponse = new HashMap<Integer, Map<String, Integer>>();
        Map<String, Integer> monthlyPurchases;
        Map<String, Integer> monthlypurchaseValue = new HashMap<String, Integer>();
        Map<String, Integer> monthlyRewards = new HashMap<String, Integer>();

        String monthName;

        for(transaction tr1 : transactionsList){ //iterating through the map of map to calculate the total purchase value
            if(userRewards.containsKey(tr1.getUserId())){
                monthlyPurchases = userRewards.get(tr1.getUserId());
                if(monthlyPurchases.containsKey(getMonth(tr1.getPurchaseDate()))){
                    int olderValue= monthlyPurchases.get(getMonth(tr1.getPurchaseDate()));
                    monthlyPurchases.remove(getMonth(tr1.getPurchaseDate()));
                    monthlyPurchases.put(getMonth(tr1.getPurchaseDate()), (olderValue + tr1.getPurchaseValue()));
                }
                else{
                    monthlyPurchases.put(getMonth(tr1.getPurchaseDate()), tr1.getPurchaseValue());
                }

            }else {
                //monthlyPurchases.clear();
                monthlyPurchases = new HashMap<String, Integer>();
                monthlyPurchases.put(getMonth(tr1.getPurchaseDate()), tr1.getPurchaseValue());
                System.out.println(tr1.getUserId());
                userRewards.put(tr1.getUserId(),monthlyPurchases);
            }
        }

        for(int key1 : userRewards.keySet()) {
            monthlypurchaseValue = userRewards.get(key1);
            int totalrewards = 0;
            for (String key : monthlypurchaseValue.keySet()) {
                totalrewards += calculateRewardsPoints(monthlypurchaseValue.get(key));
                monthlyRewards.put(key, calculateRewardsPoints(monthlypurchaseValue.get(key)));
            }
            monthlyRewards.put("Total Reward points", totalrewards);
            individualRewardsResponse.put(key1, monthlyRewards);
            monthlyRewards = new HashMap<String, Integer>();
        }
        return individualRewardsResponse;
    }


    public String getMonth(String input){
        String monthName;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "dd/MM/yyyy" );
        LocalDate localDate = LocalDate.parse( input , formatter );
        monthName = localDate.getMonth().getDisplayName( TextStyle.FULL , Locale.CANADA );
        return monthName;
    }

    public int calculateRewardsPoints(int purchase){
        int finalReward = 0;
        if (purchase > 100) { //added the formula for calculating the reward points
            finalReward = 2 * (purchase - 100) + 1 * (purchase - 50);

        } else if (purchase > 50) {
            finalReward = 1 * (purchase - 50);
        }
        return finalReward;
    }

    public Map<Integer, Map<String, Integer>> getIndividualRecords(List<transaction> transactionsList , int userId){
        Map<Integer, Map<String, Integer>> userRewards = new HashMap<Integer, Map<String, Integer>>();
        Map<Integer, Map<String, Integer>> individualRewardsResponse = new HashMap<Integer, Map<String, Integer>>();
        Map<String, Integer> monthlyPurchases;
        Map<String, Integer> monthlypurchaseValue = new HashMap<String, Integer>();
        Map<String, Integer> monthlyRewards = new HashMap<String, Integer>();

        String monthName;

        for(transaction tr1 : transactionsList){
            if(tr1.getUserId() == userId){
            if(userRewards.containsKey(tr1.getUserId())){
                monthlyPurchases = userRewards.get(tr1.getUserId());
                if(monthlyPurchases.containsKey(getMonth(tr1.getPurchaseDate()))){
                    int olderValue= monthlyPurchases.get(getMonth(tr1.getPurchaseDate()));
                    monthlyPurchases.remove(getMonth(tr1.getPurchaseDate()));
                    monthlyPurchases.put(getMonth(tr1.getPurchaseDate()), (olderValue + tr1.getPurchaseValue()));
                }
                else{
                    monthlyPurchases.put(getMonth(tr1.getPurchaseDate()), tr1.getPurchaseValue());
                }

            }else {
                //monthlyPurchases.clear();
                monthlyPurchases = new HashMap<String, Integer>();
                monthlyPurchases.put(getMonth(tr1.getPurchaseDate()), tr1.getPurchaseValue());
                System.out.println(tr1.getUserId());
                userRewards.put(tr1.getUserId(),monthlyPurchases);
                }
            }
        }

        for(int key1 : userRewards.keySet()) {
            monthlypurchaseValue = userRewards.get(key1);
            int totalrewards = 0;
            for (String key : monthlypurchaseValue.keySet()) {
                totalrewards += calculateRewardsPoints(monthlypurchaseValue.get(key));
                monthlyRewards.put(key, calculateRewardsPoints(monthlypurchaseValue.get(key)));
            }
            monthlyRewards.put("Total Reward points", totalrewards);
            individualRewardsResponse.put(key1, monthlyRewards);
            monthlyRewards = new HashMap<String, Integer>();
        }
        return individualRewardsResponse;
    }
}
