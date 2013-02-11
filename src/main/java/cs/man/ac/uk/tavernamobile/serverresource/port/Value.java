package cs.man.ac.uk.tavernamobile.serverresource.port;

import java.net.URI;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Namespace;

public abstract class Value {

	@Attribute(required = false)
	@Namespace(reference = "http://ns.taverna.org.uk/2010/port/", prefix = "port")
	protected URI href;

	public URI getHref() {
		return href;
	}

	public void setHref(URI href) {
		this.href = href;
	}
}
