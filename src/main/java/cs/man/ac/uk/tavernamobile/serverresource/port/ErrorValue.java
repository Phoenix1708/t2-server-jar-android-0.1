package cs.man.ac.uk.tavernamobile.serverresource.port;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "error")
public class ErrorValue extends Value {

	@Attribute
	//@Namespace(prefix = "port", reference = "http://ns.taverna.org.uk/2010/port/")
	protected Integer depth;
	
	@Attribute
	//@Namespace(prefix = "port", reference = "http://ns.taverna.org.uk/2010/port/")
	protected String errorFile;
	
	@Attribute
	//@Namespace(prefix = "port", reference = "http://ns.taverna.org.uk/2010/port/")
	protected Long errorByteLength;

	public Integer getDepth() {
		return depth;
	}
	public void setDepth(Integer depth) {
		this.depth = depth;
	}
	public String getErrorFile() {
		return errorFile;
	}
	public void setErrorFile(String errorFile) {
		this.errorFile = errorFile;
	}
	public Long getErrorByteLength() {
		return errorByteLength;
	}
	public void setErrorByteLength(Long errorByteLength) {
		this.errorByteLength = errorByteLength;
	}
}
