package cs.man.ac.uk.tavernamobile.serverresource.port;

import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(name = "absent")
@Namespace(reference = "http://ns.taverna.org.uk/2010/port/", prefix = "port")
public class AbsentValue extends Value {

}
