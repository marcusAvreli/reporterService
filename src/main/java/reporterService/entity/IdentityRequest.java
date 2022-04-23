package reporterService.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "identityRequest")
public class IdentityRequest {
String id;
String name;
String state;
String type;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
}
