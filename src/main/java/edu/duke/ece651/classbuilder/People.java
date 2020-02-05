package edu.duke.ece651.classbuilder;
import java.util.*;
import org.json.*;

public class People{

private int age;
private Grade grade;

public People (){
this.grade = new Grade();
}

public int getAge(){
return age;
}
public void setAge(int age){
this.age=age;
}

public Grade getGrade(){
return grade;
}
public void setGrade(Grade grade){
this.grade=grade;
}

public JSONObject toJSON() throws JSONException{
System.out.println("going into tojson");
HashMap<Object,Integer> objectmap = new HashMap<Object,Integer>();
return Helper(objectmap);
}

public JSONObject Helper(HashMap<Object,Integer> objectmap){
JSONObject ans = new JSONObject();
System.out.println("object map size:" + objectmap.size());
if(objectmap.containsKey(this)){
ans.put("ref",objectmap.get(this));
}
else{
objectmap.put(this,objectmap.size()+1);
ans.put("id",objectmap.size());
ans.put("type","People");
JSONArray myarray = new JSONArray();
JSONObject js_0 = new JSONObject();
js_0.put("age",this.age);
myarray.put(js_0);
JSONObject js_1 = new JSONObject();
js_1.put("grade",grade.Helper(objectmap));
myarray.put(js_1);
ans.put("values",myarray);
}
return ans;
}

}