package cs.man.ac.uk.tavernamobile.serverresource.port;

import java.net.URI;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Namespace;

public abstract class PortDescription {
	
	@Attribute(required = false)
	@Namespace(reference = "http://ns.taverna.org.uk/2010/port/", prefix = "port")
    protected String workflowId;
	
    @Attribute(required = false)
    @Namespace(reference = "http://ns.taverna.org.uk/2010/port/", prefix = "port")
    protected URI workflowRun;
    
    @Attribute(required = false)
    @Namespace(reference = "http://ns.taverna.org.uk/2010/port/", prefix = "port")
    protected String workflowRunId;

	public String getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}

	public URI getWorkflowRun() {
		return workflowRun;
	}

	public void setWorkflowRun(URI workflowRun) {
		this.workflowRun = workflowRun;
	}

	public String getWorkflowRunId() {
		return workflowRunId;
	}

	public void setWorkflowRunId(String workflowRunId) {
		this.workflowRunId = workflowRunId;
	}
}
