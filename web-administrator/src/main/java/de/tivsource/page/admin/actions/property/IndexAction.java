package de.tivsource.page.admin.actions.property;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;

import de.tivsource.page.admin.actions.EmptyAction;

/**
 * 
 * @author Marc Michele
 *
 */
public class IndexAction extends EmptyAction {

	/**
	 * Serial Version UID.
	 */
    private static final long serialVersionUID = -5026111146038463663L;

    /**
	 * Statischer Logger der Klasse.
	 */
    private static final Logger LOGGER = Logger.getLogger(IndexAction.class);

	@Override
    @Actions({
        @Action(
        		value = "index", 
        		results = { @Result(name = "success", type="tiles", location = "property") }
        )
    })
    public String execute() throws Exception {
    	LOGGER.info("execute() aufgerufen.");
    	return SUCCESS;
    }// Ende execute()

}// Ende class