package interview.StreamInterview.questions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ImplementStreamQuestion {
    public static void main(String[] args) {
        // Q1. How many male and female employees are there in the organization ?
        List<Employees> list = new ArrayList<>();
        setEmployee(list);
        var result = list.stream().collect(Collectors.groupingBy(Employees::getGender, Collectors.counting()));
        System.out.println(result);
    }
    public static void setEmployee(List<Employees> list){
        list.add(new Employees("John", "Doe", "male"));
    }
}

