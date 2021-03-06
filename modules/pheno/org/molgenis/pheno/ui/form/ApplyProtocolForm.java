package org.molgenis.pheno.ui.form;

import org.molgenis.auth.MolgenisUser;
import org.molgenis.framework.ui.html.ActionInput;
import org.molgenis.framework.ui.html.Container;
import org.molgenis.framework.ui.html.DateInput;
import org.molgenis.framework.ui.html.HiddenInput;
import org.molgenis.framework.ui.html.MrefInput;
import org.molgenis.framework.ui.html.TextLineInput;

public class ApplyProtocolForm extends Container
{
	/* The serial version UID of this class. Needed for serialization. */
	private static final long serialVersionUID = 4998773712604616798L;

	public ApplyProtocolForm()
	{
		this.add(new HiddenInput("__target", ""));
		this.add(new HiddenInput("select", ""));
		this.add(new HiddenInput("__action", ""));
		this.add(new TextLineInput<String>("paName", ""));
		this.add(new DateInput("paTime", ""));
		this.add(new MrefInput<MolgenisUser>("paPerformer", MolgenisUser.class));

		this.add(new ActionInput("show"));
		((ActionInput) this.get("show")).setButtonValue("Back to List mode");
		((ActionInput) this.get("show")).setLabel("Back to List mode");
		((ActionInput) this.get("show")).setTooltip("Back to List mode");

		this.add(new ActionInput("insert"));
		((ActionInput) this.get("insert")).setButtonValue("Save");
		((ActionInput) this.get("insert")).setLabel("Save");
		((ActionInput) this.get("insert")).setTooltip("Save");

	}
}
