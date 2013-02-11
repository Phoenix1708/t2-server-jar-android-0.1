package cs.man.ac.uk.tavernamobile.serverresource.port;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "value")
public class LeafValue extends Value {

	@Attribute
	//@Namespace(reference = "http://ns.taverna.org.uk/2010/port/", prefix = "port")
    protected String contentFile;
	
	@Attribute
	//@Namespace(reference = "http://ns.taverna.org.uk/2010/port/", prefix = "port")
    protected String contentType;
	
	@Attribute
	//@Namespace(reference = "http://ns.taverna.org.uk/2010/port/", prefix = "port")
    protected Long contentByteLength;
	
	public String getContentFile() {
		return contentFile;
	}
	
	public void setContentFile(String contentFile) {
		this.contentFile = contentFile;
	}
	
	public String getContentType() {
		return contentType;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public Long getContentByteLength() {
		return contentByteLength;
	}
	
	public void setContentByteLength(Long contentByteLength) {
		this.contentByteLength = contentByteLength;
	}	
}
