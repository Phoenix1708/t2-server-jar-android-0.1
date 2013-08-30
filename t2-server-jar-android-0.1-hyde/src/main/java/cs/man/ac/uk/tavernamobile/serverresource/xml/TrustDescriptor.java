package cs.man.ac.uk.tavernamobile.serverresource.xml;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "trust")
public class TrustDescriptor {

	@Element(required = false)
    protected String certificateFile;
	
	@Element(required = false)
    protected String fileType;
	
	@Element(required = false)
    protected byte[] certificateBytes;
	
	@ElementList(required = false)
    protected List<String> serverName;
    
    @Attribute(required = false)
    protected URI href;

    public String getCertificateFile() {
        return certificateFile;
    }

    public void setCertificateFile(String value) {
        this.certificateFile = value;
    }
    
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String value) {
        this.fileType = value;
    }
    
    public byte[] getCertificateBytes() {
        return certificateBytes;
    }

    public void setCertificateBytes(byte[] value) {
        this.certificateBytes = ((byte[]) value);
    }

    public List<String> getServerName() {
        if (serverName == null) {
            serverName = new ArrayList<String>();
        }
        return this.serverName;
    }

    public URI getHref() {
        return href;
    }

    public void setHref(URI value) {
        this.href = value;
    }
}
