package cs.man.ac.uk.tavernamobile.serverresource.xml;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Root;

@Root(name = "runInputs")
//@Namespace(reference = "http://ns.taverna.org.uk/2010/xml/server/rest/")
@Order(elements={"expected", "baclava", "input"})
public class TavernaRunInputs extends VersionedElement {

	@Element
	protected Location expected;

	@Element
	protected Location baclava;

	@ElementList(inline = true, entry = "input")
	protected List<Location> input;

	public Location getExpected() {
		return expected;
	}

	public void setExpected(Location expected) {
		this.expected = expected;
	}

	public Location getBaclava() {
		return baclava;
	}

	public void setBaclava(Location baclava) {
		this.baclava = baclava;
	}

	public List<Location> getInput() {
		if (input == null) {
			input = new ArrayList<Location>();
		}
		return this.input;
	}
}
