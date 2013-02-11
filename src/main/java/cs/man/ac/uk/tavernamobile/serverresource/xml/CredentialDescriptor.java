package cs.man.ac.uk.tavernamobile.serverresource.xml;

import java.net.URI;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

public abstract class CredentialDescriptor {

    @Element(required = false)
    protected URI serviceURI;
    
    @Attribute(required = false)
    protected URI href;

    public URI getServiceURI() {
        return serviceURI;
    }

    public void setServiceURI(URI value) {
        this.serviceURI = value;
    }

    public URI getHref() {
        return href;
    }

    public void setHref(URI value) {
        this.href = value;
    }

}
