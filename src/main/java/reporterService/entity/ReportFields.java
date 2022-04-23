package reporterService.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ReportFields")
public class ReportFields {

	int id;
	String name;
	String displayName;
	String description;
	int output_ordering;
	int disabled;
	int disabledByReport;
	int invisibleByReport;
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
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getOutput_ordering() {
		return output_ordering;
	}
	public void setOutput_ordering(int output_ordering) {
		this.output_ordering = output_ordering;
	}
	public int getDisabled() {
		return disabled;
	}
	public void setDisabled(int disabled) {
		this.disabled = disabled;
	}
	public int getDisabledByReport() {
		return disabledByReport;
	}
	public void setDisabledByReport(int disabledByReport) {
		this.disabledByReport = disabledByReport;
	}
	public int getInvisibleByReport() {
		return invisibleByReport;
	}
	public void setInvisibleByReport(int invisibleByReport) {
		this.invisibleByReport = invisibleByReport;
	}
	
}
