<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
	<html>
	<head>
	</head>
	<body>
		<ol>
		<xsl:for-each select="sitelist/site/tower/equipment">
			<xsl:choose>
				<xsl:when test="@price &gt; 10">
					<li style="background-color: red">
						<xsl:value-of select="@name"/>
						<xsl:text>, </xsl:text>
						<xsl:value-of select="@brand"/>
						<xsl:text>, </xsl:text>
						<xsl:value-of select="@description"/>
						<xsl:text>, </xsl:text>
						<xsl:value-of select="@price"/>
						Site: 
						<xsl:value-of select="../@name"/>
						<xsl:text>, </xsl:text>
						<xsl:value-of select="../@latitude"/>
						<xsl:text>, </xsl:text>
						<xsl:value-of select="../@longitude"/>
						Tower: 
						<xsl:value-of select="../../@name"/>
						<xsl:text>, </xsl:text>
						<xsl:value-of select="../../@height"/>
						<xsl:text>, </xsl:text>
						<xsl:value-of select="../../@sides"/>
					</li>
				</xsl:when>
				<xsl:otherwise>
					<li>
						<xsl:value-of select="@name"/>
						<xsl:text>, </xsl:text>
						<xsl:value-of select="@brand"/>
						<xsl:text>, </xsl:text>
						<xsl:value-of select="@description"/>
						<xsl:text>, </xsl:text>
						<xsl:value-of select="@price"/>
						Site: 
						<xsl:value-of select="../@name"/>
						<xsl:text>, </xsl:text>
						<xsl:value-of select="../@latitude"/>
						<xsl:text>, </xsl:text>
						<xsl:value-of select="../@longitude"/>
						Tower: 
						<xsl:value-of select="../../@name"/>
						<xsl:text>, </xsl:text>
						<xsl:value-of select="../../@height"/>
						<xsl:text>, </xsl:text>
						<xsl:value-of select="../../@sides"/>
					</li>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:for-each>
		</ol>
	</body>
	</html>
	</xsl:template>
</xsl:stylesheet>