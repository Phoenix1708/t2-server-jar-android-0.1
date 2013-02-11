package cs.man.ac.uk.tavernamobile.serverresource.port;

import java.net.URI;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "input")
public class InputPort extends Port {
	
	@Attribute
	//@Namespace(reference = "http://www.w3.org/1999/xlink")
    protected URI href;

	public URI getHref() {
		return href;
	}

	public void setHref(URI href) {
		this.href = href;
	}
}
