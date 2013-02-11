package cs.man.ac.uk.tavernamobile.serverresource.xml;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

public class FilesystemCreationOperation {

	@Text
	protected byte[] value;
	
	@Attribute
	protected String name;

	public byte[] getValue() {
		return value;
	}

	public void setValue(byte[] value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
