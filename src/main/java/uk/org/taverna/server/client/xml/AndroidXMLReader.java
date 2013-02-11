package uk.org.taverna.server.client.xml;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import uk.org.taverna.server.client.InputPort;
import uk.org.taverna.server.client.OutputPort;
import uk.org.taverna.server.client.Port;
import uk.org.taverna.server.client.PortValue;
import uk.org.taverna.server.client.Run;
import uk.org.taverna.server.client.connection.UserCredentials;
import uk.org.taverna.server.client.xml.ServerResources;
import cs.man.ac.uk.tavernamobile.serverresource.port.ErrorValue;
import cs.man.ac.uk.tavernamobile.serverresource.port.InputDescription;
import cs.man.ac.uk.tavernamobile.serverresource.port.LeafValue;
import cs.man.ac.uk.tavernamobile.serverresource.port.ListValue;
import cs.man.ac.uk.tavernamobile.serverresource.port.OutputDescription;
import cs.man.ac.uk.tavernamobile.serverresource.port.Value;
import cs.man.ac.uk.tavernamobile.serverresource.xml.ListenerDescription;
import cs.man.ac.uk.tavernamobile.serverresource.xml.Location;
import cs.man.ac.uk.tavernamobile.serverresource.xml.PolicyDescription;
import cs.man.ac.uk.tavernamobile.serverresource.xml.PropertyDescription;
import cs.man.ac.uk.tavernamobile.serverresource.xml.RunDescription;
import cs.man.ac.uk.tavernamobile.serverresource.xml.SecurityDescriptor;
import cs.man.ac.uk.tavernamobile.serverresource.xml.ServerDescription;
import cs.man.ac.uk.tavernamobile.serverresource.xml.TavernaRunInputs;

import uk.org.taverna.server.client.connection.Connection;
import uk.org.taverna.server.client.connection.MimeType;
import uk.org.taverna.server.client.util.URIUtils;
import uk.org.taverna.server.client.xml.ResourceLabel;
import uk.org.taverna.server.client.xml.RunResources;

public class AndroidXMLReader {

	private final Connection connection;

	public AndroidXMLReader(Connection connection) {
		this.connection = connection;
	}

