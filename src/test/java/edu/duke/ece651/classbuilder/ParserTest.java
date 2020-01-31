package edu.duke.ece651.classbuilder;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

public class ParserTest {
  @Test
  public void test_Parser() {
    JSONObject myobject = new JSONObject(
        "{'classes':[{'name':'Course','field':[{'name':'numStudents','type':'int'},{'name':'instructor','type':'Faculty'}]}]}");
    Parser myparser = new Parser(myobject);
    assertEquals("", myparser.getPack());
    assertEquals("Faculty", myparser.getClassmap().get("Course").get("instructor"));
    System.out.println(myparser.getClassmap());
  }
}