package cs.man.ac.uk.tavernamobile.serverresource.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Root;

@Root(name = "policyDescription")
//@Namespace(reference = "http://ns.taverna.org.uk/2010/xml/server/rest/")
@Order(elements={"runLimit", "permittedWorkflows", "permittedListenerTypes", "enabledNotificationFabrics"})
public class PolicyDescription extends VersionedElement {

	@Element
	protected Location runLimit;
	
	@Element
    protected Location permittedWorkflows;
	
	@Element
    protected Location permittedListenerTypes;
	
	@Element
    protected Location enabledNotificationFabrics;
	
	public Location getRunLimit() {
		return runLimit;
	}
	public void setRunLimit(Location runLimit) {
		this.runLimit = runLimit;
	}
	public Location getPermittedWorkflows() {
		return permittedWorkflows;
	}
	public void setPermittedWorkflows(Location permittedWorkflows) {
		this.permittedWorkflows = permittedWorkflows;
	}
	public Location getPermittedListenerTypes() {
		return permittedListenerTypes;
	}
	public void setPermittedListenerTypes(Location permittedListenerTypes) {
		this.permittedListenerTypes = permittedListenerTypes;
	}
	public Location getEnabledNotificationFabrics() {
		return enabledNotificationFabrics;
	}
	public void setEnabledNotificationFabrics(Location enabledNotificationFabrics) {
		this.enabledNotificationFabrics = enabledNotificationFabrics;
	}
}
