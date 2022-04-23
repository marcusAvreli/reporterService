package reporterService.entity;

import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "myStoredProcedure")
public class MyStoredProcedure {
	public	int id;
	public String name;
	public String description;
	public Set<String> inputParams;
	@DBTable(columnName ="PARAMETER_NAME")
	public String parameter_name;
	
	
	public String getParameter_name() {
		return parameter_name;
	}
	public void setParameter_name(String parameter_name) {
		this.parameter_name = parameter_name;
	}
	public Set<String> getInputParams() {
		return inputParams;
	}
	public void setInputParams(Set<String> inputParams) {
		this.inputParams = inputParams;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() { 
	    return "id: " + this.id+ ", name: "+this.name+ ", parameter_name: "+this.parameter_name;
	} 

}
