package cs.man.ac.uk.tavernamobile.serverresource.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class KeyPairCredential extends CredentialDescriptor
{
    @Element(required = true)
    protected String credentialName;
    
    @Element
    protected String credentialFile;
    
    @Element
    protected String fileType;
    
    @Element
    protected String unlockPassword;
    
    @Element
    protected byte[] credentialBytes;

    public String getCredentialName() {
        return credentialName;
    }

    public void setCredentialName(String value) {
        this.credentialName = value;
    }

    public String getCredentialFile() {
        return credentialFile;
    }

    public void setCredentialFile(String value) {
        this.credentialFile = value;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String value) {
        this.fileType = value;
    }

    public String getUnlockPassword() {
        return unlockPassword;
    }

    public void setUnlockPassword(String value) {
        this.unlockPassword = value;
    }

    public byte[] getCredentialBytes() {
        return credentialBytes;
    }

    public void setCredentialBytes(byte[] value) {
        this.credentialBytes = ((byte[]) value);
    }
}
