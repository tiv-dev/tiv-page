package de.tivsource.page.admin.actions.page;

import java.util.Date;
import java.util.logging.Logger;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;

import de.tivsource.ejb3plugin.InjectEJB;
import de.tivsource.page.admin.actions.EmptyAction;
import de.tivsource.page.dao.page.PageDaoLocal;
import de.tivsource.page.entity.enumeration.Language;
import de.tivsource.page.entity.page.Page;

/**
 * 
 * @author Marc Michele
 *
 */
public class EditAction extends EmptyAction {

	/**
	 * Serial Version UID.
	 */
    private static final long serialVersionUID = 7907625857484921700L;

    /**
	 * Statischer Logger der Klasse.
	 */
	private static final Logger LOGGER = Logger.getLogger("INFO");

    @InjectEJB(name="PageDao")
    private PageDaoLocal pageDaoLocal;

    private Page page;

    private String lang;

    public Page getPage() {
        return page;
    }

	public void setPage(Page page) {
        this.page = page;
    }
	
    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Override
    @Actions({
        @Action(
        		value = "edit", 
        		results = { 
        				@Result(name = "success", type = "redirectAction", location = "index.html"),
        				@Result(name = "input",   type = "tiles", location = "pageEditForm"),
        				@Result(name = "error",   type = "tiles", location = "pageEditError")
        				}
        )
    })
    public String execute() throws Exception {
    	LOGGER.info("execute() aufgerufen.");

        String remoteUser    = ServletActionContext.getRequest().getRemoteUser();
        String remoteAddress = ServletActionContext.getRequest().getRemoteAddr();

    	if(page != null) {
    		LOGGER.info(page.getTechnical());
    		Page dbPage = pageDaoLocal.findByUuid(page.getUuid());
    		

            if(lang.contentEquals(new StringBuffer("EN"))) {
                page.getContentMap().put(Language.DE, dbPage.getContentObject(Language.DE));
                dbPage.getContentMap().get(Language.EN).setContent(page.getContent(Language.EN));
                dbPage.getContentMap().get(Language.EN).setModified(new Date());

                page.getDescriptionMap().put(Language.DE, dbPage.getDescriptionObject(Language.DE));
                dbPage.getDescriptionMap().get(Language.EN).setDescription(page.getDescription(Language.EN));
                dbPage.getDescriptionMap().get(Language.EN).setKeywords(page.getKeywords(Language.EN));
                dbPage.getDescriptionMap().get(Language.EN).setName(page.getName(Language.EN));
            } else {
                dbPage.getContentMap().get(Language.DE).setContent(page.getContent(Language.DE));
                dbPage.getContentMap().get(Language.DE).setModified(new Date());

                dbPage.getDescriptionMap().get(Language.DE).setDescription(page.getDescription(Language.DE));
                dbPage.getDescriptionMap().get(Language.DE).setKeywords(page.getKeywords(Language.DE));;
                dbPage.getDescriptionMap().get(Language.DE).setName(page.getName(Language.DE));
            }


            dbPage.setBottomNavigation(page.getBottomNavigation());
            dbPage.setBottomNavigationOrder(page.getBottomNavigationOrder());
    		dbPage.setModified(new Date());
    		dbPage.setNavigation(page.getNavigation());
    		dbPage.setNavigationOrder(page.getNavigationOrder());
    		dbPage.setSpecial(page.getSpecial());
    		dbPage.setTechnical(page.getTechnical());
    		dbPage.setTopNavigation(page.getTopNavigation());
    		dbPage.setTopNavigationOrder(page.getTopNavigationOrder());
    		dbPage.setVisible(page.getVisible());
    		dbPage.setModifiedBy(remoteUser);
    		dbPage.setIp(remoteAddress);

    		pageDaoLocal.merge(dbPage);
            return SUCCESS;
    	}
    	else {
    		return ERROR;
    	}

    }// Ende execute()


}// Ende class
