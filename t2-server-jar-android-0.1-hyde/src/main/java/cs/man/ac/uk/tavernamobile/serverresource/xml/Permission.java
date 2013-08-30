package cs.man.ac.uk.tavernamobile.serverresource.xml;

import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;

@Root(name = "permission")
public enum Permission {

    NONE("none"),
    
    READ("read"),

    UPDATE("update"),

    DESTROY("destroy");
    
    @Convert(PermissionConverter.class)
    private final String value;

    Permission(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Permission fromValue(String v) {
        for (Permission c: Permission.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
