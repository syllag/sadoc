<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	session.setAttribute("currentMenu", "CV");
%>
<c:import url="../layouts/Header.jsp" />

<script>
	$(function() {
		$( "#accordion" ).accordion({
		collapsible: true
		});
		$("#accordion h3 input").click(function(e) { 
		    e.stopPropagation(); 
		}); 
	});
	
	
</script>

<section id="contentResume">
	<h1>Création d'un curriculum vitae</h1>


		<p>Choisissez les compétences à ajouter:</p>
		<div class="noCompetenceScroll">
			<div class="resume">



<!-- Formulaire avec ul/li  -->

<s:form method="post" action="CreateResume" theme="simple">
		<div id="accordion">
					<s:iterator var="referentiel" value="arbreCompetences">
					<s:if test="%{value.isEmpty()}">
						<h3 class="noClick">
							<s:property value="key.name"/> - <s:property value="key.description"/> <s:checkbox fieldValue="%{key.id}" name="form.choiceReferentiels"/>
						</h3>								
					</s:if>
					<s:else>
						<h3>
							<s:property value="key.name" />
						</h3>
						<div>
							<ul class="listDomaine">
								<s:iterator value="value">
									<s:if test="%{value.isEmpty()}">
										<li>
											<span class="ui-icon ui-icon-bullet" style="display:inline-block"></span>
											<s:property value="key.codeDomaine"/> - <s:property value="key.description"/> <s:checkbox fieldValue="%{key.id}" name="form.choiceDomaines"/>
										</li>								
									</s:if>
									<s:else>
										<li>
											<span class="ui-icon ui-icon-bullet" style="display:inline-block"></span>
											<s:property value="key.codeDomaine" />
											<ul class="listCompetence">
												<s:iterator value="value">
													<s:if test="%{value.isEmpty()}">
														<li>
															<span class="ui-icon ui-icon-radio-on" style="display:inline-block"></span>
															<s:property value="key.codeCompetence"/> - <s:property value="key.description"/> <s:checkbox fieldValue="%{key.id}" name="form.choiceCompetences" /></li>								
													</s:if>
													<s:else>
														<li>
															<span class="ui-icon ui-icon-radio-on" style="display:inline-block"></span>
															<s:property value="key.codeCompetence" />
															<ul class="listItem">
																<s:iterator value="value">
																	<li>
																		 <span class="ui-icon ui-icon-radio-off" style="display:inline-block"></span><s:property value="codeItem"/> - <s:property value="description"/> <s:checkbox fieldValue="%{key.id}" name="form.choiceItems"/>
																	</li>
																</s:iterator>
															</ul>
														</li>
													</s:else>
												</s:iterator>
											</ul>
										</li>	
									</s:else>
								</s:iterator>
							</ul>
						</div>
					</s:else>
					</s:iterator>
		</div>
		
		<s:submit cssClass="button" key="Créer le CV "/>
		
	</s:form>

