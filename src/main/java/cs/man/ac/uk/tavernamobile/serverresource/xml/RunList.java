package cs.man.ac.uk.tavernamobile.serverresource.xml;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "runList")
public class RunList {
	
	@ElementList(required = false, inline=true)
	protected List<TavernaRun> run;

    public List<TavernaRun> getRun() {
        if (run == null) {
            run = new ArrayList<TavernaRun>();
        }
        return this.run;
    }
}
