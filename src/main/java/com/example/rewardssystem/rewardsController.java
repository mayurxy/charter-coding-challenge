package com.example.rewardssystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.example.rewardssystem.models.transaction;
import com.example.rewardssystem.service.rewardsGeneration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;
import java.util.List;

@RestController
@RequestMapping("/getrewards")
public class rewardsController {

    public static List<transaction> transactionsList = new ArrayList<transaction>();

    @Autowired
    rewardsGeneration rewardsGenerationService;

    @RequestMapping("/loaddata")
    public List<transaction> loadData(){
       /* transactionsList = new ArrayList<transaction>(); // tried to load data using JSON file but library issue.
        Json json = new Json();
        ArrayList<transaction> board = json.fromJson(ArrayList.class, transaction.class, Gdx.files.internal("records.json"));
          System.out.println(Arrays.toString(board.toArray()));
        */
        transactionsList = Arrays.asList(new transaction(1,"mj", "12/02/2019", 123),
                new transaction(1,"mj", "13/01/2019", 95),
                new transaction(1,"mj", "23/01/2019", 100),
                new transaction(2,"mj2", "25/12/2019", 110),
                new transaction(2,"mj2", "11/01/2019", 150),
                new transaction(2,"mj2", "15/02/2019", 120));

        return transactionsList;

    }

    @RequestMapping("/allrecords")
    public  Map<Integer, Map<String, Integer>> getRewards() throws ParseException {
        return rewardsGenerationService.getAllRecords(transactionsList);
    }

    @RequestMapping("/getrecord/{userid}")
    public  Map<Integer, Map<String, Integer>> getRewards(@PathVariable("userid") int userid) throws ParseException {
        return rewardsGenerationService.getIndividualRecords(transactionsList, userid);
    }
}
