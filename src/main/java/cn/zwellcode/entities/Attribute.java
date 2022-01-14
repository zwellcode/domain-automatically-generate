package cn.zwellcode.entities;

public class Attribute {
private String type;
private String name;
public Attribute() {
super();
}
public Attribute(String name,String type) {
super();
this.type = type;
this.name = name;
}
public String getType() {
return type;
}

public void setType(String type) {
    this.type = type;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

}