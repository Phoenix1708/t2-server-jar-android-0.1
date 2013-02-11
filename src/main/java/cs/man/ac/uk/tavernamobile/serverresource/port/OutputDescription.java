package cs.man.ac.uk.tavernamobile.serverresource.port;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "workflowOutputs")
public class OutputDescription extends PortDescription {
	
	@ElementList(inline = true)
	protected List<OutputPort> output;

	public List<OutputPort> getOutput() {
		return output;
	}

	public void setOutput(List<OutputPort> output) {
		this.output = output;
	}
}
