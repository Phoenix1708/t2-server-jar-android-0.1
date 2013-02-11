package cs.man.ac.uk.tavernamobile.serverresource.xml;

import java.net.URI;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "credentials")
public class CredentialCollection {
	
	@ElementList(entry = "credential", inline = true)
	protected List<Credential> credential;
	
    @Attribute
    protected URI href;

	public List<Credential> getCredential() {
		return credential;
	}

	public void setCredential(List<Credential> credential) {
		this.credential = credential;
	}

	public URI getHref() {
		return href;
	}

	public void setHref(URI href) {
		this.href = href;
	}
}
