/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.xwiki.android.rest;

import java.io.StringWriter;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.xwiki.android.resources.Spaces;

/**
 * XWiki Android REST Space related source. Can get the spaces details/list as a Space(s) objects of Simple-xml object
 * model
 */
public class SpaceResources extends HttpConnector
{
    /**
     * Path provided from XWiki RESTful API
     */
    private final String SPACE_URL_PREFIX = "/xwiki/rest/wikis/";

    /**
     * Path provided from XWiki RESTful API for spaces
     */
    private final String SPACE_URL_SUFFIX = "/spaces";

    /**
     * URL of the XWiki domain
     */
    private String URLprefix;

    /**
     * Name of Wiki for acquiring pages
     */
    private String wikiName;

    /**
     * @param URLprefix XWiki URl ex:"www.xwiki.org"
     * @param wikiName name of the wiki in UTF-8 format
     */
    public SpaceResources(String URLprefix, String wikiName)
    {
        this.URLprefix = URLprefix;
        this.wikiName = wikiName;
    }

    /**
     * @return list of spaces in the specified Wiki
     */
    public Spaces getSpaces()
    {

        String Uri = "http://" + URLprefix + SPACE_URL_PREFIX + wikiName + SPACE_URL_SUFFIX;

        return buildSpaces(super.getResponse(Uri));
    }

    /**
     * Parse xml into a Spaces object
     * 
     * @param content XML content
     * @return Spaces object deserialized from the xml content
     */
    private Spaces buildSpaces(String content)
    {
        Spaces spaces = new Spaces();

        Serializer serializer = new Persister();

        try {
            spaces = serializer.read(Spaces.class, content);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return spaces;
    }

    /**
     * Generate XML content from the Spaces object
     * 
     * @param spaces Spaces object to be serialized into XML
     * @return XML content of the provided Spaces object
     */
    private String buildXmlSpaces(Spaces spaces)
    {
        Serializer serializer = new Persister();

        StringWriter result = new StringWriter();

        try {
            serializer.write(spaces, result);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result.toString();
    }

}
