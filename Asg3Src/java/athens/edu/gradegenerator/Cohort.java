package athens.edu.gradegenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class Cohort {
    public static Cohort sCohort;
    private HashMap<String, Integer> studentMap = new HashMap<String, Integer>();
    private Cohort() {
        // TODO: Put test data into the list for now
        studentMap.put("Lewis",40);
        studentMap.put("Alpha",100);
        studentMap.put("Smith",10);
        studentMap.put("Jones",92);
    }
    public static Cohort getCohort() {
        if (sCohort == null) {
            sCohort = new Cohort();
        }
        return sCohort;
    }
    public Integer getStudentGrade(String name) {
        return studentMap.get(name);
    }
    public void addStudent(String name, Integer grade) {
        studentMap.put(name,grade);
    }
    public void setGrade(String name, Integer grade) {
        studentMap.put(name,grade);
    }
    public Boolean contains(String name) {
        return studentMap.containsKey(name);
    }
    public String getAStudent() {
        Set<String> keys = studentMap.keySet();
        TreeSet<String> keysInTree = new TreeSet<String>(keys);
        return (String) keysInTree.first();
    }

    public ArrayList<String> buildAList() {
        return new ArrayList<String>(studentMap.keySet());
    }
}
