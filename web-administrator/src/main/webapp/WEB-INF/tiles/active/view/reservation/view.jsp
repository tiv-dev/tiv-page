<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>


      <!--  Start MAIN -->
      <div class="main">
        <div class="sub_menu"></div>
        <div id="usermanager_update_header" class="update usermanager module_header">
          <h2>
            <struts:text name="reservation.view"/> 
            für das 
            <struts:text name="%{reservation.event.getName(getText('language'))}"/> 
            am 
            <struts:text name="%{getText('format.reservation.date',{reservation.event.beginning})}"/> 
            im
            <struts:text name="%{reservation.event.location.getName(getText('language'))}"/> 
          </h2>
        </div>

        <div id="backend_update_form" class="update">
          <form class="form">

            <fieldset class="fieldset">

              <div class="field">
                <label for="reservation.gender" class="label">Anrede:</label>
                <struts:if test="reservation.gender">
                  Frau
                </struts:if>
                <struts:else>
                  Herr
                </struts:else>
              </div>

              <div class="field">
                <label for="reservation.firstname" class="label">Vorname:</label>
                <struts:property value="reservation.firstname"/>
              </div>

              <div class="field">
                <label for="reservation.lastname" class="label">Nachname:</label>
                <struts:property value="reservation.lastname"/>
              </div>

              <div class="field">
                <label for="reservation.email" class="label">Mail:</label>
                <struts:property value="reservation.email"/>
              </div>

              <div class="field">
                <label for="reservation.telephone" class="label">Telefon:</label>
                <struts:property value="reservation.telephone"/>
              </div>

              <div class="field">
                <label for="reservation.quantity" class="label">Personen:</label>
                <struts:property value="reservation.quantity"/>
              </div>

              <div class="field">
                <label for="reservation.time" class="label">Uhrzeit:</label>
                <struts:property value=""/>
                <struts:date name="reservation.time" format="HH:mm" />
              </div>

              <div class="field">
                <label for="reservation.confirmed" class="label">Bestätigt:</label>
                <struts:property value="reservation.confirmed"/>
              </div>

              <div class="field">
                <label for="reservation.wishes" class="label">Wünsche:</label>
                <div style="padding-left: 200px;"><struts:property value="reservation.wishes" escape="false" /></div>
              </div>

              <div class="field">
                <label for="reservation.created" class="label">Erstellt am:</label>
                <struts:property value="reservation.created"/>
              </div>

              <div class="field">
                <label for="reservation.createdAddress" class="label">IP-Adresse:</label>
                <struts:property value="reservation.createdAddress"/>
              </div>



            </fieldset>

            <div class="buttons form_bottom">
              <struts:url var="abortReservationUrl" action="index" namespace="/reservation">
                <struts:param name="event" value="reservation.event.uuid"/>
              </struts:url>
              <struts:a 
                  id="submit_deny__Close" 
                  name="submitClose" 
                  cssClass="cancel small_red_button button" 
                  href="%{abortReservationUrl}">
                    <struts:text name="form.abort"/>
              </struts:a>
            </div>
          </form>
        </div>


      </div>
      <!--  Ende MAIN -->
