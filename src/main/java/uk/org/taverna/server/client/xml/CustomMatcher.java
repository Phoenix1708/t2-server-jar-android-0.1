package uk.org.taverna.server.client.xml;

import java.net.URI;

import org.simpleframework.xml.transform.Matcher;
import org.simpleframework.xml.transform.Transform;

public class CustomMatcher implements Matcher {

	@Override
	public Transform<URI> match(Class type) throws Exception {
		if (type.equals(URI.class))
			return new URITransform();
		return null;
	}

	public class URITransform implements Transform<URI> {
		@Override
		public URI read(String value) throws Exception {
			return new URI(value);
		}
		@Override
		public String write(URI value) throws Exception {
			return value.toString();
		}
	}
}
