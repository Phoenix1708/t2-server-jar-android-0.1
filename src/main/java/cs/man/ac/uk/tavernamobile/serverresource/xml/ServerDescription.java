package cs.man.ac.uk.tavernamobile.serverresource.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Root;

@Root(name = "serverDescription")
//@Namespace(reference = "http://ns.taverna.org.uk/2010/xml/server/rest/")
@Order(elements={"runs", "policy", "feed"})
public class ServerDescription extends VersionedElement {
	
	@Element
	protected Location runs;
	
	@Element
    protected Location policy;
	
	@Element
    protected Location feed;
    
	public Location getRuns() {
		return runs;
	}
	public void setRuns(Location runs) {
		this.runs = runs;
	}
	public Location getPolicy() {
		return policy;
	}
	public void setPolicy(Location policy) {
		this.policy = policy;
	}
	public Location getFeed() {
		return feed;
	}
	public void setFeed(Location feed) {
		this.feed = feed;
	}    
}
