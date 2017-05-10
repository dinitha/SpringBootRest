package com.example.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * Created by D on 5/10/2017.
 */
@RestController
public class HelloController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/person")
    public @ResponseBody Person dailyStats(@RequestParam Integer id) {
        String query = "SELECT first_name, last_name, age" +
                " from person where person.id = " + id;

        return jdbcTemplate.queryForObject(query, (resultSet, i) -> {
            return new Person(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3));
        });

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "Hello World!";
    }


}
