package cs.man.ac.uk.tavernamobile.serverresource.xml;

import java.net.URI;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root
public class Location {
	
	@Attribute
	//@Namespace(prefix = "xlink", reference = "http://www.w3.org/1999/xlink")
	protected URI href;

	public URI getHref() {
		return href;
	}

	public void setHref(URI href) {
		this.href = href;
	}
}
