package cs.man.ac.uk.tavernamobile.serverresource.port;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root
public class Port {
	
	 @Attribute(required = true)
	 @Namespace(prefix = "port", reference = "http://ns.taverna.org.uk/2010/port/")
	 protected String name;
	 
	 @Attribute(required = false)
	 @Namespace(prefix = "port", reference = "http://ns.taverna.org.uk/2010/port/")
	 protected Integer depth;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}
}
