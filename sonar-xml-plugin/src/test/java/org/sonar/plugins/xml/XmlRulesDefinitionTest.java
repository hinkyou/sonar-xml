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
package org.sonar.plugins.xml;

import org.junit.jupiter.api.Test;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.server.rule.RulesDefinition.Rule;
import org.sonar.plugins.xml.checks.CheckList;

import static org.assertj.core.api.Assertions.assertThat;

class XmlRulesDefinitionTest {

  @Test
  void test() {
    XmlRulesDefinition rulesDefinition = new XmlRulesDefinition();
    RulesDefinition.Context context = new RulesDefinition.Context();
    rulesDefinition.define(context);
    RulesDefinition.Repository repository = context.repository(Xml.REPOSITORY_KEY);

//    assertThat(repository.name()).isEqualTo("SonarAnalyzer");
//    assertThat(repository.language()).isEqualTo("xml");
//    assertThat(repository.rules()).hasSize(CheckList.getCheckClasses().size());
//
//    RulesDefinition.Rule alertUseRule = repository.rule("S1120");
//    assertThat(alertUseRule).isNotNull();
//    assertThat(alertUseRule.name()).isEqualTo("Source code should be indented consistently");
//
//    assertThat(repository.rules().stream().filter(Rule::template).map(Rule::key))
//        .isNotEmpty()
//        .containsOnly("XPathCheck");

    for (Rule rule : repository.rules()) {
      for (RulesDefinition.Param param : rule.params()) {
        assertThat(param.description()).as("description for " + param.key()).isNotEmpty();
      }
    }
  }

}
