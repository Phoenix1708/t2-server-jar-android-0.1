package cs.man.ac.uk.tavernamobile.serverresource.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "securityDescriptor")
public class SecurityDescriptor extends VersionedElement {

	@Element
	protected String owner;
	
	@Element
	protected Location permissions;
	
	@Element
	protected CredentialCollection credentials;
	
	@Element
	protected TrustCollection trusts;

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Location getPermissions() {
		return permissions;
	}

	public void setPermissions(Location permissions) {
		this.permissions = permissions;
	}

	public CredentialCollection getCredentials() {
		return credentials;
	}

	public void setCredentials(CredentialCollection credentials) {
		this.credentials = credentials;
	}

	public TrustCollection getTrusts() {
		return trusts;
	}

	public void setTrusts(TrustCollection trusts) {
		this.trusts = trusts;
	}
}
