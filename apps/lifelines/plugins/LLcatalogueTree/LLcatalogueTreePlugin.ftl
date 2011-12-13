<#macro plugins_LLcatalogueTree_LLcatalogueTreePlugin screen>
<!-- normally you make one big form for the whole plugin-->
<form method="post" enctype="multipart/form-data" id="plugins_LLcatalogueTree_LLcatalogueTreePlugin" name="${screen.name}" action="">
	<!--needed in every form: to redirect the request to the right screen-->
	<input type="hidden" name="__target" value="${screen.name}">
	<!--needed in every form: to define the action. This can be set by the submit button-->
	<input type="hidden" name="__action" id="test" value="">
	<!-- hidden input for measurementId -->
	<input type="hidden" name="measurementId" id="measureId" value="">
	
<!-- this shows a title and border -->
	<div class="formscreen">
		<div class="form_header" id="${screen.getName()}">
			${screen.label}
			${screen.getName()}
		</div>
			
		<#--optional: mechanism to show messages-->
		<#list screen.getMessages() as message>
			<#if message.success>
		<p class="successmessage">${message.text}</p>
			<#else>
		<p class="errormessage">${message.text}</p>
			</#if>
		</#list>
		<div class="screenbody">
			<div class="screenpadding">	
				<div id="leftSide" style="width:200px;">
					${screen.getTreeView()}
				</div>
				<!--div id="CatalogueRightSide">
					right side	
				</div--><br/>
				<div id="ShoppingCartLabel">Shopping cart</div>
				<div class="ShoppingCartContents">
					<ul>
						<#list screen.getShoppingCart() as measurement>
							<li>${measurement.name}
							<input type="submit" value="Delete" onclick="__action.value='DeleteMeasurement&measurementName=${measurement.name}';return true;"/><br /><br />
							</li> 
						</#list>
					</ul>
					<div id="ShoopingCartButton">
						<input type="submit" name="orderMeasurementsSubmit" value="Next" onclick="__action.value='OrderMeasurements';return true;"/><br /><br />
					</div> 
				</div>
			</div>
			
			<input type="hidden" id="testInput" value="">
			
			<label> 	<#if screen.getStatus()?exists>${screen.getStatus()} </#if>  </label>	
			
		</div>
	</div>
</form>

</#macro>
