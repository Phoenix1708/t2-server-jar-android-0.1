package cs.man.ac.uk.tavernamobile.serverresource.port;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "output")
public class OutputPort extends Port {

	@Element
	protected LeafValue value;
	
	@Element
    protected ListValue list;
	
	@Element
    protected ErrorValue error;
	
	@Element
    protected AbsentValue absent;

	public LeafValue getValue() {
		return value;
	}

	public void setValue(LeafValue value) {
		this.value = value;
	}

	public ListValue getList() {
		return list;
	}

	public void setList(ListValue list) {
		this.list = list;
	}

	public ErrorValue getError() {
		return error;
	}

	public void setError(ErrorValue error) {
		this.error = error;
	}

	public AbsentValue getAbsent() {
		return absent;
	}

	public void setAbsent(AbsentValue absent) {
		this.absent = absent;
	}
}
