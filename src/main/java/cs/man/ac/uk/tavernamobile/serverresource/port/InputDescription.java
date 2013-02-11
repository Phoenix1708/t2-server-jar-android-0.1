package cs.man.ac.uk.tavernamobile.serverresource.port;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(name = "inputDescription")
@Namespace(reference = "http://ns.taverna.org.uk/2010/port/")
public class InputDescription extends PortDescription {
	
	@ElementList(inline = true, required = false)
	protected List<InputPort> input;

	public List<InputPort> getInput() {
		return input;
	}

	public void setInput(List<InputPort> input) {
		this.input = input;
	}
}
