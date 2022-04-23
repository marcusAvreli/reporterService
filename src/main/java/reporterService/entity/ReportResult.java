package reporterService.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ReportResult")
public class ReportResult {
	String ir_name;
	
	String ir_state;
	String ir_type;
	String ir_source;
	
	String bundle_name;
	Long bundle_created;
	
	String name;
	Long created;

	public String getBundle_name() {
		return bundle_name;
	}

	public void setBundle_name(String bundle_name) {
		this.bundle_name = bundle_name;
	}

	public Long getBundle_created() {
		return bundle_created;
	}

	public void setBundle_created(Long bundle_created) {
		this.bundle_created = bundle_created;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCreated() {
		return created;
	}

	public void setCreated(Long created) {
		this.created = created;
	}

	public String getIr_state() {
		return ir_state;
	}

	public void setIr_state(String ir_state) {
		this.ir_state = ir_state;
	}

	public String getIr_type() {
		return ir_type;
	}

	public void setIr_type(String ir_type) {
		this.ir_type = ir_type;
	}

	public String getIr_source() {
		return ir_source;
	}

	public void setIr_source(String ir_source) {
		this.ir_source = ir_source;
	}

	public String getIr_name() {
		return ir_name;
	}

	public void setIr_name(String ir_name) {
		this.ir_name = ir_name;
	}
}
