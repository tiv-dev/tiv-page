<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<struts:url var="pageAddUrl" action="addForm" namespace="/page" />
<struts:url id="remoteurl" action="table" namespace="/page"/>

<script type="text/javascript">
function formatEditLink(cellvalue, options, rowObject) {
  return "<a href='/admin/page/editForm.html?page="+ cellvalue +"'>edit</a> | <a href='/admin/page/deleteForm.html?page="+ cellvalue +"'>delete</a>";
}
</script>


      <!--  Start MAIN -->
      <div class="main">
        <div class="sub_menu">
          <struts:a href="%{pageAddUrl}">Add Page</struts:a>
        </div>

        
        
        
        <sjg:grid
          id="gridedittable"
          caption="Page"
          dataType="json"
          href="%{remoteurl}"
          pager="true"
          navigator="true"
          navigatorAdd="false"
          navigatorSearch="false"
          navigatorEdit="false"
          navigatorView="false"
          navigatorDelete="false"
          gridModel="gridModel"
          rowList="5,10,15,20"
          rowNum="15"
          editinline="false"
          viewrecords="true"
        >
    	
    	  <sjg:gridColumn name="uuid"        index="uuid"        title="UUID"        width="250" editable="false" sortable="true"  hidden="false" search="false" resizable="false" align="center" />
    	  <sjg:gridColumn name="descriptionMap.DE.name" index="descriptionMap.DE.name" title="Name" width="250" editable="false" sortable="true"  hidden="false" search="false" resizable="false" align="left" />
    	  <sjg:gridColumn name="technical"   index="technical"   title="Technical"   width="250" editable="false" sortable="true"  hidden="false" search="false" resizable="false" align="center" />
    	  
    	  <sjg:gridColumn name="special"        index="special"        title="Special"        width="100" editable="false" sortable="true"  hidden="false" search="false" resizable="false" align="center" />
    	  <sjg:gridColumn name="visible"    index="visible"    title="Visible"    width="70"  editable="false" sortable="true"  hidden="false" search="false" resizable="false" align="center" />
    	  <sjg:gridColumn name="created"     index="created"     title="Created"     width="200" editable="false" sortable="true"  hidden="false" search="false" resizable="false" align="center" />
    	  <sjg:gridColumn name="uuid"        index="editbar"     title="Actions"     width="145" editable="false" sortable="false" hidden="false" search="false" resizable="false" align="right" formatter="formatEditLink" />    	
        </sjg:grid>

        <div style="width:100%; margin: 10px;">&nbsp;</div>
    
      </div>
      <!--  Ende MAIN -->