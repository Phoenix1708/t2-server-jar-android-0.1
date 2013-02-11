package cs.man.ac.uk.tavernamobile.serverresource.port;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.Root;

@Root(name = "list")
public class ListValue extends Value {
	
	@ElementList
	@ElementListUnion({
	      @ElementList(entry = "list", inline = true, type = ListValue.class),
	      @ElementList(entry = "error", inline = true, type = ErrorValue.class),
	      @ElementList(entry = "value", inline = true, type = LeafValue.class),
	      @ElementList(entry = "absent", inline = true, type = AbsentValue.class)
	   })
    protected List<Value> valueOrListOrError;
	
    @Attribute
    //@Namespace(prefix = "port", reference = "http://ns.taverna.org.uk/2010/port/")
    protected Integer length;

	public List<Value> getValueOrListOrError() {
		return valueOrListOrError;
	}

	public void setValueOrListOrError(List<Value> valueOrListOrError) {
		this.valueOrListOrError = valueOrListOrError;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

}
