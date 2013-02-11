package cs.man.ac.uk.tavernamobile.serverresource.port;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "inputDescription")
public class InputDescription extends PortDescription {
	
	@ElementList(inline = true)
	protected List<InputPort> input;

	public List<InputPort> getInput() {
		return input;
	}

	public void setInput(List<InputPort> input) {
		this.input = input;
	}
}
