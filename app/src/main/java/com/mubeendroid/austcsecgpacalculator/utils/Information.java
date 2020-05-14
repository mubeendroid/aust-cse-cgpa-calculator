package com.mubeendroid.austcsecgpacalculator.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class Information {

    /*
        This class contains all the information.
        The first parameter of the map is subject name and the second parameter is credit number of that subject.
        Call methods according to year and semester to get corresponding data.
     */

    public Map<String, Double> get11Data() {
        Map<String, Double> map = new LinkedHashMap<>();
        map.put("CSE Theory", 3.0);
        map.put("Chemistry", 3.0);
        map.put("Physics", 3.0);
        map.put("Mathematics-I", 3.0);
        map.put("HUM Theory", 3.0);
        map.put("CSE Programming Lab", 1.5);
        map.put("CSE Fundamental Lab", 1.5);
        map.put("Hum Lab", 1.5);
        map.put("Physics Lab", 0.75);
        return map;
    }

    public Map<String, Double> get12Data() {
        Map<String, Double> map = new LinkedHashMap<>();
        map.put("Object Oriented Programming Theory", 3.0);
        map.put("Discrete Mathematics", 3.0);
        map.put("Basic Electrical Engineering Theory", 3.0);
        map.put("Mathematics-II", 3.0);
        map.put("Basic Mechanical Engineering Theory", 3.0);
        map.put("Object Oriented Programming Lab", 1.5);
        map.put("Software Development-I", 1.5);
        map.put("Basic Electrical Engineering Lab", 1.5);
        map.put("Mechanical Engineering Drawing", 0.75);
        return map;
    }

    public Map<String, Double> get21Data() {
        Map<String, Double> map = new LinkedHashMap<>();
        map.put("Society, Ethics and Technology", 3.0);
        map.put("Mathematics-III", 3.0);
        map.put("Electronic Devices & Circuits Theory", 3.0);
        map.put("Data Structures Theory", 3.0);
        map.put("Digital Logic Design Theory", 3.0);
        map.put("Electronic Devices & Circuits Lab", 1.5);
        map.put("Data Structures Lab", 1.5);
        map.put("Digital Logic Design Lab", 1.5);
        map.put("Software Development-II", 0.75);
        return map;
    }

    public Map<String, Double> get22Data() {
        Map<String, Double> map = new LinkedHashMap<>();
        map.put("Numerical Methods Theory", 3.0);
        map.put("Mathematics- IV", 3.0);
        map.put("Algorithms Theory", 3.0);
        map.put("Digital Electronics and Pulse Techniques Theory", 3.0);
        map.put("Computer Architecture", 3.0);
        map.put("Algorithms Lab", 1.5);
        map.put("Assembly Language Programming", 1.5);
        map.put("Digital Electronics and Pulse Techniques Lab", 0.75);
        map.put("Numerical Methods Lab", 0.75);
        map.put("Software Development-III", 0.75);
        return map;
    }

    public Map<String, Double> get31Data() {
        Map<String, Double> map = new LinkedHashMap<>();
        map.put("Economics and Accounting", 3.0);
        map.put("Mathematical Analysis for Computer Science", 3.0);
        map.put("Database Theory", 3.0);
        map.put("Microprocessors Theory", 3.0);
        map.put("Digital System Design Theory", 3.0);
        map.put("Database Lab", 1.5);
        map.put("Digital System Design Lab", 0.75);
        map.put("Microprocessors Lab", 0.75);
        map.put("Software Development-IV", 0.75);
        return map;
    }

    public Map<String, Double> get32Data() {
        Map<String, Double> map = new LinkedHashMap<>();
        map.put("Industrial Law and Safety Management", 3.0);
        map.put("Data Communication", 3.0);
        map.put("Operating System Theory", 3.0);
        map.put("Microcontroller Based System Design Theory", 3.0);
        map.put("Information System Design and Software Engineering Theory", 3.0);
        map.put("Operating System Lab", 1.5);
        map.put("Microcontroller Based System Design Lab", 0.75);
        map.put("Information System Design and Software Engineering Lab", 0.75);
        map.put("Software Development-V", 0.75);
        return map;
    }

    public Map<String, Double> get41Data() {
        Map<String, Double> map = new LinkedHashMap<>();
        map.put("Industrial Management", 3.0);
        map.put("Computer Networks Theory", 3.0);
        map.put("Artificial Intelligence Theory", 3.0);
        map.put("Distributed Database Systems Theory", 3.0);
        map.put("Formal Languages & Compilers Theory", 3.0);
        map.put("Project & Thesis-I", 3.0);
        map.put("Computer Networks Lab", 1.5);
        map.put("Artificial Intelligence Lab", 0.75);
        map.put("Distributed Database Systems Lab", 0.75);
        map.put("Formal Languages & Compilers Lab", 0.75);
        return map;
    }

    public Map<String, Double> get42Data() {
        Map<String, Double> map = new LinkedHashMap<>();
        map.put("Computer Graphics Theory", 3.0);
        map.put("Project and Thesis-II", 3.0);
        map.put("Option-I Theory", 3.0);
        map.put("Option-II Theory", 3.0);
        map.put("Option-III Theory", 3.0);
        map.put("Option-IV", 3.0);
        map.put("Computer Graphics Lab", 0.75);
        map.put("Option-I Lab", 0.75);
        map.put("Option-II Lab", 0.75);
        map.put("Option-III Lab", 0.75);
        return map;
    }
}
