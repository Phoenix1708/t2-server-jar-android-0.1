package cs.man.ac.uk.tavernamobile.serverresource.xml;

import java.net.URI;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

@Root(name = "run")
public class TavernaRun {

	@Text
	protected String value;
	
	@Attribute(required = false)
	//@Namespace(prefix = "href", reference = "http://www.w3.org/1999/xlink")
	protected URI href;

	@Attribute(required = false)
	//@Namespace(prefix = "serverVersion", reference = "http://www.w3.org/1999/xlink")
	protected String serverVersion;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public URI getHref() {
		return href;
	}

	public void setHref(URI value) {
		this.href = value;
	}

	public String getServerVersion() {
		return serverVersion;
	}

	public void setServerVersion(String value) {
		this.serverVersion = value;
	}

}