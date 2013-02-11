package cs.man.ac.uk.tavernamobile.serverresource.xml;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

@Root(name = "runDescription")
/*@NamespaceList({
@Namespace(reference="http://ns.taverna.org.uk/2010/xml/server/", prefix = "ts"),
@Namespace(reference="http://ns.taverna.org.uk/2010/xml/server/rest/", prefix = "ts-rest")
})*/
public class RunDescription extends VersionedElement {

	@Element
	protected RunDescription.Expiry expiry;

	@Element
	protected Location creationWorkflow;

	@Element
	protected Location createTime;

	@Element
	protected Location startTime;

	@Element
	protected Location finishTime;

	@Element
	protected Location status;

	@Element
	protected Location workingDirectory;

	@Element
	protected Location inputs;

	@Element
	protected Location output;

	@Element
	protected Location securityContext;

	@Element
	protected RunDescription.Listeners listeners;

	@Attribute
	protected String owner;

	public RunDescription.Expiry getExpiry() {
		return expiry;
	}

	public void setExpiry(RunDescription.Expiry expiry) {
		this.expiry = expiry;
	}

	public Location getCreationWorkflow() {
		return creationWorkflow;
	}

	public void setCreationWorkflow(Location creationWorkflow) {
		this.creationWorkflow = creationWorkflow;
	}

	public Location getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Location createTime) {
		this.createTime = createTime;
	}

	public Location getStartTime() {
		return startTime;
	}

	public void setStartTime(Location startTime) {
		this.startTime = startTime;
	}

	public Location getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Location finishTime) {
		this.finishTime = finishTime;
	}

	public Location getStatus() {
		return status;
	}

	public void setStatus(Location status) {
		this.status = status;
	}

	public Location getWorkingDirectory() {
		return workingDirectory;
	}

	public void setWorkingDirectory(Location workingDirectory) {
		this.workingDirectory = workingDirectory;
	}

	public Location getInputs() {
		return inputs;
	}

	public void setInputs(Location inputs) {
		this.inputs = inputs;
	}

	public Location getOutput() {
		return output;
	}

	public void setOutput(Location output) {
		this.output = output;
	}

	public Location getSecurityContext() {
		return securityContext;
	}

	public void setSecurityContext(Location securityContext) {
		this.securityContext = securityContext;
	}

	public RunDescription.Listeners getListeners() {
		return listeners;
	}

	public void setListeners(RunDescription.Listeners listeners) {
		this.listeners = listeners;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public static class Expiry {

		@Text
		protected String value;

		@Attribute
		//@Namespace(prefix = "xlink", reference = "http://www.w3.org/1999/xlink")
		protected URI href;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public URI getHref() {
			return href;
		}

		public void setHref(URI href) {
			this.href = href;
		}
	}

	@Root
	public static class Listeners extends Location {
		
		@ElementList(empty = true, inline = true, entry = "listener")
		protected List<Location> listener;

		public List<Location> getListener() {
			if (listener == null) {
				listener = new ArrayList<Location>();
			}
			return this.listener;
		}
	}
	
	//@Root(name = "listener")
	//public static class Listener extends Location {}
}
