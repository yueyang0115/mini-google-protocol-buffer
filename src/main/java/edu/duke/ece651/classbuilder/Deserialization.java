package edu.duke.ece651.classbuilder;

import java.util.ArrayList;

public class Deserialization {
  private String classname;
  private ArrayList<OneField> fieldlist;
  private StringBuilder content;

  public Deserialization(String myname, ArrayList<OneField> mylist) {
    this.classname = myname;
    this.fieldlist = mylist;
    this.content = new StringBuilder();
    Deserialize();
  }

  private void Deserialize() {
    content
        .append("public static " + this.classname + " read" + this.classname
            + "(JSONObject js) throws JSONException{\n")
        .append("HashMap<Integer,Object> objmap = new HashMap<Integer,Object>();\n")
        .append("return " + this.classname + "_helper(js,objmap);\n")
        .append("}\n\n")
        .append("public static " + this.classname + " " + this.classname
            + "_helper(JSONObject js, HashMap<Integer, Object> objmap){\n")
        .append(this.classname + " ans = new " + this.classname + "();\n")
        .append("int id = js.optInt(\"id\");\n")
        .append("if(id==0){\n")
        .append("id=js.optInt(\"ref\");\n")
        .append("}\n")
        .append("if(objmap.containsKey(id)){\n")
        .append("return (" + this.classname + ")objmap.get(id);\n")
        .append("}\n")
        .append("else{\n")
        .append("objmap.put(id,ans);\n")
        .append("JSONArray val_arr = js.optJSONArray(\"values\");\n");
    WapperMap wraper = new WapperMap();
    for (int i = 0; i < fieldlist.size(); i++) {
      String fieldname = fieldlist.get(i).getName();
      String fieldName = new Capitalizer(fieldname).ToCapitalize();
      String fieldtype = fieldlist.get(i).getType();
      content.append("JSONObject val_obj_" + i + " = val_arr.getJSONObject(" + i + ");\n");
      if (wraper.getWapper(fieldtype) != "None") {
        // System.out.println("wraper.getWapper(fieldtype)=");
        // System.out.println(wraper.getWapper(fieldtype));
        content.append("ans.set" + fieldName + "((" + fieldtype + ")val_obj_" + i + ".opt(\""
            + fieldname + "\"));\n");
      } else {
        content.append("ans.set" + fieldName + "(" + fieldName + "_helper((JSONObject)val_obj_" + i
            + ".opt(\"" + fieldname + "\"),objmap));\n");
      }
    }
    content.append("return ans;\n").append("}\n}\n\n");
  }

  public String getCode() {
    return content.toString();
  }
}