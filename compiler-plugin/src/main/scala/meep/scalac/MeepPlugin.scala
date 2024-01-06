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
