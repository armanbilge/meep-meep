/*
 * Copyright 2023 Arman Bilge
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package meep.scalac

import dotty.tools.dotc.ast.tpd.*
import dotty.tools.dotc.core.Contexts.Context
import dotty.tools.dotc.plugins.PluginPhase
import dotty.tools.dotc.plugins.StandardPlugin
import dotty.tools.dotc.report
import dotty.tools.dotc.transform.LambdaLift

class MeepPlugin extends StandardPlugin {
  def name = "meep"
  def description = "meep meep"

  def init(options: List[String]) = List(new MeepPhase)

}

class MeepPhase extends PluginPhase {
  def phaseName = "meep"

  override val runsAfter = Set(LambdaLift.name)

  override def transformClosure(tree: Closure)(using Context): Tree = {
    if tree.env.nonEmpty then
      report.error(
        s"Cannot close over ${tree.env.map(_.symbol.show).mkString(", ")}",
        tree.srcPos
      )
    tree
  }
}
