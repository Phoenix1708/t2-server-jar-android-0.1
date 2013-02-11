package cs.man.ac.uk.tavernamobile.serverresource.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class PasswordCredential extends CredentialDescriptor
{
    @Element(required = true)
    protected String username;
    
    @Element(required = true)
    protected String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String value) {
        this.username = value;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String value) {
        this.password = value;
    }
}
