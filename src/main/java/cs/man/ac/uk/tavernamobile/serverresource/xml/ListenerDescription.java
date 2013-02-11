package cs.man.ac.uk.tavernamobile.serverresource.xml;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Root;

@Root(name = "listener")
//@Namespace(reference = "http://ns.taverna.org.uk/2010/xml/server/rest/")
@Order(elements={"configuration", "properties"})
public class ListenerDescription extends VersionedElement {

	@Element
	protected Location configuration;
	
	@Element
    protected ListenerDescription.Properties properties;
    
    @Attribute
	//@Namespace(prefix = "xlink", reference = "http://www.w3.org/1999/xlink")
    protected URI href;
    
    @Attribute
   // @Namespace(reference = "http://ns.taverna.org.uk/2010/xml/server/rest/")
    protected String name;
    
    @Attribute
    //@Namespace(reference = "http://ns.taverna.org.uk/2010/xml/server/rest/")
    protected String type;
    
    public Location getConfiguration() {
		return configuration;
	}


	public void setConfiguration(Location configuration) {
		this.configuration = configuration;
	}


	public ListenerDescription.Properties getProperties() {
		return properties;
	}


	public void setProperties(ListenerDescription.Properties properties) {
		this.properties = properties;
	}


	public URI getHref() {
		return href;
	}


	public void setHref(URI href) {
		this.href = href;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}

	@Root
	public static class Properties {

		@ElementList(inline = true)
        protected List<PropertyDescription> property;

        public List<PropertyDescription> getProperty() {
            if (property == null) {
                property = new ArrayList<PropertyDescription>();
            }
            return this.property;
        }
    }
}
