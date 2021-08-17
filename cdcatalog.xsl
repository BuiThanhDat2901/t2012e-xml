<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/">
        <html>
            <head>
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>
            </head>
            <body>
                <h1>List CD</h1>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th>title</th>
                        <th>artist</th>
                        <th>country</th>
                        <th>company</th>
                        <th>price</th>
                        <th>year</th>
                    </tr>
                    <xsl:for-each select="catalog/cd">
                        <xsl:apply-templates select="."/>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="cd">
        <tr>
            <td>
                <xsl:value-of select="title"/>
            </td>
            <td>
                <xsl:value-of select="artist"/>
            </td>
            <td>
                <xsl:value-of select="country"/>
            </td>
            <td>
                <xsl:value-of select="company"/>
            </td>
            <td>
                <xsl:apply-templates select="price"/>
            </td>
            <td>
                <xsl:apply-templates select="year"/>
            </td>

        </tr>
    </xsl:template>
    <xsl:template match="price">
        <xsl:value-of select="."/>
        <xsl:if test=". &lt; 9">
            <xsl:text> </xsl:text>
            <i class="fas fa-arrow-down" style="color:red"/>
        </xsl:if>
    </xsl:template>
    <xsl:template match="year">
        <xsl:value-of select="."/>
        <xsl:if test=". &gt;= 1995">
            <i class="fab fa-hotjar" style="color:green"/>
        </xsl:if>
    </xsl:template>
</xsl:stylesheet>