<%-- 	<s:form method="post" action="CreateResume" theme="simple"> --%>
<!-- 		<div id="accordion"> -->
<!-- 				<ul> -->
<%-- 					<s:iterator var="referentiel" value="arbreCompetences"> --%>
<%-- 					<s:if test="%{value.isEmpty()}"> --%>
<!-- 						<li> -->
<%-- 							<s:property value="key.name"/> - <s:property value="key.description"/> <s:checkbox fieldValue="%{key.id}" name="form.choiceReferentiels"/> --%>
<!-- 						</li>								 -->
<%-- 					</s:if> --%>
<%-- 					<s:else> --%>
<!-- 						<li> -->
<%-- 							<s:property value="key.name" /> --%>
<!-- 							<ul> -->
<%-- 								<s:iterator value="value"> --%>
<%-- 									<s:if test="%{value.isEmpty()}"> --%>
<%-- 										<li><s:property value="key.codeDomaine"/> - <s:property value="key.description"/> <s:checkbox fieldValue="%{key.id}" name="form.choiceDomaines"/></li>								 --%>
<%-- 									</s:if> --%>
<%-- 									<s:else> --%>
<!-- 										<li> -->
<%-- 											<s:property value="key.codeDomaine" /> --%>
<!-- 											<ul> -->
<%-- 												<s:iterator value="value"> --%>
<%-- 													<s:if test="%{value.isEmpty()}"> --%>
<%-- 														<li><s:property value="key.codeCompetence"/> - <s:property value="key.description"/> <s:checkbox fieldValue="%{key.id}" name="form.choiceCompetences" /></li>								 --%>
<%-- 													</s:if> --%>
<%-- 													<s:else> --%>
<!-- 														<li> -->
<%-- 															<s:property value="key.codeCompetence" /> --%>
<!-- 															<ul> -->
<%-- 																<s:iterator value="value"> --%>
<!-- 																	<li> -->
<%-- 																		<s:property value="codeItem"/> - <s:property value="description"/> <s:checkbox fieldValue="%{key.id}" name="form.choiceItems"/> --%>
<!-- 																	</li> -->
<%-- 																</s:iterator> --%>
<!-- 															</ul> -->
<!-- 														</li> -->
<%-- 													</s:else> --%>
<%-- 												</s:iterator> --%>
<!-- 											</ul> -->
<!-- 										</li>	 -->
<%-- 									</s:else> --%>
<%-- 								</s:iterator> --%>
<!-- 							</ul> -->
<!-- 						</li> -->
<%-- 					</s:else> --%>
<%-- 					</s:iterator> --%>
<!-- 				</ul> -->

<!-- 		</div> -->
		
<%-- 		<s:submit cssClass="button" key="Créer le CV "/> --%>
		
<%-- 	</s:form> --%>
	
	
	
	
<!-- 	style sous forme de tableau géré par struts -->
	
<%-- 	<s:form method="post" action="CreateResume"> --%>
<%-- 					<s:iterator var="referentiel" value="arbreCompetences"> --%>
<%-- 					<s:if test="%{value.isEmpty()}"> --%>
<%-- 						<s:checkbox fieldValue="%{key.id}" name="form.choiceReferentiels" labelposition="left" label="%{key.name} - %{key.description}"/>				 --%>
<%-- 					</s:if> --%>
<%-- 					<s:else> --%>
<%-- 							<p><s:property value="key.name" /></p> --%>
<%-- 								<s:iterator value="value"> --%>
<%-- 									<s:if test="%{value.isEmpty()}"> --%>
<%-- 										<s:checkbox fieldValue="%{key.id}" name="form.choiceDomaines" labelposition="left" label="%{key.codeDomaine} - %{key.description}"/>							 --%>
<%-- 									</s:if> --%>
<%-- 									<s:else> --%>

<%-- 											<p><s:property value="key.codeDomaine" /></p> --%>

<%-- 												<s:iterator value="value"> --%>
<%-- 													<s:if test="%{value.isEmpty()}"> --%>
<%-- 														<s:checkbox fieldValue="%{key.id}" name="form.choiceCompetences" labelposition="left" label="%{key.codeCompetence} - %{key.description}"/>				 --%>
<%-- 													</s:if> --%>
<%-- 													<s:else> --%>
														
<%-- 															<p><s:property value="key.codeCompetence" /></p> --%>
														
<%-- 																<s:iterator value="value"> --%>
																	
<%-- 																	<s:checkbox fieldValue="%{key.id}" name="form.choiceItems" labelposition="left" label="%{codeItem} - %{description}	"/> --%>
																	
<%-- 																</s:iterator> --%>
															
<%-- 													</s:else> --%>
<%-- 												</s:iterator> --%>
<%-- 									</s:else> --%>
<%-- 								</s:iterator> --%>
<%-- 					</s:else> --%>
<%-- 					</s:iterator> --%>
				

<%-- 		<s:submit cssClass="button" key="Créer le CV "/> --%>
<%-- 	</s:form> --%>
	
				</div>
		</div>
</section>



<c:import url="../layouts/Footer.jsp" />