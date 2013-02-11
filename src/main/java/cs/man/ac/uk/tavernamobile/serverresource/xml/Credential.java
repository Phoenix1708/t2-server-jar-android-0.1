package cs.man.ac.uk.tavernamobile.serverresource.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "credentials")
public class Credential {
	
	@Element(required = false)
    protected KeyPairCredential keypair;
    @Element(required = false)
    protected PasswordCredential userpass;
    
	public KeyPairCredential getKeypair() {
		return keypair;
	}
	public void setKeypair(KeyPairCredential keypair) {
		this.keypair = keypair;
	}
	public PasswordCredential getUserpass() {
		return userpass;
	}
	public void setUserpass(PasswordCredential userpass) {
		this.userpass = userpass;
	}
}
