package cs.man.ac.uk.tavernamobile.serverresource.port;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(name = "list")
@Namespace(reference = "http://ns.taverna.org.uk/2010/port/", prefix = "port")
public class ListValue extends Value {
	
	@ElementList(required = false)
	@ElementListUnion({
	      @ElementList(entry = "list", inline = true, type = ListValue.class, required = false),
	      @ElementList(entry = "error", inline = true, type = ErrorValue.class, required = false),
	      @ElementList(entry = "value", inline = true, type = LeafValue.class, required = false),
	      @ElementList(entry = "absent", inline = true, type = AbsentValue.class, required = false)
	   })
	@Namespace(reference = "http://ns.taverna.org.uk/2010/port/", prefix = "port")
    protected List<Value> valueOrListOrError;
	
    @Attribute
    @Namespace(prefix = "port", reference = "http://ns.taverna.org.uk/2010/port/")
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
