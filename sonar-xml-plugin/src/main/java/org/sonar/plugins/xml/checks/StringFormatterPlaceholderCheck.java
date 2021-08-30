/*
 * SonarQube XML Plugin
 * Copyright (C) 2010-2021 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.plugins.xml.checks;

import org.sonar.check.Rule;
import org.sonarsource.analyzer.commons.xml.XmlFile;
import org.sonarsource.analyzer.commons.xml.checks.SonarXmlCheck;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
 * RSPEC-103
 */
@Rule(key = StringFormatterPlaceholderCheck.RULE_KEY)
public class StringFormatterPlaceholderCheck extends SonarXmlCheck {

    public static final String RULE_KEY = "StringFormatterPlaceholder";

    private static final String MESSAGE = "%占位符后有空白字符，请检查核对";

    private static final Pattern PLACEHOLDER_WITH_BLANK = Pattern.compile("%\\s");

    @Override
    public void scanFile(XmlFile file) {
        checkNode(file.getDocument());
    }

    private void checkNode(Node node) {
        List<Node> children = XmlFile.children(node);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element currentElement = (Element) node;
            checkStyle(currentElement);
        }
        children.forEach(this::checkNode);
    }

    private void checkStyle(Element node) {
        Node childNode = node.getChildNodes().item(0);
        if (childNode.getNodeType() != Node.TEXT_NODE) {
            return;
        }
        String textContent = childNode.getTextContent();
        boolean matches = PLACEHOLDER_WITH_BLANK.matcher(textContent).find();
        if (matches) {
            reportIssue(XmlFile.nodeLocation(childNode), MESSAGE, Collections.emptyList());
        }
    }


}
