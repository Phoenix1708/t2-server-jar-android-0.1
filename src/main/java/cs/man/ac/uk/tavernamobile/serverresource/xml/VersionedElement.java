package cs.man.ac.uk.tavernamobile.serverresource.xml;

import org.simpleframework.xml.Attribute;

public abstract class VersionedElement {
	
	@Attribute
	//@Namespace(prefix = "ts", reference = "http://ns.taverna.org.uk/2010/xml/server/")
    protected String serverVersion;
	
	@Attribute
	//@Namespace(prefix = "ts", reference = "http://ns.taverna.org.uk/2010/xml/server/")
    protected String serverRevision;
	
    @Attribute
    //@Namespace(prefix = "ts", reference = "http://ns.taverna.org.uk/2010/xml/server/")
    protected String serverBuildTimestamp;

    public String getServerVersion() {
		return serverVersion;
	}
	public void setServerVersion(String serverVersion) {
		this.serverVersion = serverVersion;
	}
	public String getServerRevision() {
		return serverRevision;
	}
	public void setServerRevision(String serverRevision) {
		this.serverRevision = serverRevision;
	}
	public String getServerBuildTimestamp() {
		return serverBuildTimestamp;
	}
	public void setServerBuildTimestamp(String serverBuildTimestamp) {
		this.serverBuildTimestamp = serverBuildTimestamp;
	}
}