	public <T> Object read(URI uri, Class<T> classType, UserCredentials credentials) {

		Object resources = null;
		InputStream is = connection.readStream(uri, MimeType.XML, credentials);
		
		/***** for debug only *****/
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    // Fake code simulating the copy
	    // You can generally do better with nio if you need...
	    // And please, unlike me, do something about the Exceptions :D
	    byte[] buffer = new byte[1024];
	    int len;
	    try {
			while ((len = is.read(buffer)) > -1 ) {
			    baos.write(buffer, 0, len);
			}
			baos.flush();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} 

	    // Open new InputStreams using the recorded bytes
	    // Can be repeated as many times as you wish
	    InputStream is1 = new ByteArrayInputStream(baos.toByteArray());
	    InputStream is2 = new ByteArrayInputStream(baos.toByteArray());
		
		String myString = null;		
		try {
			myString = IOUtils.toString(is2, "UTF-8");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*****end of for debug only *****/
		
		try {
			Serializer serializer = new Persister(new CustomMatcher()); 
			resources = serializer.read(classType, is1, false);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(is);
		}

		return resources;
	}

	public <T> Object read(URI uri, Class<T> classType) {
		return read(uri, classType, null);
	}

	public ServerResources readServerResources(URI uri) {
		Map<ResourceLabel, URI> links = new HashMap<ResourceLabel, URI>();

		// Read server top-level description.
		ServerDescription sd = (ServerDescription) read(uri, ServerDescription.class);
		String version = sd.getServerVersion();
		String revision = sd.getServerRevision();
		String timestamp = sd.getServerBuildTimestamp();
		links.put(ResourceLabel.RUNS, sd.getRuns().getHref());
		links.put(ResourceLabel.POLICY, sd.getPolicy().getHref());
		links.put(ResourceLabel.FEED, sd.getFeed().getHref());

		// Read policy description and add links to server's set.
		PolicyDescription pd = (PolicyDescription) read(links.get(ResourceLabel.POLICY), PolicyDescription.class);
		links.put(ResourceLabel.RUNLIMIT, pd.getRunLimit().getHref());
		links.put(ResourceLabel.PERMITTED_WORKFLOWS, pd.getPermittedWorkflows()
				.getHref());
		links.put(ResourceLabel.PERMITTED_LISTENERS, pd
				.getPermittedListenerTypes().getHref());
		links.put(ResourceLabel.ENABLED_NOTIFICATIONS, pd
				.getEnabledNotificationFabrics().getHref());

		return new ServerResources(links, version, revision, timestamp);
	}

	public Map<String, InputPort> readInputPortDescription(Run run, URI uri, UserCredentials credentials) {

		InputDescription id = (InputDescription) read(uri, InputDescription.class, credentials);

		Map<String, InputPort> ports = new HashMap<String, InputPort>();
		for (cs.man.ac.uk.tavernamobile.serverresource.port.InputPort ip : id.getInput()) {
			InputPort port = Port.newInputPort(run, ip.getName(), ip.getDepth());
			ports.put(port.getName(), port);
		}

		return ports;
	}

	public Map<String, OutputPort> readOutputPortDescription(Run run, URI uri,
			UserCredentials credentials) {

		OutputDescription od = (OutputDescription) read(uri, OutputDescription.class, credentials);

		Map<String, OutputPort> ports = new HashMap<String, OutputPort>();
		for (cs.man.ac.uk.tavernamobile.serverresource.port.OutputPort op : od.getOutput()) {

			LeafValue v = op.getValue();
			ListValue lv = op.getList();
			ErrorValue ev = op.getError();

			PortValue value = null;
			if (v != null) {
				value = PortValue.newPortData(run, v.getHref(),
						v.getContentType(), v.getContentByteLength());
			} else if (lv != null) {
				value = parseOutputPortValueStructure(run, lv);
			} else if (ev != null) {
				value = PortValue.newPortError(run, ev.getHref(),
						ev.getErrorByteLength());
			}

			OutputPort port = Port.newOutputPort(run, op.getName(),
					op.getDepth(), value);
			ports.put(port.getName(), port);
		}

		return ports;
	}

	/* This method has to parse the OutputPort structure by trying to cast to
	 * each type that a port can be. Not pretty.
	 * 
	 * Even though we know that first time through this method we must have a
	 * list we try to cast to a value first as this is what we will most often
	 * have.*/

	private PortValue parseOutputPortValueStructure(Run run, Value value) {
		if (LeafValue.class.isInstance(value)) {
			LeafValue lv = (LeafValue) value;

			return PortValue.newPortData(run, lv.getHref(),
					lv.getContentType(),
					lv.getContentByteLength());
		} else if (ListValue.class.isInstance(value)) {
			ListValue lv = (ListValue) value;

			List<PortValue> list = new ArrayList<PortValue>();
			for (Value v : lv.getValueOrListOrError()) {
				list.add(parseOutputPortValueStructure(run, v));
			}

			return PortValue.newPortList(run, lv.getHref(), list);
		} else if (ErrorValue.class.isInstance(value)) {
			ErrorValue ev = (ErrorValue) value;

			return PortValue.newPortError(run, ev.getHref(),
					ev.getErrorByteLength());
		}

		// We should NOT get here!
		return null;
	}
	
	public RunResources readRunResources(URI uri, UserCredentials credentials) {
		Map<ResourceLabel, URI> links = new HashMap<ResourceLabel, URI>();

		// Read run top-level description.
		RunDescription rd = (RunDescription) read(uri, RunDescription.class, credentials);
		String owner = rd.getOwner();
		links.put(ResourceLabel.WORKFLOW, rd.getCreationWorkflow().getHref());
		links.put(ResourceLabel.CREATE_TIME, rd.getCreateTime().getHref());
		links.put(ResourceLabel.START_TIME, rd.getStartTime().getHref());
		links.put(ResourceLabel.FINISH_TIME, rd.getFinishTime().getHref());
		links.put(ResourceLabel.STATUS, rd.getStatus().getHref());
		links.put(ResourceLabel.INPUT, rd.getInputs().getHref());
		links.put(ResourceLabel.OUTPUT, rd.getOutput().getHref());
		links.put(ResourceLabel.WDIR, rd.getWorkingDirectory().getHref());
		links.put(ResourceLabel.EXPIRY, rd.getExpiry().getHref());
		links.put(ResourceLabel.SECURITY_CTX, rd.getSecurityContext().getHref());

		// Read the inputs description.
		TavernaRunInputs tri = (TavernaRunInputs) read(links.get(ResourceLabel.INPUT), TavernaRunInputs.class, credentials);
		links.put(ResourceLabel.BACLAVA, tri.getBaclava().getHref());
		links.put(ResourceLabel.EXPECTED_INPUTS, tri.getExpected().getHref());

		// Read the special IO listeners - this is kind of hard-coded for now.
		for (Location loc : rd.getListeners().getListener()) {
			URI u = loc.getHref();
			if (URIUtils.extractFinalPathComponent(u).equalsIgnoreCase("io")) {
				ListenerDescription ld = (ListenerDescription) read(u, ListenerDescription.class, credentials);

				for (PropertyDescription pd : ld.getProperties().getProperty()) {
					if (pd.getName().equalsIgnoreCase("stdout")) {
						links.put(ResourceLabel.STDOUT, pd.getHref());
					} else if (pd.getName().equalsIgnoreCase("stderr")) {
						links.put(ResourceLabel.STDERR, pd.getHref());
					} else if (pd.getName().equalsIgnoreCase("exitcode")) {
						links.put(ResourceLabel.EXITCODE, pd.getHref());
					}
				}
			}
		}

		// Read the security context iff we are the owner of the run
		if (credentials.getUsername().equals(owner)) {
			SecurityDescriptor sd = (SecurityDescriptor) read(
					links.get(ResourceLabel.SECURITY_CTX), 
					SecurityDescriptor.class,
					credentials);

			links.put(ResourceLabel.PERMISSIONS, sd.getPermissions().getHref());
			links.put(ResourceLabel.CREDENTIALS, sd.getCredentials().getHref());
			links.put(ResourceLabel.TRUSTS, sd.getTrusts().getHref());
		}

		return new RunResources(links, owner);
	}

	/*public Map<String, URI> readRunList(URI uri, UserCredentials credentials) {
		RunList runList = (RunList) read(uri, credentials);
		List<TavernaRun> trs = runList.getRun();

		Map<String, URI> runs = new HashMap<String, URI>(trs.size());

		for (TavernaRun tr : trs) {
			runs.put(tr.getValue(), tr.getHref());
		}

		return runs;
	}

	public Map<String, RunPermission> readRunPermissions(URI uri,
			UserCredentials credentials) {
		JAXBElement<?> root = (JAXBElement<?>) read(uri, credentials);
		PermissionsDescription pd = (PermissionsDescription) root.getValue();

		Map<String, RunPermission> perms = new HashMap<String, RunPermission>();
		for (LinkedPermissionDescription lpd : pd.getPermission()) {
			Permission perm = lpd.getPermission();
			perms.put(lpd.getUserName(), RunPermission.fromString(perm.value()));
		}

		return perms;
	}

	public Map<URI, URI> readRunServiceCredentials(URI uri,
			UserCredentials credentials) {
		JAXBElement<?> root = (JAXBElement<?>) read(uri, credentials);
		CredentialList cl = (CredentialList) root.getValue();

		Map<URI, URI> creds = new HashMap<URI, URI>();
		for (Credential cred : cl.getCredential()) {
			CredentialDescriptor cd = cred.getUserpass();
			if (cd == null) {
				cd = cred.getKeypair();
			}

			creds.put(cd.getServiceURI(), cd.getHref());
		}

		return creds;
	}

	public List<URI> readRunTrustedIdentities(URI uri, UserCredentials credentials) {
		JAXBElement<?> root = (JAXBElement<?>) read(uri, credentials);
		TrustList tl = (TrustList) root.getValue();

		List<URI> trusts = new ArrayList<URI>();
		for (TrustDescriptor td : tl.getTrust()) {
			trusts.add(td.getHref());
		}

		return trusts;
	}*/
}
