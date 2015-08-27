<#assign title = resourceBundle(title)/>
<#assign greeting = resourceBundle(greeting)/>
<html>
	<head>
		<title>${title}</title>
	</head>
	<body>
		<h1>${greeting}</h1>
		<ol>
		<#list properties as property>			
			<li value="${property.propertyId}">${property.propertyPrice}</li>
			<#assign type = resourceBundle(property.propertyName)/>
			<p>${type}</p>
		</#list>
		</ol>
	</body>
</html>