<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	session.setAttribute("currentMenu", "CV");
%>
	<s:debug/>
<c:import url="../layouts/Header.jsp" />
<section id="content">
	<h1>Création d'un curriculum vitae</h1>


		<p>Choisissez les compétences à ajouter:</p>
		<div class="competenceScroll">
			<div class="competence">

<!-- Formulaire avec ul/li  -->

<%-- 	<s:form method="post" action="CreateResume"> --%>
<!-- 				<ul> -->
<%-- 					<s:iterator var="referentiel" value="arbreCompetences"> --%>
<%-- 					<s:if test="%{value.isEmpty()}"> --%>
<%-- 						<li><s:checkbox fieldValue="%{key.id}" name="form.choiceReferentiels" labelposition="left" label="%{key.name} - %{key.description}"/></li>								 --%>
<%-- 					</s:if> --%>
<%-- 					<s:else> --%>
<!-- 						<li> -->
<%-- 							<s:property value="key.name" /> --%>
<!-- 							<ul> -->
<%-- 								<s:iterator value="value"> --%>
<%-- 									<s:if test="%{value.isEmpty()}"> --%>
<%-- 										<li><s:checkbox fieldValue="%{key.id}" name="form.choiceDomaines" labelposition="left" label="%{key.codeDomaine} - %{key.description}"/></li>								 --%>
<%-- 									</s:if> --%>
<%-- 									<s:else> --%>
<!-- 										<li> -->
<%-- 											<s:property value="key.codeDomaine" /> --%>
<!-- 											<ul> -->
<%-- 												<s:iterator value="value"> --%>
<%-- 													<s:if test="%{value.isEmpty()}"> --%>
<%-- 														<li><s:checkbox fieldValue="%{key.id}" name="form.choiceCompetences" labelposition="left" label="%{key.codeCompetence} - %{key.description}"/></li>								 --%>
<%-- 													</s:if> --%>
<%-- 													<s:else> --%>
<!-- 														<li> -->
<%-- 															<s:property value="key.codeCompetence" /> --%>
<!-- 															<ul> -->
<%-- 																<s:iterator value="value"> --%>
<!-- 																	<li> -->
<%-- 																		<s:checkbox fieldValue="%{key.id}" name="form.choiceItems" labelposition="left" label="%{codeItem} - %{description}	"/> --%>
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


<%-- 		<s:submit cssClass="button" key="Créer le CV "/> --%>
<%-- 	</s:form> --%>
	
	
	
	
<!-- 	style sous forme de tableau géré par struts -->
	
	<s:form method="post" action="CreateResume">
					<s:iterator var="referentiel" value="arbreCompetences">
					<s:if test="%{value.isEmpty()}">
						<s:checkbox fieldValue="%{key.id}" name="form.choiceReferentiels" labelposition="left" label="%{key.name} - %{key.description}"/>				
					</s:if>
					<s:else>
							<p><s:property value="key.name" /></p>
								<s:iterator value="value">
									<s:if test="%{value.isEmpty()}">
										<s:checkbox fieldValue="%{key.id}" name="form.choiceDomaines" labelposition="left" label="%{key.codeDomaine} - %{key.description}"/>							
									</s:if>
									<s:else>

											<p><s:property value="key.codeDomaine" /></p>

												<s:iterator value="value">
													<s:if test="%{value.isEmpty()}">
														<s:checkbox fieldValue="%{key.id}" name="form.choiceCompetences" labelposition="left" label="%{key.codeCompetence} - %{key.description}"/>				
													</s:if>
													<s:else>
														
															<p><s:property value="key.codeCompetence" /></p>
														
																<s:iterator value="value">
																	
																	<s:checkbox fieldValue="%{key.id}" name="form.choiceItems" labelposition="left" label="%{codeItem} - %{description}	"/>
																	
																</s:iterator>
															
													</s:else>
												</s:iterator>
									</s:else>
								</s:iterator>
					</s:else>
					</s:iterator>
				

		<s:submit cssClass="button" key="Créer le CV "/>
	</s:form>
	
				</div>
		</div>
</section>



<c:import url="../layouts/Footer.jsp" />