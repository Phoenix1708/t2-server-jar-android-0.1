package cs.man.ac.uk.tavernamobile.serverresource.xml;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "trusts")
public class TrustCollection {

	@ElementList(entry = "trust", inline = true, required = false)
    protected List<TrustDescriptor> trust;
	
    @Attribute(required = false)
    protected URI href;

    public List<TrustDescriptor> getTrust() {
        if (trust == null) {
            trust = new ArrayList<TrustDescriptor>();
        }
        return this.trust;
    }

    public URI getHref() {
        return href;
    }

    public void setHref(URI value) {
        this.href = value;
    }
}
