package cs.man.ac.uk.tavernamobile.serverresource.xml;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

@Root(name = "runInput")
public class InputDescription extends VersionedElement {
	
	@Element
	protected InputDescription.File file;
	
	@Element
    protected InputDescription.Reference reference;
	
	@Element
    protected InputDescription.Value value;
	
    @Attribute
    protected String name;

    /**
     * Gets the value of the file property.
     * 
     * @return
     *     possible object is
     *     {@link InputDescription.File }
     *     
     */
    public InputDescription.File getFile() {
        return file;
    }

    /**
     * Sets the value of the file property.
     * 
     * @param value
     *     allowed object is
     *     {@link InputDescription.File }
     *     
     */
    public void setFile(InputDescription.File value) {
        this.file = value;
    }

    /**
     * Gets the value of the reference property.
     * 
     * @return
     *     possible object is
     *     {@link InputDescription.Reference }
     *     
     */
    public InputDescription.Reference getReference() {
        return reference;
    }

    /**
     * Sets the value of the reference property.
     * 
     * @param value
     *     allowed object is
     *     {@link InputDescription.Reference }
     *     
     */
    public void setReference(InputDescription.Reference value) {
        this.reference = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link InputDescription.Value }
     *     
     */
    public InputDescription.Value getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link InputDescription.Value }
     *     
     */
    public void setValue(InputDescription.Value value) {
        this.value = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }


    @Root(name = "file")
    public static class File {

        @Text
        protected String value;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

    }

    @Root(name = "reference")
    public static class Reference {

        @Text
        protected String value;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

    }

    @Root(name = "value")
    public static class Value {

        @Text
        protected String value;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

    }

}
