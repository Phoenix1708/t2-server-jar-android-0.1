package cs.man.ac.uk.tavernamobile.serverresource.xml;

import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

public class PermissionConverter implements Converter<Permission>
{
	@Override
	public Permission read(InputNode node) throws Exception
	{
		final String value = node.getValue();

		// Decide what enum it is by its value
		for( Permission ts : Permission.values() )
		{
			if( ts.value().equalsIgnoreCase(value) )
				return ts;
		}
		throw new IllegalArgumentException("No enum available for " + value);
	}

	@Override
	public void write(OutputNode node, Permission perm) throws Exception
	{
		if (node == null){
			throw new IllegalArgumentException("output node is null");
		}
		node.setValue(perm.value());
	}
}
