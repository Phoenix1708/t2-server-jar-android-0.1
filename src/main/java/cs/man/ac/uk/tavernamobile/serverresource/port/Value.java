package cs.man.ac.uk.tavernamobile.serverresource.port;

import java.net.URI;

import org.simpleframework.xml.Attribute;

public abstract class Value {

	@Attribute
	protected URI href;

	public URI getHref() {
		return href;
	}

	public void setHref(URI href) {
		this.href = href;
	}
}
