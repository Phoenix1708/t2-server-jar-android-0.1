package cs.man.ac.uk.tavernamobile.serverresource.xml;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "property")
public class PropertyDescription extends Location {

	@Attribute
    protected String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
