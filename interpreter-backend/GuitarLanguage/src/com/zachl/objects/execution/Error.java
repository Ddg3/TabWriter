package com.zachl.objects.execution;

import java.util.HashMap;

public class Error{
    public static final HashMap<String, String> ERRORS = new HashMap<>(){{
        put("Arithmetic Error", "Arithmetic requires a number, operator, and another number.");
        put("Arithmetic Operator Error", "Arithmetic operators may only be within an interval of 0 to 3 of the first number's root node");
    }};
    public String name, description;
    public Error(String name){
        this.name = name;
        this.description = ERRORS.get(name);
    }
    @Override
    public String toString(){
        return name + ":\n" + description;
    }
}
